package com.wanghui.sip2model.fields;

import com.wanghui.sip2model.annotations.Field;
import com.wanghui.sip2model.annotations.PositionedField;
import com.wanghui.sip2model.annotations.TaggedField;
import com.wanghui.sip2model.types.enumerations.*;
import com.wanghui.sip2model.types.flagfields.PatronStatus;
import com.wanghui.sip2model.types.flagfields.Summary;
import com.wanghui.sip2model.types.flagfields.SupportedMessages;

import java.util.Date;

/**
 * @auther wanghui
 * @create 2018-11-13 0:36
 * @Description 定义了SIP2各个命令请求和相应的字段；如果需要扩充字段，可以在此处定义扩展字段
 */
public class Fields {
    @Field(policy = FieldPolicy.REQUIRED)
    Boolean ACSRenewalPolicy;
    @Field(policy = FieldPolicy.REQUIRED)
    Boolean alert;
    @Field(policy = FieldPolicy.REQUIRED)
    Boolean available;
    @Field(tag = "AL", policy = FieldPolicy.REQUIRED)
    String blockedCardMessage;
    @Field(tag = "BI", policy = FieldPolicy.NOT_REQUIRED)
    Boolean cancel;
    @Field(policy = FieldPolicy.REQUIRED)
    Boolean cardRetained;
    @Field(length = 2, policy = FieldPolicy.REQUIRED)
    CirculationStatus circulationStatus;
    @Field(tag = "AU", policy = FieldPolicy.NOT_REQUIRED)
    String chargedItems;
    @Field(length = 4, policy = FieldPolicy.NOT_REQUIRED)
    Integer chargedItemsCount;
    @Field(tag = "CB", length = 4, policy = FieldPolicy.NOT_REQUIRED)
    Integer chargedItemsLimit;
    @Field(policy = FieldPolicy.REQUIRED)
    Boolean checkInOk;
    @Field(policy = FieldPolicy.REQUIRED)
    Boolean checkOutOk;
    @Field(tag = "BH", length = 3)
    CurrencyType currencyType;
    @Field(tag = "AP")
    String currentLocation;
    @Field(policy = FieldPolicy.REQUIRED)
    Date dateTimeSync; 
    @Field(length = 1, policy = FieldPolicy.REQUIRED)
    Boolean desensitize;
    @Field(tag = "AH") //This date need not be in the standard format
    String dueDate; 
    @Field(tag = "BE", policy = FieldPolicy.NOT_REQUIRED)
    String emailAddress;
    @Field(tag = "BQ", policy = FieldPolicy.NOT_REQUIRED)
    Integer endItem;
    @Field(length = 1, policy = FieldPolicy.REQUIRED)
    Boolean endSession;
    @Field(tag = "BW", policy = FieldPolicy.NOT_REQUIRED)
    Date expirationDate;
    @Field(tag = "BO", policy = FieldPolicy.NOT_REQUIRED)
    Boolean feeAcknowledged;
    @Field(tag = "BV") //Formated according to country conventions
    String feeAmount;
    @Field(tag = "CC", policy = FieldPolicy.NOT_REQUIRED) //Formated according to country conventions
    String feeLimit; 
    @Field(tag = "CG", policy = FieldPolicy.NOT_REQUIRED)
    String feeIdentifier;
    @Field(tag = "BT", length = 2)
    FeeType feeType;
    @Field(tag = "AV", policy = FieldPolicy.NOT_REQUIRED)
    String[] fineItems; 
    @Field(length = 4, policy = FieldPolicy.NOT_REQUIRED)
    Integer fineItemsCount;
    @Field(tag = "AS", policy = FieldPolicy.NOT_REQUIRED)
    String[] holdItems; 
    @Field(length = 4, policy = FieldPolicy.NOT_REQUIRED)
    Integer holdItemsCount; 
    @Field(tag = "BZ", length = 4, policy = FieldPolicy.NOT_REQUIRED)
    Integer holdItemsLimit;
    @Field(length = 1, policy = FieldPolicy.REQUIRED)
    HoldMode holdMode;
    @Field(tag = "CM", policy = FieldPolicy.NOT_REQUIRED)
    Date holdPickupDate;
    @Field(tag = "CF", length=5, policy = FieldPolicy.REQUIRED) // according to spec should be no length, not required but that breaks 3M emulator
    Integer holdQueueLength;
    @Field(tag = "BY", length = 1, policy = FieldPolicy.NOT_REQUIRED)
    HoldType holdType;
    @Field(tag = "BD", policy = FieldPolicy.NOT_REQUIRED)
    String homeAddress;
    @Field(tag = "BF", policy = FieldPolicy.NOT_REQUIRED)
    String homePhoneNumber;
    @Field(tag = "AO", policy = FieldPolicy.REQUIRED)
    String institutionId;
    @Field(tag = "AB")
    String itemIdentifier;
    @Field(tag = "CH")
    String itemProperties;
    @Field(length = 3, policy = FieldPolicy.REQUIRED)
    Language language;
    @Field(tag = "AM", policy = FieldPolicy.NOT_REQUIRED)
    String libraryName;
    @Field(tag = "CP", policy = FieldPolicy.NOT_REQUIRED)
    String locationCode;
    @Field(tag = "CN", policy = FieldPolicy.REQUIRED)
    String loginUserId;
    @Field(tag = "CO", policy = FieldPolicy.REQUIRED)
    String loginPassword;
    @Field(policy = FieldPolicy.REQUIRED)
    Boolean magneticMedia;
    @Field(length = 3, policy = FieldPolicy.REQUIRED)
    Integer maxPrintWidth;
    @Field(tag = "CK", length = 3, policy = FieldPolicy.NOT_REQUIRED)
    MediaType mediaType;
    @Field(policy = FieldPolicy.NOT_REQUIRED)
    Date nbDueDate;
    @Field(policy = FieldPolicy.REQUIRED)
    Boolean noBlock;
    @Field(policy = FieldPolicy.REQUIRED)
    Boolean offlineOk;
    @Field(policy = FieldPolicy.REQUIRED)
    Boolean ok;
    @Field(policy = FieldPolicy.REQUIRED)
    Boolean onlineStatus;
    @Field(tag = "AT", policy = FieldPolicy.NOT_REQUIRED)
    String[] overdueItems;
    @Field(length = 4, policy = FieldPolicy.NOT_REQUIRED)
    Integer overdueItemsCount;
    @Field(tag = "CA", length = 4, policy = FieldPolicy.NOT_REQUIRED)
    Integer overdueItemsLimit;
    @Field(tag = "BG", policy = FieldPolicy.NOT_REQUIRED)
    String owner;
    @Field(tag = "AA")
    String patronIdentifier;
    @Field(tag = "AD")
    String patronPassword;
    @Field(length = 14, policy = FieldPolicy.NOT_REQUIRED)
    PatronStatus patronStatus;
    @Field(policy = FieldPolicy.REQUIRED)
    Boolean paymentAccepted;
    @Field(length = 2, policy = FieldPolicy.REQUIRED)
    PaymentType paymentType;
    @Field(tag = "AQ")
    String permanentLocation;
    @Field(tag = "AE", policy = FieldPolicy.REQUIRED)
    String personalName;
    @Field(tag = "BS", policy = FieldPolicy.NOT_REQUIRED)
    String pickupLocation;
    @Field(tag = "AG", policy = FieldPolicy.NOT_REQUIRED)
    String printLine;
    @Field(length = 4, policy = FieldPolicy.REQUIRED)
    ProtocolVersion protocolVersion;
    @Field(length = 1, policy = FieldPolicy.NOT_REQUIRED)
    String PWDAlgorithm;
    @Field(tag = "BR", policy = FieldPolicy.NOT_REQUIRED)
    Integer queuePosition;
    @Field(tag = "CJ", policy = FieldPolicy.NOT_REQUIRED)
    Date recallDate;
    @Field(tag = "BU", policy = FieldPolicy.NOT_REQUIRED)
    String[] recallItems;
    @Field(length = 4, policy = FieldPolicy.NOT_REQUIRED)
    Integer recallItemsCount;
    @Field(policy = FieldPolicy.REQUIRED)
    Boolean renewalOk;
    @Field(length = 4, policy = FieldPolicy.REQUIRED)
    Integer renewedCount;
    @Field(policy = FieldPolicy.REQUIRED)
    Boolean resensitize;
    @Field(length = 3, policy = FieldPolicy.REQUIRED)
    Integer retriesAllowed;
    @Field(policy = FieldPolicy.NOT_REQUIRED)
    Date returnDate;
    @Field(policy = FieldPolicy.REQUIRED)
    Boolean SCRenewalPolicy;
    @Field(length = 2, policy = FieldPolicy.REQUIRED)
    SecurityMarker securityMarker;
    @Field(tag = "AF", policy = FieldPolicy.NOT_REQUIRED)
    String screenMessage;
    @Field(tag = "CI", policy = FieldPolicy.NOT_REQUIRED)
    Boolean securityInhibit;
    @Field(tag = "CL", policy = FieldPolicy.NOT_REQUIRED)
    String sortBin;
    @Field(tag = "BP", policy = FieldPolicy.NOT_REQUIRED)
    Integer startItem;
    @Field(length = 1, policy = FieldPolicy.REQUIRED)
    StatusCode statusCode;
    @Field(policy = FieldPolicy.REQUIRED)
    Boolean statusUpdateOk;
    @Field(length = 10, policy = FieldPolicy.NOT_REQUIRED)
    Summary summary;
    @Field(tag = "BX", length = 16, policy = FieldPolicy.REQUIRED)
    SupportedMessages supportedMessages;
    @Field(tag = "AN", policy = FieldPolicy.NOT_REQUIRED)
    String terminalLocation;
    @Field(tag = "AC")
    String terminalPassword;
    @Field(policy = FieldPolicy.REQUIRED)
    Boolean thirdPartyAllowed;
    @Field(length = 3, policy = FieldPolicy.REQUIRED)
    Integer timeoutPeriod;
    @Field(tag = "AJ")
    String titleIdentifier;
    @Field(policy = FieldPolicy.REQUIRED)
    Date transactionDate;
    @Field(tag = "BK", policy = FieldPolicy.NOT_REQUIRED)
    String transactionId;
    @Field(length = 1, policy = FieldPolicy.NOT_REQUIRED)
    String UIDAlgorithm;
    @Field(length = 4, policy = FieldPolicy.NOT_REQUIRED)
    Integer unavailableHoldsCount;
    @Field(tag = "CD", policy = FieldPolicy.NOT_REQUIRED)
    String unavailableHoldItems;
    @Field(length = 4, policy = FieldPolicy.REQUIRED)
    Integer unrenewedCount;
    @Field(tag = "BL", policy = FieldPolicy.NOT_REQUIRED)
    Boolean validPatron;
    @Field(tag = "CQ", policy = FieldPolicy.NOT_REQUIRED)
    Boolean validPatronPassword;

