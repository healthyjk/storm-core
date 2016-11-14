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
public class GpuUtilizationInfo implements org.apache.thrift.TBase<GpuUtilizationInfo, GpuUtilizationInfo._Fields>, java.io.Serializable, Cloneable, Comparable<GpuUtilizationInfo> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("GpuUtilizationInfo");

  private static final org.apache.thrift.protocol.TField ID_FIELD_DESC = new org.apache.thrift.protocol.TField("id", org.apache.thrift.protocol.TType.I32, (short)1);
  private static final org.apache.thrift.protocol.TField GPU_UTIL_FIELD_DESC = new org.apache.thrift.protocol.TField("gpu_util", org.apache.thrift.protocol.TType.I32, (short)2);
  private static final org.apache.thrift.protocol.TField TOTAL_MEM_FIELD_DESC = new org.apache.thrift.protocol.TField("total_mem", org.apache.thrift.protocol.TType.I64, (short)3);
  private static final org.apache.thrift.protocol.TField USED_MEM_FIELD_DESC = new org.apache.thrift.protocol.TField("used_mem", org.apache.thrift.protocol.TType.I64, (short)4);
  private static final org.apache.thrift.protocol.TField PROCESS_COUNT_FIELD_DESC = new org.apache.thrift.protocol.TField("process_count", org.apache.thrift.protocol.TType.I32, (short)5);

  private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
  static {
    schemes.put(StandardScheme.class, new GpuUtilizationInfoStandardSchemeFactory());
    schemes.put(TupleScheme.class, new GpuUtilizationInfoTupleSchemeFactory());
  }

  private int id; // required
  private int gpu_util; // required
  private long total_mem; // required
  private long used_mem; // required
  private int process_count; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    ID((short)1, "id"),
    GPU_UTIL((short)2, "gpu_util"),
    TOTAL_MEM((short)3, "total_mem"),
    USED_MEM((short)4, "used_mem"),
    PROCESS_COUNT((short)5, "process_count");

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
        case 1: // ID
          return ID;
        case 2: // GPU_UTIL
          return GPU_UTIL;
        case 3: // TOTAL_MEM
          return TOTAL_MEM;
        case 4: // USED_MEM
          return USED_MEM;
        case 5: // PROCESS_COUNT
          return PROCESS_COUNT;
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
  private static final int __ID_ISSET_ID = 0;
  private static final int __GPU_UTIL_ISSET_ID = 1;
  private static final int __TOTAL_MEM_ISSET_ID = 2;
  private static final int __USED_MEM_ISSET_ID = 3;
  private static final int __PROCESS_COUNT_ISSET_ID = 4;
  private byte __isset_bitfield = 0;
  public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.ID, new org.apache.thrift.meta_data.FieldMetaData("id", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32)));
    tmpMap.put(_Fields.GPU_UTIL, new org.apache.thrift.meta_data.FieldMetaData("gpu_util", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32)));
    tmpMap.put(_Fields.TOTAL_MEM, new org.apache.thrift.meta_data.FieldMetaData("total_mem", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64)));
    tmpMap.put(_Fields.USED_MEM, new org.apache.thrift.meta_data.FieldMetaData("used_mem", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64)));
    tmpMap.put(_Fields.PROCESS_COUNT, new org.apache.thrift.meta_data.FieldMetaData("process_count", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32)));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(GpuUtilizationInfo.class, metaDataMap);
  }

  public GpuUtilizationInfo() {
  }

  public GpuUtilizationInfo(
    int id,
    int gpu_util,
    long total_mem,
    long used_mem,
    int process_count)
  {
    this();
    this.id = id;
    set_id_isSet(true);
    this.gpu_util = gpu_util;
    set_gpu_util_isSet(true);
    this.total_mem = total_mem;
    set_total_mem_isSet(true);
    this.used_mem = used_mem;
    set_used_mem_isSet(true);
    this.process_count = process_count;
    set_process_count_isSet(true);
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public GpuUtilizationInfo(GpuUtilizationInfo other) {
    __isset_bitfield = other.__isset_bitfield;
    this.id = other.id;
    this.gpu_util = other.gpu_util;
    this.total_mem = other.total_mem;
    this.used_mem = other.used_mem;
    this.process_count = other.process_count;
  }

  public GpuUtilizationInfo deepCopy() {
    return new GpuUtilizationInfo(this);
  }

  @Override
  public void clear() {
    set_id_isSet(false);
    this.id = 0;
    set_gpu_util_isSet(false);
    this.gpu_util = 0;
    set_total_mem_isSet(false);
    this.total_mem = 0;
    set_used_mem_isSet(false);
    this.used_mem = 0;
    set_process_count_isSet(false);
    this.process_count = 0;
  }

  public int get_id() {
    return this.id;
  }

  public void set_id(int id) {
    this.id = id;
    set_id_isSet(true);
  }

  public void unset_id() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __ID_ISSET_ID);
  }

  /** Returns true if field id is set (has been assigned a value) and false otherwise */
  public boolean is_set_id() {
    return EncodingUtils.testBit(__isset_bitfield, __ID_ISSET_ID);
  }

  public void set_id_isSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __ID_ISSET_ID, value);
  }

  public int get_gpu_util() {
    return this.gpu_util;
  }

  public void set_gpu_util(int gpu_util) {
    this.gpu_util = gpu_util;
    set_gpu_util_isSet(true);
  }

  public void unset_gpu_util() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __GPU_UTIL_ISSET_ID);
  }

  /** Returns true if field gpu_util is set (has been assigned a value) and false otherwise */
  public boolean is_set_gpu_util() {
    return EncodingUtils.testBit(__isset_bitfield, __GPU_UTIL_ISSET_ID);
  }

  public void set_gpu_util_isSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __GPU_UTIL_ISSET_ID, value);
  }

  public long get_total_mem() {
    return this.total_mem;
  }

  public void set_total_mem(long total_mem) {
    this.total_mem = total_mem;
    set_total_mem_isSet(true);
  }

  public void unset_total_mem() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __TOTAL_MEM_ISSET_ID);
  }

  /** Returns true if field total_mem is set (has been assigned a value) and false otherwise */
  public boolean is_set_total_mem() {
    return EncodingUtils.testBit(__isset_bitfield, __TOTAL_MEM_ISSET_ID);
  }

  public void set_total_mem_isSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __TOTAL_MEM_ISSET_ID, value);
  }

  public long get_used_mem() {
    return this.used_mem;
  }

  public void set_used_mem(long used_mem) {
    this.used_mem = used_mem;
    set_used_mem_isSet(true);
  }

  public void unset_used_mem() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __USED_MEM_ISSET_ID);
  }

  /** Returns true if field used_mem is set (has been assigned a value) and false otherwise */
  public boolean is_set_used_mem() {
    return EncodingUtils.testBit(__isset_bitfield, __USED_MEM_ISSET_ID);
  }

  public void set_used_mem_isSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __USED_MEM_ISSET_ID, value);
  }

  public int get_process_count() {
    return this.process_count;
  }

  public void set_process_count(int process_count) {
    this.process_count = process_count;
    set_process_count_isSet(true);
  }

  public void unset_process_count() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __PROCESS_COUNT_ISSET_ID);
  }

  /** Returns true if field process_count is set (has been assigned a value) and false otherwise */
  public boolean is_set_process_count() {
    return EncodingUtils.testBit(__isset_bitfield, __PROCESS_COUNT_ISSET_ID);
  }

  public void set_process_count_isSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __PROCESS_COUNT_ISSET_ID, value);
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case ID:
      if (value == null) {
        unset_id();
      } else {
        set_id((Integer)value);
      }
      break;

    case GPU_UTIL:
      if (value == null) {
        unset_gpu_util();
      } else {
        set_gpu_util((Integer)value);
      }
      break;

    case TOTAL_MEM:
      if (value == null) {
        unset_total_mem();
      } else {
        set_total_mem((Long)value);
      }
      break;

    case USED_MEM:
      if (value == null) {
        unset_used_mem();
      } else {
        set_used_mem((Long)value);
      }
      break;

    case PROCESS_COUNT:
      if (value == null) {
        unset_process_count();
      } else {
        set_process_count((Integer)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case ID:
      return get_id();

    case GPU_UTIL:
      return get_gpu_util();

    case TOTAL_MEM:
      return get_total_mem();

    case USED_MEM:
      return get_used_mem();

    case PROCESS_COUNT:
      return get_process_count();

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case ID:
      return is_set_id();
    case GPU_UTIL:
      return is_set_gpu_util();
    case TOTAL_MEM:
      return is_set_total_mem();
    case USED_MEM:
      return is_set_used_mem();
    case PROCESS_COUNT:
      return is_set_process_count();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof GpuUtilizationInfo)
      return this.equals((GpuUtilizationInfo)that);
    return false;
  }

  public boolean equals(GpuUtilizationInfo that) {
    if (that == null)
      return false;

    boolean this_present_id = true;
    boolean that_present_id = true;
    if (this_present_id || that_present_id) {
      if (!(this_present_id && that_present_id))
        return false;
      if (this.id != that.id)
        return false;
    }

    boolean this_present_gpu_util = true;
    boolean that_present_gpu_util = true;
    if (this_present_gpu_util || that_present_gpu_util) {
      if (!(this_present_gpu_util && that_present_gpu_util))
        return false;
      if (this.gpu_util != that.gpu_util)
        return false;
    }

    boolean this_present_total_mem = true;
    boolean that_present_total_mem = true;
    if (this_present_total_mem || that_present_total_mem) {
      if (!(this_present_total_mem && that_present_total_mem))
        return false;
      if (this.total_mem != that.total_mem)
        return false;
    }

    boolean this_present_used_mem = true;
    boolean that_present_used_mem = true;
    if (this_present_used_mem || that_present_used_mem) {
      if (!(this_present_used_mem && that_present_used_mem))
        return false;
      if (this.used_mem != that.used_mem)
        return false;
    }

    boolean this_present_process_count = true;
    boolean that_present_process_count = true;
    if (this_present_process_count || that_present_process_count) {
      if (!(this_present_process_count && that_present_process_count))
        return false;
      if (this.process_count != that.process_count)
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    List<Object> list = new ArrayList<Object>();

    boolean present_id = true;
    list.add(present_id);
    if (present_id)
      list.add(id);

    boolean present_gpu_util = true;
    list.add(present_gpu_util);
    if (present_gpu_util)
      list.add(gpu_util);

    boolean present_total_mem = true;
    list.add(present_total_mem);
    if (present_total_mem)
      list.add(total_mem);

    boolean present_used_mem = true;
    list.add(present_used_mem);
    if (present_used_mem)
      list.add(used_mem);

    boolean present_process_count = true;
    list.add(present_process_count);
    if (present_process_count)
      list.add(process_count);

    return list.hashCode();
  }

  @Override
  public int compareTo(GpuUtilizationInfo other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = Boolean.valueOf(is_set_id()).compareTo(other.is_set_id());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (is_set_id()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.id, other.id);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(is_set_gpu_util()).compareTo(other.is_set_gpu_util());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (is_set_gpu_util()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.gpu_util, other.gpu_util);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(is_set_total_mem()).compareTo(other.is_set_total_mem());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (is_set_total_mem()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.total_mem, other.total_mem);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(is_set_used_mem()).compareTo(other.is_set_used_mem());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (is_set_used_mem()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.used_mem, other.used_mem);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(is_set_process_count()).compareTo(other.is_set_process_count());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (is_set_process_count()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.process_count, other.process_count);
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
    StringBuilder sb = new StringBuilder("GpuUtilizationInfo(");
    boolean first = true;

    sb.append("id:");
    sb.append(this.id);
    first = false;
    if (!first) sb.append(", ");
    sb.append("gpu_util:");
    sb.append(this.gpu_util);
    first = false;
    if (!first) sb.append(", ");
    sb.append("total_mem:");
    sb.append(this.total_mem);
    first = false;
    if (!first) sb.append(", ");
    sb.append("used_mem:");
    sb.append(this.used_mem);
    first = false;
    if (!first) sb.append(", ");
    sb.append("process_count:");
    sb.append(this.process_count);
    first = false;
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    if (!is_set_id()) {
      throw new org.apache.thrift.protocol.TProtocolException("Required field 'id' is unset! Struct:" + toString());
    }

    if (!is_set_gpu_util()) {
      throw new org.apache.thrift.protocol.TProtocolException("Required field 'gpu_util' is unset! Struct:" + toString());
    }

    if (!is_set_total_mem()) {
      throw new org.apache.thrift.protocol.TProtocolException("Required field 'total_mem' is unset! Struct:" + toString());
    }

    if (!is_set_used_mem()) {
      throw new org.apache.thrift.protocol.TProtocolException("Required field 'used_mem' is unset! Struct:" + toString());
    }

    if (!is_set_process_count()) {
      throw new org.apache.thrift.protocol.TProtocolException("Required field 'process_count' is unset! Struct:" + toString());
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

  private static class GpuUtilizationInfoStandardSchemeFactory implements SchemeFactory {
    public GpuUtilizationInfoStandardScheme getScheme() {
      return new GpuUtilizationInfoStandardScheme();
    }
  }

  private static class GpuUtilizationInfoStandardScheme extends StandardScheme<GpuUtilizationInfo> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, GpuUtilizationInfo struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // ID
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.id = iprot.readI32();
              struct.set_id_isSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // GPU_UTIL
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.gpu_util = iprot.readI32();
              struct.set_gpu_util_isSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // TOTAL_MEM
            if (schemeField.type == org.apache.thrift.protocol.TType.I64) {
              struct.total_mem = iprot.readI64();
              struct.set_total_mem_isSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 4: // USED_MEM
            if (schemeField.type == org.apache.thrift.protocol.TType.I64) {
              struct.used_mem = iprot.readI64();
              struct.set_used_mem_isSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 5: // PROCESS_COUNT
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.process_count = iprot.readI32();
              struct.set_process_count_isSet(true);
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

    public void write(org.apache.thrift.protocol.TProtocol oprot, GpuUtilizationInfo struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      oprot.writeFieldBegin(ID_FIELD_DESC);
      oprot.writeI32(struct.id);
      oprot.writeFieldEnd();
      oprot.writeFieldBegin(GPU_UTIL_FIELD_DESC);
      oprot.writeI32(struct.gpu_util);
      oprot.writeFieldEnd();
      oprot.writeFieldBegin(TOTAL_MEM_FIELD_DESC);
      oprot.writeI64(struct.total_mem);
      oprot.writeFieldEnd();
      oprot.writeFieldBegin(USED_MEM_FIELD_DESC);
      oprot.writeI64(struct.used_mem);
      oprot.writeFieldEnd();
      oprot.writeFieldBegin(PROCESS_COUNT_FIELD_DESC);
      oprot.writeI32(struct.process_count);
      oprot.writeFieldEnd();
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class GpuUtilizationInfoTupleSchemeFactory implements SchemeFactory {
    public GpuUtilizationInfoTupleScheme getScheme() {
      return new GpuUtilizationInfoTupleScheme();
    }
  }

  private static class GpuUtilizationInfoTupleScheme extends TupleScheme<GpuUtilizationInfo> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, GpuUtilizationInfo struct) throws org.apache.thrift.TException {
      TTupleProtocol oprot = (TTupleProtocol) prot;
      oprot.writeI32(struct.id);
      oprot.writeI32(struct.gpu_util);
      oprot.writeI64(struct.total_mem);
      oprot.writeI64(struct.used_mem);
      oprot.writeI32(struct.process_count);
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, GpuUtilizationInfo struct) throws org.apache.thrift.TException {
      TTupleProtocol iprot = (TTupleProtocol) prot;
      struct.id = iprot.readI32();
      struct.set_id_isSet(true);
      struct.gpu_util = iprot.readI32();
      struct.set_gpu_util_isSet(true);
      struct.total_mem = iprot.readI64();
      struct.set_total_mem_isSet(true);
      struct.used_mem = iprot.readI64();
      struct.set_used_mem_isSet(true);
      struct.process_count = iprot.readI32();
      struct.set_process_count_isSet(true);
    }
  }

}
