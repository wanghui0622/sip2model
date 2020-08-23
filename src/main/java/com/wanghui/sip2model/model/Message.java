package com.wanghui.sip2model.model;

import com.wanghui.sip2model.annotations.*;
import com.wanghui.sip2model.exceptions.*;
import com.wanghui.sip2model.fields.*;
import com.wanghui.sip2model.types.enumerations.AbstractEnumeration;
import com.wanghui.sip2model.types.flagfields.AbstractFlagField;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Assert;
import org.junit.Test;

import java.beans.PropertyDescriptor;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 *
 * @auther wanghui
 * @create 2018-11-13 0:36
 * @Description
 */
public abstract class Message implements Serializable {

    private static final long serialVersionUID = 1609258005567594730L;
    public static final String PROP_CHARSET = "com.wanghui.sip2server.charset";
    public static final String PROP_AUTOPOPULATE = "com.wanghui.sip2model.AutoPopulationEmptyRequiredFields";
    public static final String PROP_VARIABLE_FIELD_ORDERING = "com.wanghui.sip2server.VariableFieldOrdering";
    public static final String PROP_AUTOPOPULATE_OFF = "off";
    public static final String PROP_AUTOPOPULATE_DECODE = "decode";
    public static final String PROP_AUTOPOPULATE_ENCODE = "encode";
    public static final String PROP_AUTOPOPULATE_BIDIRECTIONAL = "bidirectional";
    public static final String PROP_AUTOPOPULATE_DEFAULT = PROP_AUTOPOPULATE_BIDIRECTIONAL;
    public static final String PROP_VARIABLE_FIELD_ORDERING_ALPHABETICAL = "alphabetical";
    public static final String PROP_VARIABLE_FIELD_ORDERING_SPECIFICATION = "specification";
    public static final String PROP_VARIABLE_FIELD_ORDERING_DEFAULT =  PROP_VARIABLE_FIELD_ORDERING_ALPHABETICAL;

    private static final String PROP_DEFAULT_CHARSET = "UTF-8";

    private static Log log = LogFactory.getLog(Message.class);

    private Character SequenceCharacter = null;

    /**
     * 设置字符序列
     * @param sequenceCharacter
     */
    public void setSequenceCharacter(Character sequenceCharacter) {
    	this.SequenceCharacter = sequenceCharacter;
    }

    /**
     * 获取字符序列
     * @return
     */
    public Character getSequenceCharacter() {
        return this.SequenceCharacter;
    }

    /**
     * 获得编码格式
     * @return
     */
    public static String getCharsetEncoding() {
        //获取指定键指示的系统属性
        return System.getProperty(PROP_CHARSET, PROP_DEFAULT_CHARSET);      
    }

