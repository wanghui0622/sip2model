package com.wanghui.sip2model.model;

import com.wanghui.sip2model.annotations.*;
import com.wanghui.sip2model.fields.FieldPolicy;

/**
 * @auther wanghui
 * @create 2018-11-13 0:36
 * @Description
 */
@Command("01")
@TestCaseDefault("01N19700101    010000AA|AC|AL|AO|")
@TestCasePopulated("01Y19700101    010000AApatronIdentifier|ACterminalPassword|ALblockedCardMessage|AOinstitutionId|")
public class BlockPatron extends Message {
    private static final long serialVersionUID = 7336173091305475737L;
    @PositionedField(start = 2, end = 2)
    private Boolean cardRetained;
    @PositionedField(start = 3, end = 20)
    private java.util.Date transactionDate;
    @TaggedField
    private String institutionId;
    @TaggedField
    private String blockedCardMessage;
    @TaggedField(FieldPolicy.REQUIRED)
    private String patronIdentifier;
    @TaggedField(FieldPolicy.REQUIRED)
    private String terminalPassword;

    public Boolean isCardRetained() {
        return this.cardRetained;
    }

    public void setCardRetained(Boolean cardRetained) {
        this.cardRetained = cardRetained;
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

    public String getBlockedCardMessage() {
        return this.blockedCardMessage;
    }

    public void setBlockedCardMessage(String blockedCardMessage) {
        this.blockedCardMessage = blockedCardMessage;
    }

    public String getPatronIdentifier() {
        return this.patronIdentifier;
    }

    public void setPatronIdentifier(String patronIdentifier) {
        this.patronIdentifier = patronIdentifier;
    }

    public String getTerminalPassword() {
        return this.terminalPassword;
    }

    public void setTerminalPassword(String terminalPassword) {
        this.terminalPassword = terminalPassword;
    }

}
