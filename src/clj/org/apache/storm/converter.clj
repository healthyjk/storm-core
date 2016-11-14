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
(ns org.apache.storm.converter
  (:import [org.apache.storm.generated SupervisorInfo NodeInfo Assignment WorkerResources
                                       StormBase TopologyStatus ClusterWorkerHeartbeat ExecutorInfo ErrorInfo Credentials RebalanceOptions KillOptions
                                       TopologyActionOptions DebugOptions ProfileRequest GpuInfo GpuUtilizationInfo])
  (:import [edu.fudan.gpuhelper GPUHelper DeviceInfo]
           (edu.fudan.nvmlhelper NVMLHelper))
  (:use [org.apache.storm util stats log])
  (:require [org.apache.storm.daemon [common :as common]])
  (:require [clojure.data.json :as json]))

(defn thriftify-gpu-info [gpu-info]
  (doto (GpuInfo.)
    (.set_id (int (:id gpu-info)))
    (.set_name (:name gpu-info))
    (.set_driver_version (int (:driver-version gpu-info)))
    (.set_major (int (:major gpu-info)))
    (.set_minor (int (:minor gpu-info)))
    (.set_total_mem_size (long (:total-mem-size gpu-info)))
    (.set_multi_processor_count (int (:multi-processor-count gpu-info)))
    (.set_cuda_cores_per_multiproc (int (:cuda-cores-per-multiproc gpu-info)))
    (.set_gpu_clock_rate (int (:gpu-clock-rate gpu-info)))
    (.set_mem_clock_rate (int (:mem-clock-rate gpu-info)))
    (.set_mem_bus_width (int (:mem-bus-width gpu-info)))
    (.set_device_overlap (boolean (:device-overlap gpu-info)))
    (.set_async_engine_count (int (:async-engine-count gpu-info))))
  ;(do
  ;  (log-message "thriftify gpu info")
  ;  (log-message "id:" (int (:id gpu-info)))
  ;  (log-message "name:" (:name gpu-info))
  ;  (log-message "driver-version:"  (int (:driver-version gpu-info)))
  ;  (log-message "major:" (int (:major gpu-info)) )
  ;  (log-message "minor:" (int (:minor gpu-info)) )
  ;  (log-message "async:"  (int (:async-engine-count gpu-info)))
  ;  (log-message "multi-core:" (*  (int (:multi-processor-count gpu-info)) (int (:cuda-cores-per-multiproc gpu-info))))
  ;  (log-message "total-mem-size:" (long (:total-mem-size gpu-info)))
  ;)
  )

(defn clojurify-gpu-info [^GpuInfo gpu-info]
  (if gpu-info
    (org.apache.storm.daemon.common.GpuInfo.
      (.get_id gpu-info)
      (.get_name gpu-info)
      (.get_driver_version gpu-info)
      (.get_major gpu-info)
      (.get_minor gpu-info)
      (.get_total_mem_size gpu-info)
      (.get_multi_processor_count gpu-info)
      (.get_cuda_cores_per_multiproc gpu-info)
      (.get_gpu_clock_rate gpu-info)
      (.get_mem_clock_rate gpu-info)
      (.get_mem_bus_width gpu-info)
      (.is_device_overlap gpu-info)
      (.get_async_engine_count gpu-info)
      )))

(defn thriftify-gpu-utilization-info [gpu-util-info]
  (doto (GpuUtilizationInfo.)
    (.set_id (int (:id gpu-util-info)))
    (.set_gpu_util (int (:gpu-util gpu-util-info)))
    (.set_total_mem (long (:total-mem gpu-util-info)))
    (.set_used_mem (long (:used-mem gpu-util-info)))
    (.set_process_count (int (:process-count gpu-util-info)))
    ))

(defn clojurify-gpu-utilization-info [^GpuUtilizationInfo gpu-util-info]
  (if gpu-util-info
    (org.apache.storm.daemon.common.GpuUtilizationInfo.
      (.get_id gpu-util-info)
      (.get_gpu_util gpu-util-info)
      (.get_total_mem gpu-util-info)
      (.get_used_mem gpu-util-info)
      (.get_process_count gpu-util-info)
      )))

(defn thriftify-supervisor-info [supervisor-info]
  (doto (SupervisorInfo.)
    (.set_time_secs (long (:time-secs supervisor-info)))
    (.set_hostname (:hostname supervisor-info))
    (.set_assignment_id (:assignment-id supervisor-info))
    (.set_used_ports (map long (:used-ports supervisor-info)))
    (.set_meta (map long (:meta supervisor-info)))
    (.set_scheduler_meta (:scheduler-meta supervisor-info))
    (.set_uptime_secs (long (:uptime-secs supervisor-info)))
    (.set_version (:version supervisor-info))
    (.set_resources_map (:resources-map supervisor-info))
    (.set_system_stats (:system-stats supervisor-info))
    (.set_gpu_infos (into [] (map thriftify-gpu-info (:gpu-infos supervisor-info))))
    (.set_gpu_util_infos (into [] (map thriftify-gpu-utilization-info (:gpu-util-infos supervisor-info))))
    ))