    /**
     * 利用java反射类，对位置字段进行实例化
     * @param messageName   请求或相应信息的类名
     * @param fieldName     字段名
     * @param annotation    字段注解
     * @return
     */
    static public PositionedFieldDefinition getPositionedFieldDefinition(String messageName, String fieldName, PositionedField annotation) {
        //此处由于在SIP2封装定义是已经定了Field,所以无法通过import的方式导入。
        java.lang.reflect.Field fld;
        try {
            /*
                在java.lang.Class中对getDeclaredField的定义：public Field getDeclaredField(String name) throws NoSuchFieldException, SecurityException
                判断某个类中是否有name声明的字段。
             */
            fld = Fields.class.getDeclaredField(fieldName);
        } catch (Exception ex) {
            throw new AssertionError(messageName + " - Positioned FieldDescriptor not defined: " + fieldName);
        }

        /*
        在java.lang.reflect.Field中对getDeclaredField的定义：public <T extends Annotation> T getAnnotation(Class<T> annotationClass)
        获取注解信息，如果存在返回则返回相关信息，如果不存在则返回null。
         */
        Field fldann = fld.getAnnotation(Field.class);
        if (fldann == null) {
            throw new AssertionError(messageName + " - Positioned FieldDescriptor not defined: " + fieldName);
        }
        /*
        调用辅助类FieldDefinition，生成字段。
        其中，fld.getType():使用了java.lang.reflect.Field中public Class<?> getType()。
        通过该函数返回标识由该对象表示的字段的声明类型的类对象
         */
        FieldDefinition field = new FieldDefinition(fldann, fld.getType());

        /*
        判断字段长度是否符合要求，如果不符合要求的话，抛出异常。
         */
        if (field.length == 0) {
            throw new AssertionError(messageName + " - Positioned FieldDescriptor must explicit length: " + fieldName);
        }
        if ((annotation.end() - annotation.start() + 1) != field.length) {
            throw new AssertionError(messageName + " - Positioned FieldDescriptors length mismatch: " + fieldName);
        }

        //以上代码是对代码合法性进行验证，验证通过后，调用位置字段定义辅助类的构造方法，生成实例。
        PositionedFieldDefinition pfd = new PositionedFieldDefinition(fieldName, annotation.start(),
                annotation.end(), field, annotation.policy());
        return pfd;
    }

    /**
     *  利用java反射类，分隔符字段定义
     * @param messageName   请求或相应类名
     * @param fieldName     字段名
     * @param annotation    注解
     * @return
     */
    static public TaggedFieldDefinition getTaggedFieldDefinition(String messageName, String fieldName, TaggedField annotation) {
        java.lang.reflect.Field fld;
        try {
            fld = Fields.class.getDeclaredField(fieldName);
        } catch (Exception ex) {
            throw new AssertionError(messageName + " - Tagged FieldDescriptor not defined: " + fieldName);
        }

        Field fldann = fld.getAnnotation(Field.class);
        if (fldann == null) {
            throw new AssertionError(messageName + " - Tagged FieldDescriptor not defined: " + fieldName);
        }

        FieldDefinition field = new FieldDefinition(fldann, fld.getType());
        if (field.tag.isEmpty()) {
            throw new AssertionError(messageName + " - field tag not defined: " + fieldName);
        }

        TaggedFieldDefinition tfd = new TaggedFieldDefinition(fieldName, field, annotation.value());
        return tfd;
    }
}
