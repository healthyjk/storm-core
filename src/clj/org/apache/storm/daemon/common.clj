;; Licensed to the Apache Software Foundation (ASF) under one
;; or more contributor license agreements.  See the NOTICE file
;; distributed with this work for additional information
;; regarding copyright ownership.  The ASF licenses this file
;; to you under the Apache License, Version 2.0 (the
;; "License"); you may not use this file except in compliance
;; with the License.  You may obtain a copy of the License at
;;
;; http://www.apache.org/licenses/LICENSE-2.0
;;
;; Unless required by applicable law or agreed to in writing, software
;; distributed under the License is distributed on an "AS IS" BASIS,
;; WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
;; See the License for the specific language governing permissions and
;; limitations under the License.
(ns org.apache.storm.daemon.common
  (:use [org.apache.storm log config util])
  (:import [org.apache.storm.generated StormTopology
            InvalidTopologyException GlobalStreamId]
           [org.apache.storm.utils ThriftTopologyUtils])
  (:import [org.apache.storm.utils Utils])
  (:import [org.apache.storm.daemon.metrics.reporters PreparableReporter]
           [com.codahale.metrics MetricRegistry]
           [org.apache.storm.daemon.metrics MetricsUtils])
  (:import [org.apache.storm.task WorkerTopologyContext])
  (:import [org.apache.storm Constants])
  (:import [org.apache.storm.metric SystemBolt])
  (:import [org.apache.storm.metric EventLoggerBolt])
  (:import [org.apache.storm.security.auth IAuthorizer]) 
  (:import [java.io InterruptedIOException])
  (:require [clojure.set :as set])  
  (:require [org.apache.storm.daemon.acker :as acker])
  (:require [org.apache.storm.thrift :as thrift])
  (:require [metrics.core  :refer [default-registry]]))

(defn start-metrics-reporter [reporter conf]
  (doto reporter
    (.prepare default-registry conf)
    (.start))
  (log-message "Started statistics report plugin..."))

(defn start-metrics-reporters [conf]
  (doseq [reporter (MetricsUtils/getPreparableReporters conf)]
    (start-metrics-reporter reporter conf)))


(def ACKER-COMPONENT-ID acker/ACKER-COMPONENT-ID)
(def ACKER-INIT-STREAM-ID acker/ACKER-INIT-STREAM-ID)
(def ACKER-ACK-STREAM-ID acker/ACKER-ACK-STREAM-ID)
(def ACKER-FAIL-STREAM-ID acker/ACKER-FAIL-STREAM-ID)
(def ACKER-RESET-TIMEOUT-STREAM-ID acker/ACKER-RESET-TIMEOUT-STREAM-ID)

(def SYSTEM-STREAM-ID "__system")

(def EVENTLOGGER-COMPONENT-ID "__eventlogger")
(def EVENTLOGGER-STREAM-ID "__eventlog")

(def SYSTEM-COMPONENT-ID Constants/SYSTEM_COMPONENT_ID)
(def SYSTEM-TICK-STREAM-ID Constants/SYSTEM_TICK_STREAM_ID)
(def METRICS-STREAM-ID Constants/METRICS_STREAM_ID)
(def METRICS-TICK-STREAM-ID Constants/METRICS_TICK_STREAM_ID)
(def CREDENTIALS-CHANGED-STREAM-ID Constants/CREDENTIALS_CHANGED_STREAM_ID)

;; the task id is the virtual port
;; node->host is here so that tasks know who to talk to just from assignment
;; this avoid situation where node goes down and task doesn't know what to do information-wise
(defrecord Assignment [master-code-dir node->host executor->node+port executor->start-time-secs worker->resources])


;; component->executors is a map from spout/bolt id to number of executors for that component
(defrecord StormBase [storm-name launch-time-secs status num-workers component->executors owner topology-action-options prev-status component->debug])

(defrecord GpuInfo [id name driver-version major minor total-mem-size multi-processor-count cuda-cores-per-multiproc gpu-clock-rate mem-clock-rate mem-bus-width device-overlap async-engine-count])

(defrecord GpuUtilizationInfo [id gpu-util total-mem used-mem process-count])

