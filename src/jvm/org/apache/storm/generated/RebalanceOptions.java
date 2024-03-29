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
public class RebalanceOptions implements org.apache.thrift.TBase<RebalanceOptions, RebalanceOptions._Fields>, java.io.Serializable, Cloneable, Comparable<RebalanceOptions> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("RebalanceOptions");

  private static final org.apache.thrift.protocol.TField WAIT_SECS_FIELD_DESC = new org.apache.thrift.protocol.TField("wait_secs", org.apache.thrift.protocol.TType.I32, (short)1);
  private static final org.apache.thrift.protocol.TField NUM_WORKERS_FIELD_DESC = new org.apache.thrift.protocol.TField("num_workers", org.apache.thrift.protocol.TType.I32, (short)2);
  private static final org.apache.thrift.protocol.TField NUM_EXECUTORS_FIELD_DESC = new org.apache.thrift.protocol.TField("num_executors", org.apache.thrift.protocol.TType.MAP, (short)3);

  private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
  static {
    schemes.put(StandardScheme.class, new RebalanceOptionsStandardSchemeFactory());
    schemes.put(TupleScheme.class, new RebalanceOptionsTupleSchemeFactory());
  }

  private int wait_secs; // optional
  private int num_workers; // optional
  private Map<String,Integer> num_executors; // optional

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    WAIT_SECS((short)1, "wait_secs"),
    NUM_WORKERS((short)2, "num_workers"),
    NUM_EXECUTORS((short)3, "num_executors");

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
        case 1: // WAIT_SECS
          return WAIT_SECS;
        case 2: // NUM_WORKERS
          return NUM_WORKERS;
        case 3: // NUM_EXECUTORS
          return NUM_EXECUTORS;
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
  private static final int __WAIT_SECS_ISSET_ID = 0;
  private static final int __NUM_WORKERS_ISSET_ID = 1;
  private byte __isset_bitfield = 0;
  private static final _Fields optionals[] = {_Fields.WAIT_SECS,_Fields.NUM_WORKERS,_Fields.NUM_EXECUTORS};
  public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.WAIT_SECS, new org.apache.thrift.meta_data.FieldMetaData("wait_secs", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32)));
    tmpMap.put(_Fields.NUM_WORKERS, new org.apache.thrift.meta_data.FieldMetaData("num_workers", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32)));
    tmpMap.put(_Fields.NUM_EXECUTORS, new org.apache.thrift.meta_data.FieldMetaData("num_executors", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.MapMetaData(org.apache.thrift.protocol.TType.MAP, 
            new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING), 
            new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32))));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(RebalanceOptions.class, metaDataMap);
  }

  public RebalanceOptions() {
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public RebalanceOptions(RebalanceOptions other) {
    __isset_bitfield = other.__isset_bitfield;
    this.wait_secs = other.wait_secs;
    this.num_workers = other.num_workers;
    if (other.is_set_num_executors()) {
      Map<String,Integer> __this__num_executors = new HashMap<String,Integer>(other.num_executors);
      this.num_executors = __this__num_executors;
    }
  }

  public RebalanceOptions deepCopy() {
    return new RebalanceOptions(this);
  }

  @Override
  public void clear() {
    set_wait_secs_isSet(false);
    this.wait_secs = 0;
    set_num_workers_isSet(false);
    this.num_workers = 0;
    this.num_executors = null;
  }

  public int get_wait_secs() {
    return this.wait_secs;
  }

  public void set_wait_secs(int wait_secs) {
    this.wait_secs = wait_secs;
    set_wait_secs_isSet(true);
  }

  public void unset_wait_secs() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __WAIT_SECS_ISSET_ID);
  }

  /** Returns true if field wait_secs is set (has been assigned a value) and false otherwise */
  public boolean is_set_wait_secs() {
    return EncodingUtils.testBit(__isset_bitfield, __WAIT_SECS_ISSET_ID);
  }

  public void set_wait_secs_isSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __WAIT_SECS_ISSET_ID, value);
  }

  public int get_num_workers() {
    return this.num_workers;
  }

  public void set_num_workers(int num_workers) {
    this.num_workers = num_workers;
    set_num_workers_isSet(true);
  }

  public void unset_num_workers() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __NUM_WORKERS_ISSET_ID);
  }

  /** Returns true if field num_workers is set (has been assigned a value) and false otherwise */
  public boolean is_set_num_workers() {
    return EncodingUtils.testBit(__isset_bitfield, __NUM_WORKERS_ISSET_ID);
  }

  public void set_num_workers_isSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __NUM_WORKERS_ISSET_ID, value);
  }

  public int get_num_executors_size() {
    return (this.num_executors == null) ? 0 : this.num_executors.size();
  }

  public void put_to_num_executors(String key, int val) {
    if (this.num_executors == null) {
      this.num_executors = new HashMap<String,Integer>();
    }
    this.num_executors.put(key, val);
  }

  public Map<String,Integer> get_num_executors() {
    return this.num_executors;
  }

  public void set_num_executors(Map<String,Integer> num_executors) {
    this.num_executors = num_executors;
  }

  public void unset_num_executors() {
    this.num_executors = null;
  }

  /** Returns true if field num_executors is set (has been assigned a value) and false otherwise */
  public boolean is_set_num_executors() {
    return this.num_executors != null;
  }

  public void set_num_executors_isSet(boolean value) {
    if (!value) {
      this.num_executors = null;
    }
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case WAIT_SECS:
      if (value == null) {
        unset_wait_secs();
      } else {
        set_wait_secs((Integer)value);
      }
      break;

    case NUM_WORKERS:
      if (value == null) {
        unset_num_workers();
      } else {
        set_num_workers((Integer)value);
      }
      break;

    case NUM_EXECUTORS:
      if (value == null) {
        unset_num_executors();
      } else {
        set_num_executors((Map<String,Integer>)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case WAIT_SECS:
      return get_wait_secs();

    case NUM_WORKERS:
      return get_num_workers();

    case NUM_EXECUTORS:
      return get_num_executors();

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case WAIT_SECS:
      return is_set_wait_secs();
    case NUM_WORKERS:
      return is_set_num_workers();
    case NUM_EXECUTORS:
      return is_set_num_executors();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof RebalanceOptions)
      return this.equals((RebalanceOptions)that);
    return false;
  }

  public boolean equals(RebalanceOptions that) {
    if (that == null)
      return false;

    boolean this_present_wait_secs = true && this.is_set_wait_secs();
    boolean that_present_wait_secs = true && that.is_set_wait_secs();
    if (this_present_wait_secs || that_present_wait_secs) {
      if (!(this_present_wait_secs && that_present_wait_secs))
        return false;
      if (this.wait_secs != that.wait_secs)
        return false;
    }

    boolean this_present_num_workers = true && this.is_set_num_workers();
    boolean that_present_num_workers = true && that.is_set_num_workers();
    if (this_present_num_workers || that_present_num_workers) {
      if (!(this_present_num_workers && that_present_num_workers))
        return false;
      if (this.num_workers != that.num_workers)
        return false;
    }

    boolean this_present_num_executors = true && this.is_set_num_executors();
    boolean that_present_num_executors = true && that.is_set_num_executors();
    if (this_present_num_executors || that_present_num_executors) {
      if (!(this_present_num_executors && that_present_num_executors))
        return false;
      if (!this.num_executors.equals(that.num_executors))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    List<Object> list = new ArrayList<Object>();

    boolean present_wait_secs = true && (is_set_wait_secs());
    list.add(present_wait_secs);
    if (present_wait_secs)
      list.add(wait_secs);

    boolean present_num_workers = true && (is_set_num_workers());
    list.add(present_num_workers);
    if (present_num_workers)
      list.add(num_workers);

    boolean present_num_executors = true && (is_set_num_executors());
    list.add(present_num_executors);
    if (present_num_executors)
      list.add(num_executors);

    return list.hashCode();
  }

  @Override
  public int compareTo(RebalanceOptions other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = Boolean.valueOf(is_set_wait_secs()).compareTo(other.is_set_wait_secs());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (is_set_wait_secs()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.wait_secs, other.wait_secs);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(is_set_num_workers()).compareTo(other.is_set_num_workers());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (is_set_num_workers()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.num_workers, other.num_workers);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(is_set_num_executors()).compareTo(other.is_set_num_executors());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (is_set_num_executors()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.num_executors, other.num_executors);
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
    StringBuilder sb = new StringBuilder("RebalanceOptions(");
    boolean first = true;

    if (is_set_wait_secs()) {
      sb.append("wait_secs:");
      sb.append(this.wait_secs);
      first = false;
    }
    if (is_set_num_workers()) {
      if (!first) sb.append(", ");
      sb.append("num_workers:");
      sb.append(this.num_workers);
      first = false;
    }
    if (is_set_num_executors()) {
      if (!first) sb.append(", ");
      sb.append("num_executors:");
      if (this.num_executors == null) {
        sb.append("null");
      } else {
        sb.append(this.num_executors);
      }
      first = false;
    }
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
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

  private static class RebalanceOptionsStandardSchemeFactory implements SchemeFactory {
    public RebalanceOptionsStandardScheme getScheme() {
      return new RebalanceOptionsStandardScheme();
    }
  }

  private static class RebalanceOptionsStandardScheme extends StandardScheme<RebalanceOptions> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, RebalanceOptions struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // WAIT_SECS
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.wait_secs = iprot.readI32();
              struct.set_wait_secs_isSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // NUM_WORKERS
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.num_workers = iprot.readI32();
              struct.set_num_workers_isSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // NUM_EXECUTORS
            if (schemeField.type == org.apache.thrift.protocol.TType.MAP) {
              {
                org.apache.thrift.protocol.TMap _map494 = iprot.readMapBegin();
                struct.num_executors = new HashMap<String,Integer>(2*_map494.size);
                String _key495;
                int _val496;
                for (int _i497 = 0; _i497 < _map494.size; ++_i497)
                {
                  _key495 = iprot.readString();
                  _val496 = iprot.readI32();
                  struct.num_executors.put(_key495, _val496);
                }
                iprot.readMapEnd();
              }
              struct.set_num_executors_isSet(true);
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

    public void write(org.apache.thrift.protocol.TProtocol oprot, RebalanceOptions struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.is_set_wait_secs()) {
        oprot.writeFieldBegin(WAIT_SECS_FIELD_DESC);
        oprot.writeI32(struct.wait_secs);
        oprot.writeFieldEnd();
      }
      if (struct.is_set_num_workers()) {
        oprot.writeFieldBegin(NUM_WORKERS_FIELD_DESC);
        oprot.writeI32(struct.num_workers);
        oprot.writeFieldEnd();
      }
      if (struct.num_executors != null) {
        if (struct.is_set_num_executors()) {
          oprot.writeFieldBegin(NUM_EXECUTORS_FIELD_DESC);
          {
            oprot.writeMapBegin(new org.apache.thrift.protocol.TMap(org.apache.thrift.protocol.TType.STRING, org.apache.thrift.protocol.TType.I32, struct.num_executors.size()));
            for (Map.Entry<String, Integer> _iter498 : struct.num_executors.entrySet())
            {
              oprot.writeString(_iter498.getKey());
              oprot.writeI32(_iter498.getValue());
            }
            oprot.writeMapEnd();
          }
          oprot.writeFieldEnd();
        }
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class RebalanceOptionsTupleSchemeFactory implements SchemeFactory {
    public RebalanceOptionsTupleScheme getScheme() {
      return new RebalanceOptionsTupleScheme();
    }
  }

  private static class RebalanceOptionsTupleScheme extends TupleScheme<RebalanceOptions> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, RebalanceOptions struct) throws org.apache.thrift.TException {
      TTupleProtocol oprot = (TTupleProtocol) prot;
      BitSet optionals = new BitSet();
      if (struct.is_set_wait_secs()) {
        optionals.set(0);
      }
      if (struct.is_set_num_workers()) {
        optionals.set(1);
      }
      if (struct.is_set_num_executors()) {
        optionals.set(2);
      }
      oprot.writeBitSet(optionals, 3);
      if (struct.is_set_wait_secs()) {
        oprot.writeI32(struct.wait_secs);
      }
      if (struct.is_set_num_workers()) {
        oprot.writeI32(struct.num_workers);
      }
      if (struct.is_set_num_executors()) {
        {
          oprot.writeI32(struct.num_executors.size());
          for (Map.Entry<String, Integer> _iter499 : struct.num_executors.entrySet())
          {
            oprot.writeString(_iter499.getKey());
            oprot.writeI32(_iter499.getValue());
          }
        }
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, RebalanceOptions struct) throws org.apache.thrift.TException {
      TTupleProtocol iprot = (TTupleProtocol) prot;
      BitSet incoming = iprot.readBitSet(3);
      if (incoming.get(0)) {
        struct.wait_secs = iprot.readI32();
        struct.set_wait_secs_isSet(true);
      }
      if (incoming.get(1)) {
        struct.num_workers = iprot.readI32();
        struct.set_num_workers_isSet(true);
      }
      if (incoming.get(2)) {
        {
          org.apache.thrift.protocol.TMap _map500 = new org.apache.thrift.protocol.TMap(org.apache.thrift.protocol.TType.STRING, org.apache.thrift.protocol.TType.I32, iprot.readI32());
          struct.num_executors = new HashMap<String,Integer>(2*_map500.size);
          String _key501;
          int _val502;
          for (int _i503 = 0; _i503 < _map500.size; ++_i503)
          {
            _key501 = iprot.readString();
            _val502 = iprot.readI32();
            struct.num_executors.put(_key501, _val502);
          }
        }
        struct.set_num_executors_isSet(true);
      }
    }
  }

}