(defn clojurify-supervisor-info [^SupervisorInfo supervisor-info]
  (if supervisor-info
    (org.apache.storm.daemon.common.SupervisorInfo.
      (.get_time_secs supervisor-info)
      (.get_hostname supervisor-info)
      (.get_assignment_id supervisor-info)
      (if (.get_used_ports supervisor-info) (into [] (.get_used_ports supervisor-info)))
      (if (.get_meta supervisor-info) (into [] (.get_meta supervisor-info)))
      (if (.get_scheduler_meta supervisor-info) (into {} (.get_scheduler_meta supervisor-info)))
      (.get_uptime_secs supervisor-info)
      (.get_version supervisor-info)
      (if-let [res-map (.get_resources_map supervisor-info)] (into {} res-map))
      (if-let [system_stats (.get_system_stats supervisor-info)] (into {} system_stats))
      (if-let [gpu-infos (.get_gpu_infos supervisor-info)] (map clojurify-gpu-info gpu-infos))
      (if-let [gpu-util-infos (.get_gpu_util_infos supervisor-info)] (map clojurify-gpu-utilization-info gpu-util-infos))
      )))

(defn thriftify-assignment [assignment]
  (let [thrift-assignment (doto (Assignment.)
                            (.set_master_code_dir (:master-code-dir assignment))
                            (.set_node_host (:node->host assignment))
                            (.set_executor_node_port (into {}
                                                           (map (fn [[k v]]
                                                                  [(map long k)
                                                                   (NodeInfo. (first v) (set (map long (rest v))))])
                                                                (:executor->node+port assignment))))
                            (.set_executor_start_time_secs
                              (into {}
                                    (map (fn [[k v]]
                                           [(map long k) (long v)])
                                         (:executor->start-time-secs assignment)))))]
    (if (:worker->resources assignment)
      (.set_worker_resources thrift-assignment (into {} (map
                                                          (fn [[node+port resources]]
                                                            [(NodeInfo. (first node+port) (set (map long (rest node+port))))
                                                             (doto (WorkerResources.)
                                                               (.set_mem_on_heap (first resources))
                                                               (.set_mem_off_heap (second resources))
                                                               (.set_cpu (last resources)))])
                                                          (:worker->resources assignment)))))
    thrift-assignment))

(defn clojurify-executor->node_port [executor->node_port]
  (into {}
    (map-val
      (fn [nodeInfo]
        (concat [(.get_node nodeInfo)] (.get_port nodeInfo))) ;nodeInfo should be converted to [node,port1,port2..]
      (map-key
        (fn [list-of-executors]
          (into [] list-of-executors)) ; list of executors must be coverted to clojure vector to ensure it is sortable.
        executor->node_port))))

(defn clojurify-worker->resources [worker->resources]
  "convert worker info to be [node, port]
   convert resources to be [mem_on_heap mem_off_heap cpu]"
  (into {} (map
             (fn [[nodeInfo resources]]
               [(concat [(.get_node nodeInfo)] (.get_port nodeInfo))
                [(.get_mem_on_heap resources) (.get_mem_off_heap resources) (.get_cpu resources)]])
             worker->resources)))

(defn clojurify-assignment [^Assignment assignment]
  (if assignment
    (org.apache.storm.daemon.common.Assignment.
      (.get_master_code_dir assignment)
      (into {} (.get_node_host assignment))
      (clojurify-executor->node_port (into {} (.get_executor_node_port assignment)))
      (map-key (fn [executor] (into [] executor))
        (into {} (.get_executor_start_time_secs assignment)))
      (clojurify-worker->resources (into {} (.get_worker_resources assignment))))))

(defn convert-to-symbol-from-status [status]
  (condp = status
    TopologyStatus/ACTIVE {:type :active}
    TopologyStatus/INACTIVE {:type :inactive}
    TopologyStatus/REBALANCING {:type :rebalancing}
    TopologyStatus/KILLED {:type :killed}
    nil))

(defn- convert-to-status-from-symbol [status]
  (if status
    (condp = (:type status)
      :active TopologyStatus/ACTIVE
      :inactive TopologyStatus/INACTIVE
      :rebalancing TopologyStatus/REBALANCING
      :killed TopologyStatus/KILLED
      nil)))

