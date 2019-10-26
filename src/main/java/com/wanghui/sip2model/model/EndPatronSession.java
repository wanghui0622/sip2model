package com.wanghui.sip2model.model;

import com.wanghui.sip2model.annotations.*;
import com.wanghui.sip2model.fields.FieldPolicy;

/**
 * @auther wanghui
 * @create 2018-11-13 0:36
 * @Description
 */
@Command("35")
@TestCaseDefault("3519700101    010000AA|AC|AD|AO|")
@TestCasePopulated("3519700101    010000AApatronIdentifier|ACterminalPassword|ADpatronPassword|AOinstitutionId|")
public class EndPatronSession extends Message {
    private static final long serialVersionUID = -1263417214546161837L;
    @PositionedField(start = 2, end = 19)
    private java.util.Date transactionDate;
    @TaggedField()
    private String institutionId;
    @TaggedField(FieldPolicy.REQUIRED)
    private String patronIdentifier;
    @TaggedField(FieldPolicy.REQUIRED)
    private String terminalPassword;
    @TaggedField(FieldPolicy.REQUIRED)
    private String patronPassword;

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

    public String getTerminalPassword() {
        return this.terminalPassword;
    }

    public void setTerminalPassword(String terminalPassword) {
        this.terminalPassword = terminalPassword;
    }

    public String getPatronPassword() {
        return this.patronPassword;
    }

    public void setPatronPassword(String patronPassword) {
        this.patronPassword = patronPassword;
    }

}