(defrecord SupervisorInfo [time-secs hostname assignment-id used-ports meta scheduler-meta uptime-secs version resources-map system-stats gpu-infos gpu-util-infos])

(defprotocol DaemonCommon
  (waiting? [this]))

(defrecord ExecutorStats [^long processed
                          ^long acked
                          ^long emitted
                          ^long transferred
                          ^long failed])

(defn new-executor-stats []
  (ExecutorStats. 0 0 0 0 0))

(defn get-storm-id [storm-cluster-state storm-name]
  (let [active-storms (.active-storms storm-cluster-state)]
    (find-first
      #(= storm-name (:storm-name (.storm-base storm-cluster-state % nil)))
      active-storms)
    ))

(defn topology-bases [storm-cluster-state]
  (let [active-topologies (.active-storms storm-cluster-state)]
    (into {} 
          (dofor [id active-topologies]
                 [id (.storm-base storm-cluster-state id nil)]
                 ))
    ))

(defn validate-distributed-mode! [conf]
  (if (local-mode? conf)
      (throw
        (IllegalArgumentException. "Cannot start server in local mode!"))))

(defmacro defserverfn [name & body]
  `(let [exec-fn# (fn ~@body)]
    (defn ~name [& args#]
      (try-cause
        (apply exec-fn# args#)
      (catch InterruptedIOException e#
        (throw e#))
      (catch InterruptedException e#
        (throw e#))
      (catch Throwable t#
        (log-error t# "Error on initialization of server " ~(str name))
        (exit-process! 13 "Error on initialization")
        )))))

(defn- validate-ids! [^StormTopology topology]
  (let [sets (map #(.getFieldValue topology %) thrift/STORM-TOPOLOGY-FIELDS)
        offending (apply any-intersection sets)]
    (if-not (empty? offending)
      (throw (InvalidTopologyException.
              (str "Duplicate component ids: " offending))))
    (doseq [f thrift/STORM-TOPOLOGY-FIELDS
            :let [obj-map (.getFieldValue topology f)]]
      (if-not (ThriftTopologyUtils/isWorkerHook f)
        (do
          (doseq [id (keys obj-map)]
            (if (Utils/isSystemId id)
              (throw (InvalidTopologyException.
                       (str id " is not a valid component id")))))
          (doseq [obj (vals obj-map)
                  id (-> obj .get_common .get_streams keys)]
            (if (Utils/isSystemId id)
              (throw (InvalidTopologyException.
                       (str id " is not a valid stream id"))))))))))

(defn all-components [^StormTopology topology]
  (apply merge {}
    (for [f thrift/STORM-TOPOLOGY-FIELDS]
      (if-not (ThriftTopologyUtils/isWorkerHook f)
        (.getFieldValue topology f)))))

(defn component-conf [component]
  (->> component
      .get_common
      .get_json_conf
      from-json))

(defn validate-basic! [^StormTopology topology]
  (validate-ids! topology)
  (doseq [f thrift/SPOUT-FIELDS
          obj (->> f (.getFieldValue topology) vals)]
    (if-not (empty? (-> obj .get_common .get_inputs))
      (throw (InvalidTopologyException. "May not declare inputs for a spout"))))
  (doseq [[comp-id comp] (all-components topology)
          :let [conf (component-conf comp)
                p (-> comp .get_common thrift/parallelism-hint)]]
    (when (and (> (conf TOPOLOGY-TASKS) 0)
               p
               (<= p 0))
      (throw (InvalidTopologyException. "Number of executors must be greater than 0 when number of tasks is greater than 0"))
      )))

(defn validate-structure! [^StormTopology topology]
  ;; validate all the component subscribe from component+stream which actually exists in the topology
  ;; and if it is a fields grouping, validate the corresponding field exists  
  (let [all-components (all-components topology)]
    (doseq [[id comp] all-components
            :let [inputs (.. comp get_common get_inputs)]]
      (doseq [[global-stream-id grouping] inputs
              :let [source-component-id (.get_componentId global-stream-id)
                    source-stream-id    (.get_streamId global-stream-id)]]
        (if-not (contains? all-components source-component-id)
          (throw (InvalidTopologyException. (str "Component: [" id "] subscribes from non-existent component [" source-component-id "]")))
          (let [source-streams (-> all-components (get source-component-id) .get_common .get_streams)]
            (if-not (contains? source-streams source-stream-id)
              (throw (InvalidTopologyException. (str "Component: [" id "] subscribes from non-existent stream: [" source-stream-id "] of component [" source-component-id "]")))
              (if (= :fields (thrift/grouping-type grouping))
                (let [grouping-fields (set (.get_fields grouping))
                      source-stream-fields (-> source-streams (get source-stream-id) .get_output_fields set)
                      diff-fields (set/difference grouping-fields source-stream-fields)]
                  (when-not (empty? diff-fields)
                    (throw (InvalidTopologyException. (str "Component: [" id "] subscribes from stream: [" source-stream-id "] of component [" source-component-id "] with non-existent fields: " diff-fields)))))))))))))

(defn acker-inputs [^StormTopology topology]
  (let [bolt-ids (.. topology get_bolts keySet)
        spout-ids (.. topology get_spouts keySet)
        spout-inputs (apply merge
                            (for [id spout-ids]
                              {[id ACKER-INIT-STREAM-ID] ["id"]}
                              ))
        bolt-inputs (apply merge
                           (for [id bolt-ids]
                             {[id ACKER-ACK-STREAM-ID] ["id"]
                              [id ACKER-FAIL-STREAM-ID] ["id"]
                              [id ACKER-RESET-TIMEOUT-STREAM-ID] ["id"]}
                             ))]
    (merge spout-inputs bolt-inputs)))

;; the event logger receives inputs from all the spouts and bolts
;; with a field grouping on component id so that all tuples from a component
;; goes to same executor and can be viewed via logviewer.
(defn eventlogger-inputs [^StormTopology topology]
  (let [bolt-ids (.. topology get_bolts keySet)
        spout-ids (.. topology get_spouts keySet)
        spout-inputs (apply merge
                       (for [id spout-ids]
                         {[id EVENTLOGGER-STREAM-ID] ["component-id"]}
                         ))
        bolt-inputs (apply merge
                      (for [id bolt-ids]
                        {[id EVENTLOGGER-STREAM-ID] ["component-id"]}
                        ))]
    (merge spout-inputs bolt-inputs)))

(defn add-acker! [storm-conf ^StormTopology ret]
  (let [num-executors (if (nil? (storm-conf TOPOLOGY-ACKER-EXECUTORS)) (storm-conf TOPOLOGY-WORKERS) (storm-conf TOPOLOGY-ACKER-EXECUTORS))
        acker-bolt (thrift/mk-bolt-spec* (acker-inputs ret)
                                         (new org.apache.storm.daemon.acker)
                                         {ACKER-ACK-STREAM-ID (thrift/direct-output-fields ["id"])
                                          ACKER-FAIL-STREAM-ID (thrift/direct-output-fields ["id"])
                                          ACKER-RESET-TIMEOUT-STREAM-ID (thrift/direct-output-fields ["id"])
                                          }
                                         :p num-executors
                                         :conf {TOPOLOGY-TASKS num-executors
                                                TOPOLOGY-TICK-TUPLE-FREQ-SECS (storm-conf TOPOLOGY-MESSAGE-TIMEOUT-SECS)})]
    (dofor [[_ bolt] (.get_bolts ret)
            :let [common (.get_common bolt)]]
           (do
             (.put_to_streams common ACKER-ACK-STREAM-ID (thrift/output-fields ["id" "ack-val"]))
             (.put_to_streams common ACKER-FAIL-STREAM-ID (thrift/output-fields ["id"]))
             (.put_to_streams common ACKER-RESET-TIMEOUT-STREAM-ID (thrift/output-fields ["id"]))
             ))
    (dofor [[_ spout] (.get_spouts ret)
            :let [common (.get_common spout)
                  spout-conf (merge
                               (component-conf spout)
                               {TOPOLOGY-TICK-TUPLE-FREQ-SECS (storm-conf TOPOLOGY-MESSAGE-TIMEOUT-SECS)})]]
      (do
        ;; this set up tick tuples to cause timeouts to be triggered
        (.set_json_conf common (to-json spout-conf))
        (.put_to_streams common ACKER-INIT-STREAM-ID (thrift/output-fields ["id" "init-val" "spout-task"]))
        (.put_to_inputs common
                        (GlobalStreamId. ACKER-COMPONENT-ID ACKER-ACK-STREAM-ID)
                        (thrift/mk-direct-grouping))
        (.put_to_inputs common
                        (GlobalStreamId. ACKER-COMPONENT-ID ACKER-FAIL-STREAM-ID)
                        (thrift/mk-direct-grouping))
        (.put_to_inputs common
                        (GlobalStreamId. ACKER-COMPONENT-ID ACKER-RESET-TIMEOUT-STREAM-ID)
                        (thrift/mk-direct-grouping))
        ))
    (.put_to_bolts ret "__acker" acker-bolt)
    ))

(defn add-metric-streams! [^StormTopology topology]
  (doseq [[_ component] (all-components topology)
          :let [common (.get_common component)]]
    (.put_to_streams common METRICS-STREAM-ID
                     (thrift/output-fields ["task-info" "data-points"]))))

(defn add-system-streams! [^StormTopology topology]
  (doseq [[_ component] (all-components topology)
          :let [common (.get_common component)]]
    (.put_to_streams common SYSTEM-STREAM-ID (thrift/output-fields ["event"]))))


(defn map-occurrences [afn coll]
  (->> coll
       (reduce (fn [[counts new-coll] x]
                 (let [occurs (inc (get counts x 0))]
                   [(assoc counts x occurs) (cons (afn x occurs) new-coll)]))
               [{} []])
       (second)
       (reverse)))

(defn number-duplicates
  "(number-duplicates [\"a\", \"b\", \"a\"]) => [\"a\", \"b\", \"a#2\"]"
  [coll]
  (map-occurrences (fn [x occurences] (if (>= occurences 2) (str x "#" occurences) x)) coll))

(defn metrics-consumer-register-ids
  "Generates a list of component ids for each metrics consumer
   e.g. [\"__metrics_org.mycompany.MyMetricsConsumer\", ..] "
  [storm-conf]
  (->> (get storm-conf TOPOLOGY-METRICS-CONSUMER-REGISTER)         
       (map #(get % "class"))
       (number-duplicates)
       (map #(str Constants/METRICS_COMPONENT_ID_PREFIX %))))

(defn metrics-consumer-bolt-specs [storm-conf topology]
  (let [component-ids-that-emit-metrics (cons SYSTEM-COMPONENT-ID (keys (all-components topology)))
        inputs (->> (for [comp-id component-ids-that-emit-metrics]
                      {[comp-id METRICS-STREAM-ID] :shuffle})
                    (into {}))
        
        mk-bolt-spec (fn [class arg p]
                       (thrift/mk-bolt-spec*
                        inputs
                        (org.apache.storm.metric.MetricsConsumerBolt. class arg)
                        {} :p p :conf {TOPOLOGY-TASKS p}))]
    
    (map
     (fn [component-id register]           
       [component-id (mk-bolt-spec (get register "class")
                                   (get register "argument")
                                   (or (get register "parallelism.hint") 1))])
     
     (metrics-consumer-register-ids storm-conf)
     (get storm-conf TOPOLOGY-METRICS-CONSUMER-REGISTER))))

;; return the fields that event logger bolt expects
(defn eventlogger-bolt-fields []
  [(EventLoggerBolt/FIELD_COMPONENT_ID) (EventLoggerBolt/FIELD_MESSAGE_ID)  (EventLoggerBolt/FIELD_TS) (EventLoggerBolt/FIELD_VALUES)]
  )

(defn add-eventlogger! [storm-conf ^StormTopology ret]
  (let [num-executors (if (nil? (storm-conf TOPOLOGY-EVENTLOGGER-EXECUTORS)) (storm-conf TOPOLOGY-WORKERS) (storm-conf TOPOLOGY-EVENTLOGGER-EXECUTORS))
        eventlogger-bolt (thrift/mk-bolt-spec* (eventlogger-inputs ret)
                     (EventLoggerBolt.)
                     {}
                     :p num-executors
                     :conf {TOPOLOGY-TASKS num-executors
                            TOPOLOGY-TICK-TUPLE-FREQ-SECS (storm-conf TOPOLOGY-MESSAGE-TIMEOUT-SECS)})]

    (doseq [[_ component] (all-components ret)
            :let [common (.get_common component)]]
      (.put_to_streams common EVENTLOGGER-STREAM-ID (thrift/output-fields (eventlogger-bolt-fields))))
    (.put_to_bolts ret EVENTLOGGER-COMPONENT-ID eventlogger-bolt)
    ))

(defn add-metric-components! [storm-conf ^StormTopology topology]  
  (doseq [[comp-id bolt-spec] (metrics-consumer-bolt-specs storm-conf topology)]
    (.put_to_bolts topology comp-id bolt-spec)))

(defn add-system-components! [conf ^StormTopology topology]
  (let [system-bolt-spec (thrift/mk-bolt-spec*
                          {}
                          (SystemBolt.)
                          {SYSTEM-TICK-STREAM-ID (thrift/output-fields ["rate_secs"])
                           METRICS-TICK-STREAM-ID (thrift/output-fields ["interval"])
                           CREDENTIALS-CHANGED-STREAM-ID (thrift/output-fields ["creds"])}
                          :p 0
                          :conf {TOPOLOGY-TASKS 0})]
    (.put_to_bolts topology SYSTEM-COMPONENT-ID system-bolt-spec)))

(defn system-topology! [storm-conf ^StormTopology topology]
  (validate-basic! topology)
  (let [ret (.deepCopy topology)]
    (add-acker! storm-conf ret)
    (add-eventlogger! storm-conf ret)
    (add-metric-components! storm-conf ret)
    (add-system-components! storm-conf ret)
    (add-metric-streams! ret)
    (add-system-streams! ret)
    (validate-structure! ret)
    ret
    ))

(defn has-ackers? [storm-conf]
  (or (nil? (storm-conf TOPOLOGY-ACKER-EXECUTORS)) (> (storm-conf TOPOLOGY-ACKER-EXECUTORS) 0)))

(defn has-eventloggers? [storm-conf]
  (or (nil? (storm-conf TOPOLOGY-EVENTLOGGER-EXECUTORS)) (> (storm-conf TOPOLOGY-EVENTLOGGER-EXECUTORS) 0)))

(defn num-start-executors [component]
  (thrift/parallelism-hint (.get_common component)))

(defn storm-task-info
  "Returns map from task -> component id"
  [^StormTopology user-topology storm-conf]
  (->> (system-topology! storm-conf user-topology)
       all-components
       (map-val (comp #(get % TOPOLOGY-TASKS) component-conf))
       (sort-by first)
       (mapcat (fn [[c num-tasks]] (repeat num-tasks c)))
       (map (fn [id comp] [id comp]) (iterate (comp int inc) (int 1)))
       (into {})
       ))

(defn executor-id->tasks [[first-task-id last-task-id]]
  (->> (range first-task-id (inc last-task-id))
       (map int)))

(defn worker-context [worker]
  (WorkerTopologyContext. (:system-topology worker)
                          (:storm-conf worker)
                          (:task->component worker)
                          (:component->sorted-tasks worker)
                          (:component->stream->fields worker)
                          (:storm-id worker)
                          (supervisor-storm-resources-path
                            (supervisor-stormdist-root (:conf worker) (:storm-id worker)))
                          (worker-pids-root (:conf worker) (:worker-id worker))
                          (:port worker)
                          (:task-ids worker)
                          (:default-shared-resources worker)
                          (:user-shared-resources worker)
                          ))


(defn to-task->node+port [executor->node+port]
  (->> executor->node+port
       (mapcat (fn [[e node+port]] (for [t (executor-id->tasks e)] [t node+port])))
       (into {})))

(defn mk-authorization-handler [klassname conf]
  (let [aznClass (if klassname (Class/forName klassname))
        aznHandler (if aznClass (.newInstance aznClass))] 
    (if aznHandler (.prepare ^IAuthorizer aznHandler conf))
    (log-debug "authorization class name:" klassname
                 " class:" aznClass
                 " handler:" aznHandler)
    aznHandler
  )) 

