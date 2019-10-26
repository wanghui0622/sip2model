package com.wanghui.sip2model.model;

import com.wanghui.sip2model.annotations.*;
import com.wanghui.sip2model.fields.FieldPolicy;

/**
 * @auther wanghui
 * @create 2018-11-13 0:36
 * @Description
 */
@Command("38")
@TestCaseDefault("38N19700101    010000AA|AO|")
@TestCasePopulated("38Y19700101    010000AApatronIdentifier|AFscreenMessage|AGprintLine|AOinstitutionId|BKtransactionId|")
public class FeePaidResponse extends Message {
    private static final long serialVersionUID = 3684506970071368895L;
    @PositionedField(start = 2, end = 2)
    private Boolean paymentAccepted;
    @PositionedField(start = 3, end = 20)
    private java.util.Date transactionDate;
    @TaggedField
    private String institutionId;
    @TaggedField(FieldPolicy.REQUIRED)
    private String patronIdentifier;
    @TaggedField
    private String transactionId;
    @TaggedField
    private String screenMessage;
    @TaggedField
    private String printLine;

    public Boolean isPaymentAccepted() {
        return this.paymentAccepted;
    }

    public void setPaymentAccepted(Boolean paymentAccepted) {
        this.paymentAccepted = paymentAccepted;
    }

    public java.util.Date getTransactionDate() {
        return this.transactionDate;
    }

    public void setTransactionDate(java.util.Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getInstitutionId() {
        return this.institutionId;
    }

    public void setInstitutionId(String institutionId) {
        this.institutionId = institutionId;
    }

    public String getPatronIdentifier() {
        return this.patronIdentifier;
    }

    public void setPatronIdentifier(String patronIdentifier) {
        this.patronIdentifier = patronIdentifier;
    }

    public String getTransactionId() {
        return this.transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getScreenMessage() {
        return this.screenMessage;
    }

    public void setScreenMessage(String screenMessage) {
        this.screenMessage = screenMessage;
    }

    public String getPrintLine() {
        return this.printLine;
    }

    public void setPrintLine(String printLine) {
        this.printLine = printLine;
    }
}