    /**
     * 将日期转化为SIP2中规定的日期格式
     * @param date
     * @return
     */
    private String mangleDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd    HHmmss");
        return sdf.format(date);
    }

    /**
     * 将SIP2中的日期格式转化为日期类型的日期
     * @param date
     * @return
     */
    private Date demangleDate(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd    HHmmss");
        try {
            return sdf.parse(date);
        } catch (Exception ex) {
            return null;
        }
    }

    /**
     *
     * @param desc          PropertyDescriptor描述了Java Bean通过一对访问器方法导出的一个属性。
     * @param SIPField      SIP2定义字段
     * @param autoPop      是否自动获取属性
     * @return
     * @throws MandatoryFieldOmitted
     */
    private String[] getProp(PropertyDescriptor desc, FieldDefinition SIPField, boolean autoPop) throws MandatoryFieldOmitted {
        String[] ret = null;
        try {
            //获取用于读取属性值的方法。无法读取属性，则可能返回null。
            Method read = desc.getReadMethod();
            Object value = null;
            //根据可以获取到读取属性的方法，进行不同的业务逻辑
            if (read != null) {
                value = read.invoke(this, new Object[0]);
            } else {
                //如果是Boolean类型的话，无法通过getReadMethod()方法获取Boolean类型变量的isxxx()方法，
                //当判断是Boolean类型的时候，需要通过java.lang.Class<T>的getMethod（String)获取读取Boolean属性的Method。
            if (desc.getPropertyType() == Boolean.class) {
                    read = this.getClass().getMethod("is" + desc.getName().substring(0, 1).toUpperCase() + desc.getName().substring(1), new Class[]{});
                    if (read != null) {
                        value = read.invoke(this, new Object[0]);                        
                    }
                }
            }
            //根据属性值类型的不同，对属性进行封装

            /*
            Boolean类型属性对应SIP2中规定的值:
            magnetic media		1-char, fixed-length required field: Y or N or U.
            desensitize		    1-char, fixed-length required field: Y or N or U.
            ok		            1-char, fixed-length required field: 0 or 1.
            renewal ok		    1-char, fixed-length required field: Y or N.
            security inhibit	1-char, fixed-length optional field: Y or N.
             */
            if (desc.getPropertyType() == Boolean.class) {
                if (value == null) {
                    if (SIPField != null) {
                        if (SIPField.policy != null) {
                            if (SIPField.policy == FieldPolicy.REQUIRED) {
                                if (desc.getName().equalsIgnoreCase("magneticMedia")) {
                                    ret = new String[] { "U" };
                                } else {
                                    if (!autoPop) {
                                        throw new MandatoryFieldOmitted(desc.getDisplayName());
                                    }
                                    if (desc.getName().equalsIgnoreCase("ok")) {
                                        ret = new String[] { "0" };
                                    } else {
                                        ret = new String[] { "N" };
                                    }
                                }
                            }
                        }
                    }
                } else if (desc.getName().equalsIgnoreCase("ok")) {
                    ret = new String[] { ((Boolean) value).booleanValue() ? "1" : "0" };
                } else {
                    ret = new String[] { ((Boolean) value).booleanValue() ? "Y" : "N" };
                }
            } else if (desc.getPropertyType() == Date.class) {       //Date类型属性值
                if (value != null) {
                    ret = new String[] { this.mangleDate((Date) value) };
                } else {
                    if (SIPField != null) {
                        if (SIPField.policy != null) {
                            if (SIPField.policy == FieldPolicy.REQUIRED) {
                                if (!autoPop) {
                                    throw new MandatoryFieldOmitted(desc.getDisplayName());
                                }
                                ret = new String[] { this.mangleDate(new Date()) };
                            }
                        }
                    }
                }
            } else if (desc.getPropertyType() == String[].class) {       //String数组属性值
                if (value != null) {
                    ret = (String[]) value;
                }
            } else if (desc.getPropertyType() == Integer.class) {       //Integer类型
                if (value != null) {
                    if (SIPField.length != 0) {
                        ret = new String[] { String.format("%0" + SIPField.length + "d", value) };
                    } else {
                        ret = new String[] { value.toString() };
                    }
                } else {
                    if (SIPField != null) {
                        if (SIPField.policy != null) {
                            if (SIPField.policy == FieldPolicy.REQUIRED) {
                                if (!autoPop) {
                                    throw new MandatoryFieldOmitted(desc.getDisplayName());
                                }
                                if (SIPField.length != 0) {
                                    ret = new String[] { String.format("%0" + SIPField.length + "d", 0) };
                                } else {
                                    ret = new String[] { "0" };
                                }
                            }
                        }
                    }
                }
            } else {          //其他类型
                if (value != null) {
                    ret = new String[] { value.toString() };
                } else {
                    if (SIPField != null) {
                        if (SIPField.policy != null) {
                            if (SIPField.policy == FieldPolicy.REQUIRED) {
                                if (!autoPop) {
                                    throw new MandatoryFieldOmitted(desc.getDisplayName());
                                }
                                Class<?>[] interfaces = desc.getPropertyType().getInterfaces();
                                for (Class<?> interfce : interfaces) {
                                    if (interfce == AbstractEnumeration.class) {
                                        Method mthd = desc.getPropertyType().getDeclaredMethod("values",
                                                new Class[] {});
                                        Object[] values = (Object[]) mthd.invoke(null, new Object[] {});
                                        if (values.length > 0) {
                                            ret = new String[] { values[0].toString() };
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } catch (MandatoryFieldOmitted mfo) {
            throw mfo;
        } catch (Exception ex) {
            Message.log.error("Unexpected error getting " + desc.getDisplayName(), ex);
        }

        return (ret != null) ? ret : new String[] { "" };
    }

    private String pad(String input, PositionedFieldDefinition field) {
        StringBuffer ret = new StringBuffer();
        ret.append(input);

        while (ret.length() <= (field.end - field.start)) {
            ret.append(" ");
        }
        return ret.toString();
    }

    public String encode() throws MandatoryFieldOmitted, InvalidFieldLength, MessageNotUnderstood {
      return this.encode(this.getSequenceCharacter());
    }
    
    public String encode(boolean autoPop) throws MandatoryFieldOmitted, InvalidFieldLength, MessageNotUnderstood {
      return this.encode(this.getSequenceCharacter(), autoPop);
    }    
    
    public String encode(Character sequence) throws MandatoryFieldOmitted, InvalidFieldLength, MessageNotUnderstood {
        String pop = System.getProperty(Message.PROP_AUTOPOPULATE, PROP_AUTOPOPULATE_BIDIRECTIONAL);
        boolean autoPop = false;
        if (pop.equalsIgnoreCase(PROP_AUTOPOPULATE_ENCODE) || pop.equalsIgnoreCase(PROP_AUTOPOPULATE_DEFAULT)) {
            autoPop = true;
        }
        return encode(sequence, autoPop);
    }

    private String encode(Character sequence, boolean autoPop) throws MandatoryFieldOmitted, InvalidFieldLength, MessageNotUnderstood {
        Map<Integer, String> fixed = new TreeMap<Integer, String>();
        String order = System.getProperty(Message.PROP_VARIABLE_FIELD_ORDERING, PROP_VARIABLE_FIELD_ORDERING_DEFAULT);
        Map<String, String[]> variable;
        if (order.equalsIgnoreCase(Message.PROP_VARIABLE_FIELD_ORDERING_SPECIFICATION)) {
          variable = new LinkedHashMap<String, String[]>();
        } else {
          variable = new TreeMap<String, String[]>();         
        }
        StringBuffer message = new StringBuffer();

        Field[] fields = this.getClass().getDeclaredFields(); 

        for (Field fld : fields) {
          if (fld.isAnnotationPresent(PositionedField.class)) {
            PositionedField annotation = (PositionedField)fld.getAnnotation(PositionedField.class);               
            PositionedFieldDefinition field = Fields.getPositionedFieldDefinition(this.getClass().getName(), fld.getName(), annotation);
            PropertyDescriptor desc;
            try {
              desc = PropertyUtils.getPropertyDescriptor(this, fld.getName());
            } catch (Exception ex) {
              throw new AssertionError("Introspection problem during encoding for " + fld.getName() + " in " + this.getClass().getName());
            }
            if (desc == null) {
              throw new AssertionError("Introspection problem during encoding for " + fld.getName() + " in " + this.getClass().getName());
            }
            String[] value = this.getProp(desc, field, autoPop);
            if (value[0].length() > (field.end - field.start + 1)) {
              throw new InvalidFieldLength(desc.getDisplayName(), (field.end - field.start + 1));
            }
            if ((desc.getPropertyType() == Date.class) || (desc.getPropertyType() == Boolean.class) || (desc.getPropertyType() == Integer.class)) {
              if (!(StringUtils.isEmpty(value[0]) || (value[0].length() == (field.end - field.start + 1)))) {
                throw new AssertionError("FixedFieldDescriptor for " + desc.getDisplayName() + " in " + this.getClass().getSimpleName()
                    + ", start/end (" + field.start + "," + field.end + ") invalid for type " +
                    desc.getPropertyType().getName());
              }
            }
            if (fixed.containsKey(Integer.valueOf(field.start))) {
              throw new AssertionError("Positioning error inserting field at " + field.start + " for class " + this.getClass().getName());
            }
            fixed.put(new Integer(field.start), this.pad(value[0], field));
          }
          if (fld.isAnnotationPresent(TaggedField.class)) {
            TaggedField annotation = (TaggedField)fld.getAnnotation(TaggedField.class);
            TaggedFieldDefinition field = Fields.getTaggedFieldDefinition(this.getClass().getName(), fld.getName(), annotation);
            PropertyDescriptor desc;
            try {
              desc = PropertyUtils.getPropertyDescriptor(this, fld.getName());
            } catch (Exception ex) {
              throw new AssertionError("Introspection problem during encoding for " + fld.getName() + " in " + this.getClass().getName());
            }
            if (desc == null) {
              throw new AssertionError("Introspection problem during encoding for " + fld.getName() + " in " + this.getClass().getName());
            }
            String[] value = this.getProp(desc, field, autoPop);
            if (value.length > 0 && StringUtils.isNotEmpty(value[0])) {
              if (field.length != 0) {
                if (desc.getPropertyType() == String.class) {
                  if (value[0].length() > field.length) {
                    throw new InvalidFieldLength(desc.getDisplayName(), field.length);
                  }
                } else {
                  if (value[0].length() != field.length) {
                    throw new InvalidFieldLength(desc.getDisplayName(), field.length);
                  }
                }
              }
              variable.put(field.tag, value);
            } else if (field.policy == FieldPolicy.REQUIRED) {
              variable.put(field.tag, new String[]{""});
            }
          }
        }

        if (this.getClass().isAnnotationPresent(Command.class)) {
            message.append(((Command)(this.getClass().getAnnotation(Command.class))).value());
        } else {
            throw new AssertionError("No command annotation present for class " + this.getClass().getName());
        }

        Iterator<Integer> fixedIterate = fixed.keySet().iterator();
        while (fixedIterate.hasNext()) {
            Integer key = fixedIterate.next();
            if (message.length() != key.intValue()) {
                throw new AssertionError("Positioning error inserting field at " + key + " for class " + this.getClass().getName());
            }
            message.append(fixed.get(key));
        }

        Iterator<String> varIterate = variable.keySet().iterator();
        while (varIterate.hasNext()) {
            String key = varIterate.next();
            String[] values = variable.get(key);
            for (String value : values) {
                message.append(key);
                message.append(value);
                message.append(TaggedFieldDefinition.TERMINATOR);
            }
        }

        return this.addChecksum(message.toString(), sequence);
    }

    private void setProp(PropertyDescriptor desc, String value) {
        try {
            if (desc.getPropertyType() == Boolean.class) {
                desc.getWriteMethod().invoke(this,
                                     new Object[] { value.equalsIgnoreCase("U") ? null :
                                             new Boolean(value.
                                                     equalsIgnoreCase("Y") ||
                                                     value.equalsIgnoreCase("1")) });
                return;
            }
            if (desc.getPropertyType() == Date.class) {
                desc.getWriteMethod().invoke(this,
                                     new Object[] { this.demangleDate(value) });
                return;
            }
            if (desc.getPropertyType() == Integer.class) {
                    if (!value.trim().isEmpty()) {
                desc.getWriteMethod().invoke(this,
                                     new Object[] {Integer.valueOf(value.trim()) });
                    }
                return;
            }
            if (desc.getPropertyType() == String.class) {
                desc.getWriteMethod().invoke(this,
                                     new Object[] { new String(value) });
                return;
            }
            if (desc.getPropertyType().getSuperclass() == AbstractFlagField.class) {
                Object data = desc.getPropertyType().getConstructor(new Class[] { String.class }).newInstance(new Object[] { new String(value) });
                    if (data != null) {
                desc.getWriteMethod().invoke(this,
                        new Object[] { data });
                    }
                return;
            }
            Class<?>[] interfaces = desc.getPropertyType().getInterfaces();
            for (Class<?> interfce : interfaces) {
                if (interfce == AbstractEnumeration.class) {
                    Method mthd = interfce.getDeclaredMethod("getKey",
                            new Class[] { String.class });
                    Method mthdInst = desc.getPropertyType().getDeclaredMethod("values",
                            new Class[] {});
                    Object[] values = (Object[]) mthdInst.invoke(null, new Object[] {});
                    if (values.length > 0) {
                        Object data = mthd.invoke(values[0],
                                new Object[] { new String(value) });
                        desc.getWriteMethod().invoke(this,
                                new Object[] { data });
                        return;
                    }
                }
            }
            if (desc.getPropertyType() == String[].class) {
                String[] current = (String[]) desc.getReadMethod().invoke(this, new Object[0]);
                if (current == null) {
                    desc.getWriteMethod().invoke(this,
                            new Object[] { new String[] { new String(value) } });
                    return;
                } else {
                    List<String> l = new ArrayList<String>(current.length + 1);
                    l.addAll(Arrays.asList(current));
                    l.add(new String(value));
                    desc.getWriteMethod().invoke(this,
                            new Object[] { l.toArray(new String[l.size()]) });
                    return;
                }
            }
        } catch (Exception ex) {
            Message.log.error("Unexpected error setting " + desc.getDisplayName() + " to " + value, ex);
        }
    }

    public static Message decode(String message, Character sequence, boolean checksumCheck) throws MandatoryFieldOmitted, ChecksumError, SequenceError,
        MessageNotUnderstood {
        String pop = System.getProperty(Message.PROP_AUTOPOPULATE, PROP_AUTOPOPULATE_BIDIRECTIONAL);
        boolean autoPop = false;
        if (pop.equalsIgnoreCase(PROP_AUTOPOPULATE_DECODE) || pop.equalsIgnoreCase(PROP_AUTOPOPULATE_DEFAULT)) {
            autoPop = true;
        }
      return decode(message, sequence, checksumCheck, autoPop);
    }

    private static Message decode(String message, Character sequence, boolean checksumCheck, boolean autoPop) throws MandatoryFieldOmitted, ChecksumError, SequenceError,
            MessageNotUnderstood {
        if (checksumCheck) {
            if (!Message.CheckChecksum(message)) {
                throw new ChecksumError();
            }
        }
        Character sequenceCharacter = Message.GetSequenceCharacter(message);

        if (sequence != null) {
            if (sequenceCharacter != null) {
                if (!sequence.equals(sequenceCharacter)) {
                    throw new SequenceError();
                }
            }
        }

        if (message == null) {
            throw new MessageNotUnderstood();
        }
        if (message.length() < 2) {
            throw new MessageNotUnderstood();
        }
        String command = message.substring(0, 2);
        Class<? extends Message> msgClass = Message.messages.get(command);
        if (msgClass == null) {
            throw new MessageNotUnderstood();
        }
        Message msg;
    try {
            msg = msgClass.newInstance();
        } catch (Exception ex) {
            throw new AssertionError("Instantiation problem creating new " + msgClass.getName());
        }
        Field[] fields = msg.getClass().getDeclaredFields();

        int fixedFieldEnd = 2;

        for (Field fld : fields) {
            if (fld.isAnnotationPresent(PositionedField.class)) {
                PositionedField annotation = fld.getAnnotation(PositionedField.class);
                PositionedFieldDefinition field = Fields.getPositionedFieldDefinition(msg.getClass().getName(), fld.getName(), annotation);
                PropertyDescriptor desc;
                try {
                    desc = PropertyUtils.getPropertyDescriptor(msg, fld.getName());
                } catch (Exception ex) {
                    throw new AssertionError("Introspection problem during decoding for " + fld.getName() + " in " + msg.getClass().getName());
                }
                if (desc == null) {
                    throw new AssertionError("Introspection problem during decoding for " + fld.getName() + " in " + msg.getClass().getName());
                }
                String value = "";
                if (message.length() > field.end) {
                  value = message.substring(field.start, field.end + 1);
                } else {
                  if (!autoPop) {
                    throw new MandatoryFieldOmitted(desc.getDisplayName());
                  }
                }
                msg.setProp(desc, value);
                if (fixedFieldEnd < field.end) {
                    fixedFieldEnd = field.end;
                }
            }
        }

        msg.parseVarFields(fixedFieldEnd + 1, message);

        msg.SequenceCharacter = sequenceCharacter;

        for (Field fld : fields) {
            if (fld.isAnnotationPresent(TaggedField.class)) {
              TaggedField annotation = fld.getAnnotation(TaggedField.class);
              TaggedFieldDefinition field = Fields.getTaggedFieldDefinition(msg.getClass().getName(), fld.getName(), annotation);
                PropertyDescriptor desc;
                try {
                    desc = PropertyUtils.getPropertyDescriptor(msg, fld.getName());
                } catch (Exception ex) {
                    throw new AssertionError("Introspection problem during decoding for " + fld.getName() + " in " + msg.getClass().getName());
                }
                if (desc == null) {
                    throw new AssertionError("Introspection problem during decoding for " + fld.getName() + " in " + msg.getClass().getName());
                }
                try {
                  msg.getProp(desc, field, false);
                } catch (MandatoryFieldOmitted ex) {
              if (autoPop) {
                msg.setProp(desc, "");
              } else {
                throw ex;
              }
                }
            }
        }

        return msg;
    }

    private static boolean CheckChecksum(String message) {
        try {
            String tail = message.substring(message.length() - 6);
            if (!tail.startsWith("AZ")) {
                return true;
            }
            String truncated = message.substring(0, message.length() - 4);
            String check = tail.substring(2);
            String checksum = Message.calculateChecksum(truncated);
            return (checksum.equals(check));
        } catch (Exception ex) {
        }

        return true;
    }

    private static Character GetSequenceCharacter(String message) {
        try {
            String tail = message.substring(message.length() - 9);
            if (!tail.startsWith("AY")) {
                return null;
            }
            return tail.charAt(2);
        } catch (Exception ex) {
        }

        return null;
    }
    protected static String calculateChecksum(String data) throws UnsupportedEncodingException {
        int checksum = 0;
        // Fix from Rustam Usmanov
        byte[] bytes = data.getBytes(Message.getCharsetEncoding());
        for (byte b : bytes) {
            // Fix from Rustam Usmanov
          checksum += b & 0xff;
        }
        checksum = -checksum & 0xffff;
        return Integer.toHexString(checksum).toUpperCase();
    }

    protected String addChecksum(String command, Character sequence) {
        StringBuffer check = new StringBuffer();
        if (sequence != null) {
            check.append("AY");
            check.append(sequence);
            check.append("AZ");
            try {
                check.append(Message.calculateChecksum(command + check.toString()));
                return command + check.toString();
            } catch (Exception e) {
                return command;
            }
        } else {
            return command;
        }
    }

    private void parseVarFields(int offset, String data) {
        int status = 1;
        StringBuffer fieldtag = new StringBuffer();
        StringBuffer fielddata = new StringBuffer();

        for (int n = offset; n < data.length(); n++) {
            if (status == 1) {
                fieldtag = new StringBuffer();
                fieldtag.append(data.charAt(n));
                status = 2;
            } else if (status == 2) {
                fielddata = new StringBuffer();
                fieldtag.append(data.charAt(n));
                status = 3;
            } else if (status == 3) {
                if (data.charAt(n) == TaggedFieldDefinition.TERMINATOR) {
                    this.setFieldProp(fieldtag.toString(), fielddata.toString());
                    status = 1;
                } else {
                    fielddata.append(data.charAt(n));
                }
            }
        }
        return;
    }

    private void setFieldProp(String tag, String data) {
        Field[] fields = this.getClass().getDeclaredFields();

        for (Field fld : fields) {
                if (fld.isAnnotationPresent(TaggedField.class)) {
                    TaggedField annotation = fld.getAnnotation(TaggedField.class);
                    TaggedFieldDefinition field = Fields.getTaggedFieldDefinition(this.getClass().getName(), fld.getName(), annotation);
                    PropertyDescriptor desc;
                    try {
                        desc = PropertyUtils.getPropertyDescriptor(this, fld.getName());
                    } catch (Exception ex) {
                        throw new AssertionError("Introspection problem during decoding for " + fld.getName() + " in " + this.getClass().getName());
                    }
                    if (desc == null) {
                        throw new AssertionError("Introspection problem during decoding for " + fld.getName() + " in " + this.getClass().getName());
                    }
                    if (field.tag.equals(tag)) {
                        this.setProp(desc, data);
                    }
                }
            }
        }

    @Deprecated
    public void xmlEncode(OutputStream strm) {
        XMLEncoder out = new XMLEncoder(strm);
        out.writeObject(this);
        out.flush();
    }

    public static Message xmlDecode(InputStream strm) {
        XMLDecoder in = new XMLDecoder(strm);
        Message msg = (Message) in.readObject();
        return msg;
    }

    private static Hashtable<String, Class<? extends Message>> messages = new Hashtable<String, Class<? extends Message>>();

    static {
        for (Messages m: Messages.values()) {
            try {
                @SuppressWarnings("unchecked")
                Class<? extends Message> message = (Class<? extends Message>)Class.forName(Messages.class.getPackage().getName() +  "." + m.name());
                if (message != null) {
                    if (message.isAnnotationPresent(Command.class)) {
                        String cmd = ((Command)message.getAnnotation(Command.class)).value();
                        if (cmd.isEmpty()) {
                            throw new AssertionError(m.name() + " has empty command string.");
                        }
                        if (Message.messages.containsKey(cmd)) {
                            throw new AssertionError(m.name() + " duplicates command string.");
                        }
                        Message.messages.put(cmd, (Class<? extends Message>)message);
                    }
                }
            } catch (Exception ex) {
                Message.log.warn(m.name() + " not yet implemented.");
            }
        }
    }

    @Override
    public String toString() {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        this.xmlEncode(buffer);
        return new String(buffer.toByteArray());
    }
    
    /**
     * 以下代码用于测试用例的实现
     */
    private Message getEmptyMessage() {
        try {
            Message msg = (Message)this.getClass().newInstance();
            for (Field field: this.getClass().getDeclaredFields()) {
                if (field.getType() == Date.class) {
                    PropertyDescriptor desc = PropertyUtils.getPropertyDescriptor(msg, field.getName());
                    if (desc != null) {
                        if (field.isAnnotationPresent(PositionedField.class)) {
                            PositionedField annotation = (PositionedField)field.getAnnotation(PositionedField.class);               
                            PositionedFieldDefinition fld = Fields.getPositionedFieldDefinition(this.getClass().getName(), field.getName(), annotation);
                            if (fld.policy == FieldPolicy.REQUIRED) {
                                Method method = desc.getWriteMethod();
                                if (method != null) {
                                    method.invoke(msg, new Object[]{demangleDate("19700101    010000")});
}
                            }
                        }

                        if (field.isAnnotationPresent(TaggedField.class)) {
                            TaggedField annotation = (TaggedField)field.getAnnotation(TaggedField.class);               
                            TaggedFieldDefinition fld = Fields.getTaggedFieldDefinition(this.getClass().getName(), field.getName(), annotation);
                            if (fld.policy == FieldPolicy.REQUIRED) {
                                Method method = desc.getWriteMethod();
                                if (method != null) {
                                    method.invoke(msg, new Object[]{demangleDate("19700101    010000")});
                                }                                                
                            }
                        }
                    }
                }
            }
            return msg;
        } catch (Exception ex) {
            Assert.fail("Exception getting default message: " + ex.getMessage());
            return null;
        }
    }

    private Message getDefaultMessage() {
        try {
            Message msg = (Message)this.getClass().newInstance();
            for (Field field: this.getClass().getDeclaredFields()) {
                boolean required = false;
                int length = 0;
                PropertyDescriptor desc = PropertyUtils.getPropertyDescriptor(msg, field.getName());
                if (desc != null) {
                    if (field.isAnnotationPresent(PositionedField.class)) {
                        PositionedField annotation = (PositionedField)field.getAnnotation(PositionedField.class);               
                        PositionedFieldDefinition fld = Fields.getPositionedFieldDefinition(this.getClass().getName(), field.getName(), annotation);
                        required = (fld.policy == FieldPolicy.REQUIRED);
                        length = fld.end - fld.start + 1;
                    }
                    if (field.isAnnotationPresent(TaggedField.class)) {
                        TaggedField annotation = (TaggedField)field.getAnnotation(TaggedField.class);               
                        TaggedFieldDefinition fld = Fields.getTaggedFieldDefinition(this.getClass().getName(), field.getName(), annotation);
                        if (fld.policy == FieldPolicy.REQUIRED) {
                            required = (fld.policy == FieldPolicy.REQUIRED);
                        }
                    }
                    if (field.getType() == String.class) {
                        Method method = desc.getWriteMethod();
                        if (method != null) {
                            if (length > 0) {
                                method.invoke(msg, new Object[]{String.format("%0$" + length + "c", ' ')});
                            }
                        }
                    }
                    if (required) {
                        if (field.getType() == String.class) {
                            Method method = desc.getWriteMethod();
                            if (method != null) {
                                if (length == 0) {
                                    method.invoke(msg, new Object[]{new String()});
                                }
                            }
                        }
                        if (field.getType() == Integer.class) {
                            Method method = desc.getWriteMethod();
                            if (method != null) {
                                method.invoke(msg, new Object[]{Integer.valueOf(0)});
                            }
                        }
                        if (field.getType() == Boolean.class) {
                            Method method = desc.getWriteMethod();
                            if (method != null) {
                                method.invoke(msg, new Object[]{new Boolean(false)});
                            }
                        }
                        if (field.getType() == Date.class) {
                            Method method = desc.getWriteMethod();
                            if (method != null) {
                                method.invoke(msg, new Object[]{demangleDate("19700101    010000")});
                            }
                        }
                        Class<?>[] interfaces = desc.getPropertyType().getInterfaces();
                        for (Class<?> interfce : interfaces) {
                            if (interfce == AbstractEnumeration.class) {
                                if (field.isAnnotationPresent(PositionedField.class)) {
                                    Method mthdInst = desc.getPropertyType().getDeclaredMethod("values",
                                            new Class[] {});
                                    Object[] values = (Object[]) mthdInst.invoke(null, new Object[] {});
                                    if (values.length > 0) {
                                        desc.getWriteMethod().invoke(msg,
                                                new Object[] { values[0] });
                                    }                                    
                                }
                            }
                        }
                    }
                }
            }
            return msg;
        } catch (Exception ex) {
            Assert.fail("Exception getting default message: " + ex.getMessage());
            return null;
        }
    }

    private Message getPopulatedMessage() {
        try {
            Message msg = (Message)this.getClass().newInstance();
            for (Field field: this.getClass().getDeclaredFields()) {
                PropertyDescriptor desc = PropertyUtils.getPropertyDescriptor(msg, field.getName());
                if (desc != null) {
                    int length = 0;
                    if (field.isAnnotationPresent(PositionedField.class)) {
                        PositionedField annotation = (PositionedField)field.getAnnotation(PositionedField.class);               
                        PositionedFieldDefinition fld = Fields.getPositionedFieldDefinition(this.getClass().getName(), field.getName(), annotation);
                        length = fld.length;
                    }
                    if (field.isAnnotationPresent(TaggedField.class)) {
                        TaggedField annotation = (TaggedField)field.getAnnotation(TaggedField.class);               
                        TaggedFieldDefinition fld = Fields.getTaggedFieldDefinition(this.getClass().getName(), field.getName(), annotation);
                        length = fld.length;
                    }
                    Method method = desc.getWriteMethod();
                    if (method != null) {
                        Class<?> type = desc.getPropertyType();
                        if (type == Date.class) {
                            method.invoke(msg, new Object[]{demangleDate("19700101    010000")});
                        }                    
                        if (type == Boolean.class) {
                            method.invoke(msg, new Object[]{new Boolean(true)});
                        }                    
                        if (type == Integer.class) {
                            String value = "123456789";
                            if (length != 0) {
                                value = value.substring(0, length);
                            }
                            method.invoke(msg, new Object[]{Integer.valueOf(value)});
                        }                    
                        if (type == String.class) {
                            String value = field.getName();
                            if (length != 0) {
                                value = value.substring(0, length);
                            }
                            method.invoke(msg, new Object[]{new String(value)});
                        }
                        if (desc.getPropertyType().getSuperclass() == AbstractFlagField.class) {
                          AbstractFlagField afd = (AbstractFlagField)desc.getReadMethod().invoke(msg, new Object[]{});
                          PropertyDescriptor[] dscs = PropertyUtils.getPropertyDescriptors(desc.getPropertyType());
                          for (PropertyDescriptor dsc: dscs) {
                            String name = dsc.getPropertyType().getName();
                            if (name != null) {
                              if (name.equalsIgnoreCase("boolean")) {
                                 Method writer = dsc.getWriteMethod();
                                 if (writer != null) {
                                   writer.invoke(afd, new Object[]{new Boolean(true)});                                   
                                 }
                              }
                            }
                          }
                        }
                        Class<?>[] interfaces = desc.getPropertyType().getInterfaces();
                        for (Class<?> interfce : interfaces) {
                            if (interfce == AbstractEnumeration.class) {
                                Method mthdInst = desc.getPropertyType().getDeclaredMethod("values",
                                        new Class[] {});
                                Object[] values = (Object[]) mthdInst.invoke(null, new Object[] {});
                                if (values.length > 0) {
                                    desc.getWriteMethod().invoke(msg,
                                            new Object[] { values[values.length - 1] });
                                }
                            }
                        }
                        if (desc.getPropertyType() == String[].class) {
                            String value = field.getName();
                            if (length != 0) {
                                value = value.substring(0, length-1);
                            }
                            method.invoke(msg, new Object[]{new String[]{new String(value + "1"), new String(value + "2")}});                            
                        }
                        
                    }
                }
            }
            return msg;
        } catch (Exception ex) {
            Assert.fail("Exception getting populated message: " + ex.getClass().getName());
            return null;
        }
    }    
    
    @Test 
    public void testCaseDisableEncodeAutoPopulate() {
      try {
        if (this instanceof SCResend || this instanceof ACSResend) { // have no mandatory fields
          return;
        }
      this.getEmptyMessage().encode(null, false);
        Assert.fail("Missing mandatory fields not caught");
    } catch (MandatoryFieldOmitted e) {
      Assert.assertTrue("Caught missing mandatory field: " + e.getMessage(), true);
    } catch (InvalidFieldLength e) {
            Assert.fail("Fixed Field Too Long: " + e.getMessage());
    } catch (MessageNotUnderstood e) {
            Assert.fail("Message not understood: " + e.getMessage());
    } 
    }
    
    @Test 
    public void testCaseDisableDecodeAutoPopulate() {
      try {
        if (this instanceof SCResend || this instanceof ACSResend) { // have no mandatory fields
          return;
        }
        String message = "";
            if (this.getClass().isAnnotationPresent(Command.class)) {
                message = ((Command)(this.getClass().getAnnotation(Command.class))).value();
            } else {
                Assert.fail("No command annotation present for class " + this.getClass().getName());
            }       
        Message.decode(message, null, false, false);
          Assert.fail("Missing mandatory fields not caught");
    } catch (MandatoryFieldOmitted e) {
      Assert.assertTrue("Caught missing mandatory field: " + e.getMessage(), true);
    } catch (MessageNotUnderstood e) {
            Assert.fail("Message not understood: " + e.getMessage());
    } catch (ChecksumError e) {
            Assert.fail("Checksum Error");
        } catch (SequenceError e) {
            Assert.fail("Sequence Error");
    }
    }    
    
    @Test
    public void testCaseDefaultEncode() {
        try {
            if (this.getClass().isAnnotationPresent(TestCaseDefault.class)) {
                String t = this.getEmptyMessage().encode(null, true);
                String v = ((TestCaseDefault)(this.getClass().getAnnotation(TestCaseDefault.class))).value();
                Assert.assertEquals(v, t);
            } else {
                Assert.fail("Message has no TestCaseDefault annotation");
            }            
        } catch (MandatoryFieldOmitted e) {
            Assert.fail("Mandatory Field Omitted: " + e.getMessage());
        } catch (InvalidFieldLength e) {
            Assert.fail("Fixed Field Too Long: " + e.getMessage());
        } catch (MessageNotUnderstood e) {
            Assert.fail("Message not understood: " + e.getMessage());
        }
    }

    @Test
    public void testCaseDefaultDecode() {
        try {
            if (this.getClass().isAnnotationPresent(TestCaseDefault.class)) {
                String v = ((TestCaseDefault)(this.getClass().getAnnotation(TestCaseDefault.class))).value();
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                Message m = Message.decode(v, null, false, false);
                m.xmlEncode(stream);
                String r = stream.toString();
                stream = new ByteArrayOutputStream();
                m = this.getDefaultMessage();
                m.xmlEncode(stream);
                String t = stream.toString();
                Assert.assertEquals(t, r);
            } else {
                Assert.fail("Message has no TestCaseDefault annotation");
            }            
        } catch (MandatoryFieldOmitted e) {
            Assert.fail("Mandatory Field Omitted");
        } catch (ChecksumError e) {
            Assert.fail("Checksum Error");
        } catch (SequenceError e) {
            Assert.fail("Sequence Error");
        } catch (MessageNotUnderstood e) {
            Assert.fail("Message Not Understood");
        }
    }

    @Test
    public void testCasePopulatedEncode() {
        try {
            if (this.getClass().isAnnotationPresent(TestCasePopulated.class)) {
                String t = this.getPopulatedMessage().encode(null, false);
                String v = ((TestCasePopulated)(this.getClass().getAnnotation(TestCasePopulated.class))).value();
                Assert.assertEquals(v, t);
            } else {
                Assert.fail("Message has no TestCasePopulated annotation");
            }            
        } catch (MandatoryFieldOmitted e) {
            Assert.fail("Mandatory Field Omitted: " + e.getMessage());
        } catch (InvalidFieldLength e) {
            Assert.fail("Field Wrong Size: " + e.getMessage());
        } catch (MessageNotUnderstood e) {
            Assert.fail("Message not understood: " + e.getMessage());
        }
    }

    @Test
    public void testCasePopulatedDecode() {
        try {
            if (this.getClass().isAnnotationPresent(TestCasePopulated.class)) {
                String v = ((TestCasePopulated)(this.getClass().getAnnotation(TestCasePopulated.class))).value();
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                Message m = Message.decode(v, null, false, false);
                m.xmlEncode(stream);
                String r = stream.toString();
                stream = new ByteArrayOutputStream();
                m = this.getPopulatedMessage();
                m.xmlEncode(stream);
                String t = stream.toString();
                Assert.assertEquals(t, r);
            } else {
                Assert.fail("Message has no TestCasePopulated annotation");
            }            
        } catch (MandatoryFieldOmitted e) {
            Assert.fail("Mandatory Field Omitted");
        } catch (ChecksumError e) {
            Assert.fail("Checksum Error");
        } catch (SequenceError e) {
            Assert.fail("Sequence Error");
        } catch (MessageNotUnderstood e) {
            Assert.fail("Message Not Understood");
        }
    }

    @Test
    public void testCaseDefaultRoundTrip() {
        try {
            String t = this.getEmptyMessage().encode('0', true);
            Message m;
            m = Message.decode(t, '0', true, false);
            Assert.assertEquals(t, m.encode('0', false));
        } catch (MandatoryFieldOmitted e) {
            Assert.fail("Mandatory Field Omitted: " + e.getMessage());
        } catch (InvalidFieldLength e) {
            Assert.fail("Fixed Field Too Long: " + e.getMessage());
        } catch (ChecksumError e) {
            Assert.fail("Checksum Error");
        } catch (SequenceError e) {
            Assert.fail("Sequence Error");
        } catch (MessageNotUnderstood e) {
            Assert.fail("Message Not Understood");
        }
    }    

    @Test
    public void testCasePopulatedRoundTrip() {
        try {
            String t = this.getPopulatedMessage().encode('0', false);
            Message m;
            m = Message.decode(t, '0', true, false);
            Assert.assertEquals(t, m.encode('0', false));
        } catch (MandatoryFieldOmitted e) {
            Assert.fail("Mandatory Field Omitted: " + e.getMessage());
        } catch (InvalidFieldLength e) {
            Assert.fail("Fixed Field Too Long: " + e.getMessage());
        } catch (ChecksumError e) {
            Assert.fail("Checksum Error");
        } catch (SequenceError e) {
            Assert.fail("Sequence Error");
        } catch (MessageNotUnderstood e) {
            Assert.fail("Message Not Understood");
        }
    }    
}