(defn clojurify-rebalance-options [^RebalanceOptions rebalance-options]
  (-> {:action :rebalance}
    (assoc-non-nil :delay-secs (if (.is_set_wait_secs rebalance-options) (.get_wait_secs rebalance-options)))
    (assoc-non-nil :num-workers (if (.is_set_num_workers rebalance-options) (.get_num_workers rebalance-options)))
    (assoc-non-nil :component->executors (if (.is_set_num_executors rebalance-options) (into {} (.get_num_executors rebalance-options))))))

(defn thriftify-rebalance-options [rebalance-options]
  (if rebalance-options
    (let [thrift-rebalance-options (RebalanceOptions.)]
      (if (:delay-secs rebalance-options)
        (.set_wait_secs thrift-rebalance-options (int (:delay-secs rebalance-options))))
      (if (:num-workers rebalance-options)
        (.set_num_workers thrift-rebalance-options (int (:num-workers rebalance-options))))
      (if (:component->executors rebalance-options)
        (.set_num_executors thrift-rebalance-options (map-val int (:component->executors rebalance-options))))
      thrift-rebalance-options)))

(defn clojurify-kill-options [^KillOptions kill-options]
  (-> {:action :kill}
    (assoc-non-nil :delay-secs (if (.is_set_wait_secs kill-options) (.get_wait_secs kill-options)))))

(defn thriftify-kill-options [kill-options]
  (if kill-options
    (let [thrift-kill-options (KillOptions.)]
      (if (:delay-secs kill-options)
        (.set_wait_secs thrift-kill-options (int (:delay-secs kill-options))))
      thrift-kill-options)))

(defn thriftify-topology-action-options [storm-base]
  (if (:topology-action-options storm-base)
    (let [ topology-action-options (:topology-action-options storm-base)
           action (:action topology-action-options)
           thrift-topology-action-options (TopologyActionOptions.)]
      (if (= action :kill)
        (.set_kill_options thrift-topology-action-options (thriftify-kill-options topology-action-options)))
      (if (= action :rebalance)
        (.set_rebalance_options thrift-topology-action-options (thriftify-rebalance-options topology-action-options)))
      thrift-topology-action-options)))

(defn clojurify-topology-action-options [^TopologyActionOptions topology-action-options]
  (if topology-action-options
    (or (and (.is_set_kill_options topology-action-options)
             (clojurify-kill-options
               (.get_kill_options topology-action-options)))
        (and (.is_set_rebalance_options topology-action-options)
             (clojurify-rebalance-options
               (.get_rebalance_options topology-action-options))))))

(defn clojurify-debugoptions [^DebugOptions options]
  (if options
    {
      :enable (.is_enable options)
      :samplingpct (.get_samplingpct options)
      }
    ))

(defn thriftify-debugoptions [options]
  (doto (DebugOptions.)
    (.set_enable (get options :enable false))
    (.set_samplingpct (get options :samplingpct 10))))

(defn thriftify-storm-base [storm-base]
  (doto (StormBase.)
    (.set_name (:storm-name storm-base))
    (.set_launch_time_secs (int (:launch-time-secs storm-base)))
    (.set_status (convert-to-status-from-symbol (:status storm-base)))
    (.set_num_workers (int (:num-workers storm-base)))
    (.set_component_executors (map-val int (:component->executors storm-base)))
    (.set_owner (:owner storm-base))
    (.set_topology_action_options (thriftify-topology-action-options storm-base))
    (.set_prev_status (convert-to-status-from-symbol (:prev-status storm-base)))
    (.set_component_debug (map-val thriftify-debugoptions (:component->debug storm-base)))))

(defn clojurify-storm-base [^StormBase storm-base]
  (if storm-base
    (org.apache.storm.daemon.common.StormBase.
      (.get_name storm-base)
      (.get_launch_time_secs storm-base)
      (convert-to-symbol-from-status (.get_status storm-base))
      (.get_num_workers storm-base)
      (into {} (.get_component_executors storm-base))
      (.get_owner storm-base)
      (clojurify-topology-action-options (.get_topology_action_options storm-base))
      (convert-to-symbol-from-status (.get_prev_status storm-base))
      (map-val clojurify-debugoptions (.get_component_debug storm-base)))))

(defn thriftify-stats [stats]
  (if stats
    (map-val thriftify-executor-stats
      (map-key #(ExecutorInfo. (int (first %1)) (int (last %1)))
        stats))
    {}))

(defn clojurify-stats [stats]
  (if stats
    (map-val clojurify-executor-stats
      (map-key (fn [x] (list (.get_task_start x) (.get_task_end x)))
        stats))
    {}))

(defn clojurify-zk-worker-hb [^ClusterWorkerHeartbeat worker-hb]
  (if worker-hb
    {:storm-id (.get_storm_id worker-hb)
     :executor-stats (clojurify-stats (into {} (.get_executor_stats worker-hb)))
     :uptime (.get_uptime_secs worker-hb)
     :time-secs (.get_time_secs worker-hb)
     :system-stats (.get_system_stats worker-hb)
     }
    {}))

