package com.wanghui.sip2model.model;

import com.wanghui.sip2model.annotations.*;
import com.wanghui.sip2model.fields.FieldPolicy;

/**
 * @auther wanghui
 * @create 2018-11-13 0:36
 * @Description
 */
@Command("19")
@TestCaseDefault("1919700101    010000AB|AO|CH|")
@TestCasePopulated("1919700101    010000ABitemIdentifier|ACterminalPassword|AOinstitutionId|CHitemProperties|")
public class ItemStatusUpdate extends Message {
    private static final long serialVersionUID = -2127793191374183987L;
    @PositionedField(start = 2, end = 19)
    private java.util.Date transactionDate;
    @TaggedField
    private String institutionId;
    @TaggedField(FieldPolicy.REQUIRED)
    private String itemIdentifier;
    @TaggedField(FieldPolicy.NOT_REQUIRED)
    private String terminalPassword;
    @TaggedField(FieldPolicy.REQUIRED)
    private String itemProperties;


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

    public String getItemIdentifier() {
        return this.itemIdentifier;
    }

    public void setItemIdentifier(String itemIdentifier) {
        this.itemIdentifier = itemIdentifier;
    }

    public String getTerminalPassword() {
        return this.terminalPassword;
    }

    public void setTerminalPassword(String terminalPassword) {
        this.terminalPassword = terminalPassword;
    }

    public String getItemProperties() {
        return this.itemProperties;
    }

    public void setItemProperties(String itemProperties) {
        this.itemProperties = itemProperties;
    }
}
