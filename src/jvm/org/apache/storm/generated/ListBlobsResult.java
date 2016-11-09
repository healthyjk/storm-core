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
public class ListBlobsResult implements org.apache.thrift.TBase<ListBlobsResult, ListBlobsResult._Fields>, java.io.Serializable, Cloneable, Comparable<ListBlobsResult> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("ListBlobsResult");

  private static final org.apache.thrift.protocol.TField KEYS_FIELD_DESC = new org.apache.thrift.protocol.TField("keys", org.apache.thrift.protocol.TType.LIST, (short)1);
  private static final org.apache.thrift.protocol.TField SESSION_FIELD_DESC = new org.apache.thrift.protocol.TField("session", org.apache.thrift.protocol.TType.STRING, (short)2);

  private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
  static {
    schemes.put(StandardScheme.class, new ListBlobsResultStandardSchemeFactory());
    schemes.put(TupleScheme.class, new ListBlobsResultTupleSchemeFactory());
  }

  private List<String> keys; // required
  private String session; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    KEYS((short)1, "keys"),
    SESSION((short)2, "session");

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
        case 1: // KEYS
          return KEYS;
        case 2: // SESSION
          return SESSION;
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
  public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.KEYS, new org.apache.thrift.meta_data.FieldMetaData("keys", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.ListMetaData(org.apache.thrift.protocol.TType.LIST, 
            new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING))));
    tmpMap.put(_Fields.SESSION, new org.apache.thrift.meta_data.FieldMetaData("session", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(ListBlobsResult.class, metaDataMap);
  }

  public ListBlobsResult() {
  }

  public ListBlobsResult(
    List<String> keys,
    String session)
  {
    this();
    this.keys = keys;
    this.session = session;
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public ListBlobsResult(ListBlobsResult other) {
    if (other.is_set_keys()) {
      List<String> __this__keys = new ArrayList<String>(other.keys);
      this.keys = __this__keys;
    }
    if (other.is_set_session()) {
      this.session = other.session;
    }
  }

  public ListBlobsResult deepCopy() {
    return new ListBlobsResult(this);
  }

  @Override
  public void clear() {
    this.keys = null;
    this.session = null;
  }

  public int get_keys_size() {
    return (this.keys == null) ? 0 : this.keys.size();
  }

  public java.util.Iterator<String> get_keys_iterator() {
    return (this.keys == null) ? null : this.keys.iterator();
  }

  public void add_to_keys(String elem) {
    if (this.keys == null) {
      this.keys = new ArrayList<String>();
    }
    this.keys.add(elem);
  }

  public List<String> get_keys() {
    return this.keys;
  }

  public void set_keys(List<String> keys) {
    this.keys = keys;
  }

  public void unset_keys() {
    this.keys = null;
  }

  /** Returns true if field keys is set (has been assigned a value) and false otherwise */
  public boolean is_set_keys() {
    return this.keys != null;
  }

  public void set_keys_isSet(boolean value) {
    if (!value) {
      this.keys = null;
    }
  }

  public String get_session() {
    return this.session;
  }

  public void set_session(String session) {
    this.session = session;
  }

  public void unset_session() {
    this.session = null;
  }

  /** Returns true if field session is set (has been assigned a value) and false otherwise */
  public boolean is_set_session() {
    return this.session != null;
  }

  public void set_session_isSet(boolean value) {
    if (!value) {
      this.session = null;
    }
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case KEYS:
      if (value == null) {
        unset_keys();
      } else {
        set_keys((List<String>)value);
      }
      break;

    case SESSION:
      if (value == null) {
        unset_session();
      } else {
        set_session((String)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case KEYS:
      return get_keys();

    case SESSION:
      return get_session();

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case KEYS:
      return is_set_keys();
    case SESSION:
      return is_set_session();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof ListBlobsResult)
      return this.equals((ListBlobsResult)that);
    return false;
  }

  public boolean equals(ListBlobsResult that) {
    if (that == null)
      return false;

    boolean this_present_keys = true && this.is_set_keys();
    boolean that_present_keys = true && that.is_set_keys();
    if (this_present_keys || that_present_keys) {
      if (!(this_present_keys && that_present_keys))
        return false;
      if (!this.keys.equals(that.keys))
        return false;
    }

    boolean this_present_session = true && this.is_set_session();
    boolean that_present_session = true && that.is_set_session();
    if (this_present_session || that_present_session) {
      if (!(this_present_session && that_present_session))
        return false;
      if (!this.session.equals(that.session))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    List<Object> list = new ArrayList<Object>();

    boolean present_keys = true && (is_set_keys());
    list.add(present_keys);
    if (present_keys)
      list.add(keys);

    boolean present_session = true && (is_set_session());
    list.add(present_session);
    if (present_session)
      list.add(session);

    return list.hashCode();
  }

  @Override
  public int compareTo(ListBlobsResult other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = Boolean.valueOf(is_set_keys()).compareTo(other.is_set_keys());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (is_set_keys()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.keys, other.keys);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(is_set_session()).compareTo(other.is_set_session());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (is_set_session()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.session, other.session);
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
    StringBuilder sb = new StringBuilder("ListBlobsResult(");
    boolean first = true;

    sb.append("keys:");
    if (this.keys == null) {
      sb.append("null");
    } else {
      sb.append(this.keys);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("session:");
    if (this.session == null) {
      sb.append("null");
    } else {
      sb.append(this.session);
    }
    first = false;
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    if (!is_set_keys()) {
      throw new org.apache.thrift.protocol.TProtocolException("Required field 'keys' is unset! Struct:" + toString());
    }

    if (!is_set_session()) {
      throw new org.apache.thrift.protocol.TProtocolException("Required field 'session' is unset! Struct:" + toString());
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
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class ListBlobsResultStandardSchemeFactory implements SchemeFactory {
    public ListBlobsResultStandardScheme getScheme() {
      return new ListBlobsResultStandardScheme();
    }
  }

  private static class ListBlobsResultStandardScheme extends StandardScheme<ListBlobsResult> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, ListBlobsResult struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // KEYS
            if (schemeField.type == org.apache.thrift.protocol.TType.LIST) {
              {
                org.apache.thrift.protocol.TList _list506 = iprot.readListBegin();
                struct.keys = new ArrayList<String>(_list506.size);
                String _elem507;
                for (int _i508 = 0; _i508 < _list506.size; ++_i508)
                {
                  _elem507 = iprot.readString();
                  struct.keys.add(_elem507);
                }
                iprot.readListEnd();
              }
              struct.set_keys_isSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // SESSION
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.session = iprot.readString();
              struct.set_session_isSet(true);
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

    public void write(org.apache.thrift.protocol.TProtocol oprot, ListBlobsResult struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.keys != null) {
        oprot.writeFieldBegin(KEYS_FIELD_DESC);
        {
          oprot.writeListBegin(new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRING, struct.keys.size()));
          for (String _iter509 : struct.keys)
          {
            oprot.writeString(_iter509);
          }
          oprot.writeListEnd();
        }
        oprot.writeFieldEnd();
      }
      if (struct.session != null) {
        oprot.writeFieldBegin(SESSION_FIELD_DESC);
        oprot.writeString(struct.session);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class ListBlobsResultTupleSchemeFactory implements SchemeFactory {
    public ListBlobsResultTupleScheme getScheme() {
      return new ListBlobsResultTupleScheme();
    }
  }

  private static class ListBlobsResultTupleScheme extends TupleScheme<ListBlobsResult> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, ListBlobsResult struct) throws org.apache.thrift.TException {
      TTupleProtocol oprot = (TTupleProtocol) prot;
      {
        oprot.writeI32(struct.keys.size());
        for (String _iter510 : struct.keys)
        {
          oprot.writeString(_iter510);
        }
      }
      oprot.writeString(struct.session);
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, ListBlobsResult struct) throws org.apache.thrift.TException {
      TTupleProtocol iprot = (TTupleProtocol) prot;
      {
        org.apache.thrift.protocol.TList _list511 = new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRING, iprot.readI32());
        struct.keys = new ArrayList<String>(_list511.size);
        String _elem512;
        for (int _i513 = 0; _i513 < _list511.size; ++_i513)
        {
          _elem512 = iprot.readString();
          struct.keys.add(_elem512);
        }
      }
      struct.set_keys_isSet(true);
      struct.session = iprot.readString();
      struct.set_session_isSet(true);
    }
  }

}