(defn thriftify-zk-worker-hb [worker-hb]
  (if (not-empty (filter second (:executor-stats worker-hb)))
    (doto (ClusterWorkerHeartbeat.)
      (.set_uptime_secs (:uptime worker-hb))
      (.set_storm_id (:storm-id worker-hb))
      (.set_executor_stats (thriftify-stats (filter second (:executor-stats worker-hb))))
      (.set_time_secs (:time-secs worker-hb))
      (.set_system_stats (:system-stats worker-hb))
      )))

(defn clojurify-error [^ErrorInfo error]
  (if error
    {
      :error (.get_error error)
      :time-secs (.get_error_time_secs error)
      :host (.get_host error)
      :port (.get_port error)
      }
    ))

(defn thriftify-error [error]
  (doto (ErrorInfo. (:error error) (:time-secs error))
    (.set_host (:host error))
    (.set_port (:port error))))

(defn clojurify-profile-request
  [^ProfileRequest request]
  (when request
    {:host (.get_node (.get_nodeInfo request))
     :port (first (.get_port (.get_nodeInfo request)))
     :action     (.get_action request)
     :timestamp  (.get_time_stamp request)}))

(defn thriftify-profile-request
  [profile-request]
  (let [nodeinfo (doto (NodeInfo.)
                   (.set_node (:host profile-request))
                   (.set_port (set [(:port profile-request)])))
        request (ProfileRequest. nodeinfo (:action profile-request))]
    (.set_time_stamp request (:timestamp profile-request))
    request))

(defn thriftify-credentials [credentials]
    (doto (Credentials.)
      (.set_creds (if credentials credentials {}))))

(defn clojurify-crdentials [^Credentials credentials]
  (if credentials
    (into {} (.get_creds credentials))
    nil
    ))

(defn device-info-to-gpu-info [^DeviceInfo device-info]
  "transform device-info to GpuInfo"
  (do
    ;(log-message "id:" (.getDevOrdinal device-info))
    ;(log-message "name:" (.getDevName device-info))
    ;(log-message "driver-version:" (.getDriverVersion device-info))
    ;(log-message "major:" (.getMajor device-info))
    ;(log-message "minor:" (.getMinor device-info))
    ;(log-message "async:" (.getAsyncEngineCount device-info))
    ;(log-message "multi-core:" (* (.getMultiprocessorCount device-info) (.getCUDACoresPerMulpro device-info)))
    ;(log-message "total-mem-size:" (.getTotalMemSize device-info))
  (if device-info
    (org.apache.storm.daemon.common.GpuInfo.
      (.getDevOrdinal device-info)
      (.getDevName device-info)
      (.getDriverVersion device-info)
      (.getMajor device-info)
      (.getMinor device-info)
      (.getTotalMemSize device-info)
      (.getMultiprocessorCount device-info)
      (.getCUDACoresPerMulpro device-info)
      (.getGPUClockRate device-info)
      (.getMemClockRate device-info)
      (.getMemBusWidth device-info)
      (.getDeviceOverlap device-info)
      (.getAsyncEngineCount device-info)
      ))))

(defn mk-device-infos-fn
  "Returns a fuction that returns the gpu information of the node"
  []
  (let [gpu-helper (GPUHelper.)
        gpu-count (.getDeviceCount gpu-helper)]
    (fn []
      (into [] (map #(.getDeviceInfo gpu-helper %) (range gpu-count))))
    ))

(defn mk-gpu-infos-fn
  "return a function that contains the detail gpu information"
  []
  (let [device-infos ((mk-device-infos-fn))]
    (fn []
      (into []
            (for [device-info device-infos]
              (device-info-to-gpu-info device-info))))
    ))

; device-info is a map converted from json
(defn device-info-to-gpu-util-info [device-info]
  (if device-info
    (org.apache.storm.daemon.common.GpuUtilizationInfo.
      (int (:deviceIndex device-info))
      (int (:gpuUtilization device-info))
      (long (:totalMem device-info))
      (long (:usedMem device-info))
      (int (:processCount device-info)))))

(defn mk-gpu-util-infos-fn
  "return a function that contains the detail gpu utilization information"
  []
  (fn []
    (let [nvml-info (NVMLHelper/getNvmlInfo)
          nvml-json-str (json/read-str nvml-info :key-fn keyword)
          gpu-util-infos (:deviceInfo nvml-json-str)]
      (log-message "gpu-util-info: " nvml-json-str)
      (into []
            (for [gpu-util-info gpu-util-infos]
              (device-info-to-gpu-util-info gpu-util-info))))))