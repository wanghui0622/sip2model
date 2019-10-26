package com.wanghui.sip2model.model;

import com.wanghui.sip2model.annotations.*;
import com.wanghui.sip2model.fields.FieldPolicy;

import java.util.Date;

/**
 * @auther wanghui
 * @create 2018-11-13 0:36
 * @Description
 */
@Command("36")
@TestCaseDefault("36N19700101    010000AA|AO|")
@TestCasePopulated("36Y19700101    010000AApatronIdentifier|AFscreenMessage|AGprintLine|AOinstitutionId|")
public class EndSessionResponse extends Message {
    private static final long serialVersionUID = 8955079727656656773L;
    @PositionedField(start = 2, end = 2)
    private Boolean endSession;
    @PositionedField(start = 3, end = 20)
    private Date transactionDate;
    @TaggedField
    private String institutionId;
    @TaggedField(FieldPolicy.REQUIRED)
    private String patronIdentifier;
    @TaggedField
    private String screenMessage;
    @TaggedField
    private String printLine;

    public Boolean isEndSession() {
        return this.endSession;
    }

    public void setEndSession(Boolean endSession) {
        this.endSession = endSession;
    }

    public Date getTransactionDate() {
        return this.transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
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
