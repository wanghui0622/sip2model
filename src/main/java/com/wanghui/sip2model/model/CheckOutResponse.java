package com.wanghui.sip2model.model;

import com.wanghui.sip2model.annotations.*;
import com.wanghui.sip2model.fields.FieldPolicy;
import com.wanghui.sip2model.types.enumerations.CurrencyType;
import com.wanghui.sip2model.types.enumerations.FeeType;
import com.wanghui.sip2model.types.enumerations.MediaType;

import java.util.Date;

/**
 * @auther wanghui
 * @create 2018-11-13 0:36
 * @Description
 */
@Command("12")
@TestCaseDefault("120NUN19700101    010000AA|AB|AH|AJ|AO|")
@TestCasePopulated("121YYY19700101    010000AApatronIdentifier|ABitemIdentifier|AFscreenMessage|AGprintLine|AHdueDate|AJtitleIdentifier|AOinstitutionId|BHGBP|BKtransactionId|BT09|BVfeeAmount|CHitemProperties|CIY|CK010|")
public class CheckOutResponse extends Message {
    private static final long serialVersionUID = 9222995766948881317L;
    @PositionedField(start = 2, end = 2)
    private Boolean ok;
    @PositionedField(start = 3, end = 3)
    private Boolean renewalOk;
    @PositionedField(start = 4, end = 4)
    private Boolean magneticMedia;
    @PositionedField(start = 5, end = 5)
    private Boolean desensitize;
    @PositionedField(start = 6, end = 23)
    private Date transactionDate;
    @TaggedField
    private String institutionId;
    @TaggedField(FieldPolicy.REQUIRED)
    private String patronIdentifier;
    @TaggedField(FieldPolicy.REQUIRED)
    private String itemIdentifier;
    @TaggedField(FieldPolicy.REQUIRED)
    private String titleIdentifier;
    @TaggedField(FieldPolicy.REQUIRED)
    private String dueDate;
    @TaggedField(FieldPolicy.NOT_REQUIRED)
    private FeeType feeType;
    @TaggedField
    private Boolean securityInhibit;
    @TaggedField(FieldPolicy.NOT_REQUIRED)
    private CurrencyType currencyType;
    @TaggedField(FieldPolicy.NOT_REQUIRED)
    private String feeAmount;
    @TaggedField
    private MediaType mediaType;
    @TaggedField(FieldPolicy.NOT_REQUIRED)
    private String itemProperties;
    @TaggedField
    private String transactionId;
    @TaggedField
    private String screenMessage;
    @TaggedField
    private String printLine;

    public CurrencyType getCurrencyType() {
        return this.currencyType;
    }

    public Boolean isDesensitize() {
        return this.desensitize;
    }

    public String getDueDate() {
        return this.dueDate;
    }

    public String getFeeAmount() {
        return this.feeAmount;
    }

    public FeeType getFeeType() {
        return this.feeType;
    }

    public String getInstitutionId() {
        return this.institutionId;
    }

    public String getItemIdentifier() {
        return this.itemIdentifier;
    }

    public String getItemProperties() {
        return this.itemProperties;
    }

    public Boolean isMagneticMedia() {
        return this.magneticMedia;
    }

    public MediaType getMediaType() {
        return this.mediaType;
    }

    public Boolean isOk() {
        return this.ok;
    }

    public String getPatronIdentifier() {
        return this.patronIdentifier;
    }

    public String getPrintLine() {
        return this.printLine;
    }

    public Boolean isRenewalOk() {
        return this.renewalOk;
    }

    public String getScreenMessage() {
        return this.screenMessage;
    }

    public Boolean isSecurityInhibit() {
        return this.securityInhibit;
    }

    public String getTitleIdentifier() {
        return this.titleIdentifier;
    }

    public Date getTransactionDate() {
        return this.transactionDate;
    }

    public String getTransactionId() {
        return this.transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public void setTitleIdentifier(String titleIdentifier) {
        this.titleIdentifier = titleIdentifier;
    }

    public void setSecurityInhibit(Boolean securityInhibit) {
        this.securityInhibit = securityInhibit;
    }

    public void setScreenMessage(String screenMessage) {
        this.screenMessage = screenMessage;
    }

    public void setRenewalOk(Boolean renewalOk) {
        this.renewalOk = renewalOk;
    }

    public void setPrintLine(String printLine) {
        this.printLine = printLine;
    }

    public void setPatronIdentifier(String patronIdentifier) {
        this.patronIdentifier = patronIdentifier;
    }

    public void setOk(Boolean ok) {
        this.ok = ok;
    }

    public void setMediaType(MediaType mediaType) {
        this.mediaType = mediaType;
    }

    public void setMagneticMedia(Boolean magneticMedia) {
        this.magneticMedia = magneticMedia;
    }

    public void setItemProperties(String itemProperties) {
        this.itemProperties = itemProperties;
    }

    public void setItemIdentifier(String itemIdentifier) {
        this.itemIdentifier = itemIdentifier;
    }

    public void setInstitutionId(String institutionId) {
        this.institutionId = institutionId;
    }

    public void setFeeType(FeeType feeType) {
        this.feeType = feeType;
    }

    public void setFeeAmount(String feeAmount) {
        this.feeAmount = feeAmount;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public void setDesensitize(Boolean desensitize) {
        this.desensitize = desensitize;
    }

    public void setCurrencyType(CurrencyType currencyType) {
        this.currencyType = currencyType;
    }
}
