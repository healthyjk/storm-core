/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
/**
 * Autogenerated by Thrift Compiler (0.9.3)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package org.apache.storm.generated;

import org.apache.thrift.scheme.IScheme;
import org.apache.thrift.scheme.SchemeFactory;
import org.apache.thrift.scheme.StandardScheme;

import org.apache.thrift.scheme.TupleScheme;
import org.apache.thrift.protocol.TTupleProtocol;
import org.apache.thrift.protocol.TProtocolException;
import org.apache.thrift.EncodingUtils;
import org.apache.thrift.TException;
import org.apache.thrift.async.AsyncMethodCallback;
import org.apache.thrift.server.AbstractNonblockingServer.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.EnumMap;
import java.util.Set;
import java.util.HashSet;
import java.util.EnumSet;
import java.util.Collections;
import java.util.BitSet;
import java.nio.ByteBuffer;
import java.util.Arrays;
import javax.annotation.Generated;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked"})
@Generated(value = "Autogenerated by Thrift Compiler (0.9.3)")
public class LSWorkerHeartbeat implements org.apache.thrift.TBase<LSWorkerHeartbeat, LSWorkerHeartbeat._Fields>, java.io.Serializable, Cloneable, Comparable<LSWorkerHeartbeat> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("LSWorkerHeartbeat");

  private static final org.apache.thrift.protocol.TField TIME_SECS_FIELD_DESC = new org.apache.thrift.protocol.TField("time_secs", org.apache.thrift.protocol.TType.I32, (short)1);
  private static final org.apache.thrift.protocol.TField TOPOLOGY_ID_FIELD_DESC = new org.apache.thrift.protocol.TField("topology_id", org.apache.thrift.protocol.TType.STRING, (short)2);
  private static final org.apache.thrift.protocol.TField EXECUTORS_FIELD_DESC = new org.apache.thrift.protocol.TField("executors", org.apache.thrift.protocol.TType.LIST, (short)3);
  private static final org.apache.thrift.protocol.TField PORT_FIELD_DESC = new org.apache.thrift.protocol.TField("port", org.apache.thrift.protocol.TType.I32, (short)4);

  private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
  static {
    schemes.put(StandardScheme.class, new LSWorkerHeartbeatStandardSchemeFactory());
    schemes.put(TupleScheme.class, new LSWorkerHeartbeatTupleSchemeFactory());
  }

  private int time_secs; // required
  private String topology_id; // required
  private List<ExecutorInfo> executors; // required
  private int port; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    TIME_SECS((short)1, "time_secs"),
    TOPOLOGY_ID((short)2, "topology_id"),
    EXECUTORS((short)3, "executors"),
    PORT((short)4, "port");

    private static final Map<String, _Fields> byName = new HashMap<String, _Fields>();

    static {
      for (_Fields field : EnumSet.allOf(_Fields.class)) {
        byName.put(field.getFieldName(), field);
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, or null if its not found.
     */
    public static _Fields findByThriftId(int fieldId) {
      switch(fieldId) {
        case 1: // TIME_SECS
          return TIME_SECS;
        case 2: // TOPOLOGY_ID
          return TOPOLOGY_ID;
        case 3: // EXECUTORS
          return EXECUTORS;
        case 4: // PORT
          return PORT;
        default:
          return null;
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, throwing an exception
     * if it is not found.
     */
    public static _Fields findByThriftIdOrThrow(int fieldId) {
      _Fields fields = findByThriftId(fieldId);
      if (fields == null) throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
      return fields;
    }

    /**
     * Find the _Fields constant that matches name, or null if its not found.
     */
    public static _Fields findByName(String name) {
      return byName.get(name);
    }

    private final short _thriftId;
    private final String _fieldName;

    _Fields(short thriftId, String fieldName) {
      _thriftId = thriftId;
      _fieldName = fieldName;
    }

    public short getThriftFieldId() {
      return _thriftId;
    }

    public String getFieldName() {
      return _fieldName;
    }
  }

  // isset id assignments
  private static final int __TIME_SECS_ISSET_ID = 0;
  private static final int __PORT_ISSET_ID = 1;
  private byte __isset_bitfield = 0;
  public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.TIME_SECS, new org.apache.thrift.meta_data.FieldMetaData("time_secs", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32)));
    tmpMap.put(_Fields.TOPOLOGY_ID, new org.apache.thrift.meta_data.FieldMetaData("topology_id", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.EXECUTORS, new org.apache.thrift.meta_data.FieldMetaData("executors", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.ListMetaData(org.apache.thrift.protocol.TType.LIST, 
            new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, ExecutorInfo.class))));
    tmpMap.put(_Fields.PORT, new org.apache.thrift.meta_data.FieldMetaData("port", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32)));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(LSWorkerHeartbeat.class, metaDataMap);
  }

  public LSWorkerHeartbeat() {
  }

  public LSWorkerHeartbeat(
    int time_secs,
    String topology_id,
    List<ExecutorInfo> executors,
    int port)
  {
    this();
    this.time_secs = time_secs;
    set_time_secs_isSet(true);
    this.topology_id = topology_id;
    this.executors = executors;
    this.port = port;
    set_port_isSet(true);
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public LSWorkerHeartbeat(LSWorkerHeartbeat other) {
    __isset_bitfield = other.__isset_bitfield;
    this.time_secs = other.time_secs;
    if (other.is_set_topology_id()) {
      this.topology_id = other.topology_id;
    }
    if (other.is_set_executors()) {
      List<ExecutorInfo> __this__executors = new ArrayList<ExecutorInfo>(other.executors.size());
      for (ExecutorInfo other_element : other.executors) {
        __this__executors.add(new ExecutorInfo(other_element));
      }
      this.executors = __this__executors;
    }
    this.port = other.port;
  }

  public LSWorkerHeartbeat deepCopy() {
    return new LSWorkerHeartbeat(this);
  }

  @Override
  public void clear() {
    set_time_secs_isSet(false);
    this.time_secs = 0;
    this.topology_id = null;
    this.executors = null;
    set_port_isSet(false);
    this.port = 0;
  }

  public int get_time_secs() {
    return this.time_secs;
  }

  public void set_time_secs(int time_secs) {
    this.time_secs = time_secs;
    set_time_secs_isSet(true);
  }

  public void unset_time_secs() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __TIME_SECS_ISSET_ID);
  }

  /** Returns true if field time_secs is set (has been assigned a value) and false otherwise */
  public boolean is_set_time_secs() {
    return EncodingUtils.testBit(__isset_bitfield, __TIME_SECS_ISSET_ID);
  }

  public void set_time_secs_isSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __TIME_SECS_ISSET_ID, value);
  }

  public String get_topology_id() {
    return this.topology_id;
  }

  public void set_topology_id(String topology_id) {
    this.topology_id = topology_id;
  }

  public void unset_topology_id() {
    this.topology_id = null;
  }

  /** Returns true if field topology_id is set (has been assigned a value) and false otherwise */
  public boolean is_set_topology_id() {
    return this.topology_id != null;
  }

  public void set_topology_id_isSet(boolean value) {
    if (!value) {
      this.topology_id = null;
    }
  }

  public int get_executors_size() {
    return (this.executors == null) ? 0 : this.executors.size();
  }

  public java.util.Iterator<ExecutorInfo> get_executors_iterator() {
    return (this.executors == null) ? null : this.executors.iterator();
  }

  public void add_to_executors(ExecutorInfo elem) {
    if (this.executors == null) {
      this.executors = new ArrayList<ExecutorInfo>();
    }
    this.executors.add(elem);
  }

  public List<ExecutorInfo> get_executors() {
    return this.executors;
  }

  public void set_executors(List<ExecutorInfo> executors) {
    this.executors = executors;
  }

  public void unset_executors() {
    this.executors = null;
  }

  /** Returns true if field executors is set (has been assigned a value) and false otherwise */
  public boolean is_set_executors() {
    return this.executors != null;
  }

  public void set_executors_isSet(boolean value) {
    if (!value) {
      this.executors = null;
    }
  }

  public int get_port() {
    return this.port;
  }

  public void set_port(int port) {
    this.port = port;
    set_port_isSet(true);
  }

  public void unset_port() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __PORT_ISSET_ID);
  }

  /** Returns true if field port is set (has been assigned a value) and false otherwise */
  public boolean is_set_port() {
    return EncodingUtils.testBit(__isset_bitfield, __PORT_ISSET_ID);
  }

  public void set_port_isSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __PORT_ISSET_ID, value);
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case TIME_SECS:
      if (value == null) {
        unset_time_secs();
      } else {
        set_time_secs((Integer)value);
      }
      break;

    case TOPOLOGY_ID:
      if (value == null) {
        unset_topology_id();
      } else {
        set_topology_id((String)value);
      }
      break;

    case EXECUTORS:
      if (value == null) {
        unset_executors();
      } else {
        set_executors((List<ExecutorInfo>)value);
      }
      break;

    case PORT:
      if (value == null) {
        unset_port();
      } else {
        set_port((Integer)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case TIME_SECS:
      return get_time_secs();

    case TOPOLOGY_ID:
      return get_topology_id();

    case EXECUTORS:
      return get_executors();

    case PORT:
      return get_port();

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case TIME_SECS:
      return is_set_time_secs();
    case TOPOLOGY_ID:
      return is_set_topology_id();
    case EXECUTORS:
      return is_set_executors();
    case PORT:
      return is_set_port();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof LSWorkerHeartbeat)
      return this.equals((LSWorkerHeartbeat)that);
    return false;
  }

  public boolean equals(LSWorkerHeartbeat that) {
    if (that == null)
      return false;

    boolean this_present_time_secs = true;
    boolean that_present_time_secs = true;
    if (this_present_time_secs || that_present_time_secs) {
      if (!(this_present_time_secs && that_present_time_secs))
        return false;
      if (this.time_secs != that.time_secs)
        return false;
    }

    boolean this_present_topology_id = true && this.is_set_topology_id();
    boolean that_present_topology_id = true && that.is_set_topology_id();
    if (this_present_topology_id || that_present_topology_id) {
      if (!(this_present_topology_id && that_present_topology_id))
        return false;
      if (!this.topology_id.equals(that.topology_id))
        return false;
    }

    boolean this_present_executors = true && this.is_set_executors();
    boolean that_present_executors = true && that.is_set_executors();
    if (this_present_executors || that_present_executors) {
      if (!(this_present_executors && that_present_executors))
        return false;
      if (!this.executors.equals(that.executors))
        return false;
    }

    boolean this_present_port = true;
    boolean that_present_port = true;
    if (this_present_port || that_present_port) {
      if (!(this_present_port && that_present_port))
        return false;
      if (this.port != that.port)
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    List<Object> list = new ArrayList<Object>();

    boolean present_time_secs = true;
    list.add(present_time_secs);
    if (present_time_secs)
      list.add(time_secs);

    boolean present_topology_id = true && (is_set_topology_id());
    list.add(present_topology_id);
    if (present_topology_id)
      list.add(topology_id);

    boolean present_executors = true && (is_set_executors());
    list.add(present_executors);
    if (present_executors)
      list.add(executors);

    boolean present_port = true;
    list.add(present_port);
    if (present_port)
      list.add(port);

    return list.hashCode();
  }

  @Override
  public int compareTo(LSWorkerHeartbeat other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = Boolean.valueOf(is_set_time_secs()).compareTo(other.is_set_time_secs());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (is_set_time_secs()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.time_secs, other.time_secs);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(is_set_topology_id()).compareTo(other.is_set_topology_id());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (is_set_topology_id()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.topology_id, other.topology_id);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(is_set_executors()).compareTo(other.is_set_executors());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (is_set_executors()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.executors, other.executors);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(is_set_port()).compareTo(other.is_set_port());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (is_set_port()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.port, other.port);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    return 0;
  }

  public _Fields fieldForId(int fieldId) {
    return _Fields.findByThriftId(fieldId);
  }

  public void read(org.apache.thrift.protocol.TProtocol iprot) throws org.apache.thrift.TException {
    schemes.get(iprot.getScheme()).getScheme().read(iprot, this);
  }

  public void write(org.apache.thrift.protocol.TProtocol oprot) throws org.apache.thrift.TException {
    schemes.get(oprot.getScheme()).getScheme().write(oprot, this);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder("LSWorkerHeartbeat(");
    boolean first = true;

    sb.append("time_secs:");
    sb.append(this.time_secs);
    first = false;
    if (!first) sb.append(", ");
    sb.append("topology_id:");
    if (this.topology_id == null) {
      sb.append("null");
    } else {
      sb.append(this.topology_id);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("executors:");
    if (this.executors == null) {
      sb.append("null");
    } else {
      sb.append(this.executors);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("port:");
    sb.append(this.port);
    first = false;
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    if (!is_set_time_secs()) {
      throw new org.apache.thrift.protocol.TProtocolException("Required field 'time_secs' is unset! Struct:" + toString());
    }

    if (!is_set_topology_id()) {
      throw new org.apache.thrift.protocol.TProtocolException("Required field 'topology_id' is unset! Struct:" + toString());
    }

    if (!is_set_executors()) {
      throw new org.apache.thrift.protocol.TProtocolException("Required field 'executors' is unset! Struct:" + toString());
    }

    if (!is_set_port()) {
      throw new org.apache.thrift.protocol.TProtocolException("Required field 'port' is unset! Struct:" + toString());
    }

    // check for sub-struct validity
  }

  private void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException {
    try {
      write(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(out)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, ClassNotFoundException {
    try {
      // it doesn't seem like you should have to do this, but java serialization is wacky, and doesn't call the default constructor.
      __isset_bitfield = 0;
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class LSWorkerHeartbeatStandardSchemeFactory implements SchemeFactory {
    public LSWorkerHeartbeatStandardScheme getScheme() {
      return new LSWorkerHeartbeatStandardScheme();
    }
  }

  private static class LSWorkerHeartbeatStandardScheme extends StandardScheme<LSWorkerHeartbeat> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, LSWorkerHeartbeat struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // TIME_SECS
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.time_secs = iprot.readI32();
              struct.set_time_secs_isSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // TOPOLOGY_ID
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.topology_id = iprot.readString();
              struct.set_topology_id_isSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // EXECUTORS
            if (schemeField.type == org.apache.thrift.protocol.TType.LIST) {
              {
                org.apache.thrift.protocol.TList _list734 = iprot.readListBegin();
                struct.executors = new ArrayList<ExecutorInfo>(_list734.size);
                ExecutorInfo _elem735;
                for (int _i736 = 0; _i736 < _list734.size; ++_i736)
                {
                  _elem735 = new ExecutorInfo();
                  _elem735.read(iprot);
                  struct.executors.add(_elem735);
                }
                iprot.readListEnd();
              }
              struct.set_executors_isSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 4: // PORT
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.port = iprot.readI32();
              struct.set_port_isSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          default:
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
        }
        iprot.readFieldEnd();
      }
      iprot.readStructEnd();
      struct.validate();
    }

    public void write(org.apache.thrift.protocol.TProtocol oprot, LSWorkerHeartbeat struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      oprot.writeFieldBegin(TIME_SECS_FIELD_DESC);
      oprot.writeI32(struct.time_secs);
      oprot.writeFieldEnd();
      if (struct.topology_id != null) {
        oprot.writeFieldBegin(TOPOLOGY_ID_FIELD_DESC);
        oprot.writeString(struct.topology_id);
        oprot.writeFieldEnd();
      }
      if (struct.executors != null) {
        oprot.writeFieldBegin(EXECUTORS_FIELD_DESC);
        {
          oprot.writeListBegin(new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRUCT, struct.executors.size()));
          for (ExecutorInfo _iter737 : struct.executors)
          {
            _iter737.write(oprot);
          }
          oprot.writeListEnd();
        }
        oprot.writeFieldEnd();
      }
      oprot.writeFieldBegin(PORT_FIELD_DESC);
      oprot.writeI32(struct.port);
      oprot.writeFieldEnd();
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class LSWorkerHeartbeatTupleSchemeFactory implements SchemeFactory {
    public LSWorkerHeartbeatTupleScheme getScheme() {
      return new LSWorkerHeartbeatTupleScheme();
    }
  }

  private static class LSWorkerHeartbeatTupleScheme extends TupleScheme<LSWorkerHeartbeat> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, LSWorkerHeartbeat struct) throws org.apache.thrift.TException {
      TTupleProtocol oprot = (TTupleProtocol) prot;
      oprot.writeI32(struct.time_secs);
      oprot.writeString(struct.topology_id);
      {
        oprot.writeI32(struct.executors.size());
        for (ExecutorInfo _iter738 : struct.executors)
        {
          _iter738.write(oprot);
        }
      }
      oprot.writeI32(struct.port);
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, LSWorkerHeartbeat struct) throws org.apache.thrift.TException {
      TTupleProtocol iprot = (TTupleProtocol) prot;
      struct.time_secs = iprot.readI32();
      struct.set_time_secs_isSet(true);
      struct.topology_id = iprot.readString();
      struct.set_topology_id_isSet(true);
      {
        org.apache.thrift.protocol.TList _list739 = new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRUCT, iprot.readI32());
        struct.executors = new ArrayList<ExecutorInfo>(_list739.size);
        ExecutorInfo _elem740;
        for (int _i741 = 0; _i741 < _list739.size; ++_i741)
        {
          _elem740 = new ExecutorInfo();
          _elem740.read(iprot);
          struct.executors.add(_elem740);
        }
      }
      struct.set_executors_isSet(true);
      struct.port = iprot.readI32();
      struct.set_port_isSet(true);
    }
  }

}

