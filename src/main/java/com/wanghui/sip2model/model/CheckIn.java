package com.wanghui.sip2model.model;


import com.wanghui.sip2model.annotations.*;
import com.wanghui.sip2model.fields.FieldPolicy;

import java.util.Date;

/**
 * @auther wanghui
 * @create 2018-11-13 0:36
 * @Description
 */
@Command("09")
@TestCaseDefault("09N19700101    010000                  AB|AC|AO|AP|")
@TestCasePopulated("09Y19700101    01000019700101    010000ABitemIdentifier|ACterminalPassword|AOinstitutionId|APcurrentLocation|BIY|CHitemProperties|")
public class CheckIn extends Message {
    private static final long serialVersionUID = -7321140594135175919L;
    @PositionedField(start = 2, end = 2)
    private Boolean noBlock;
    @PositionedField(start = 3, end = 20)
    private Date transactionDate;
    @PositionedField(start = 21, end = 38)
    private Date returnDate;
    @TaggedField(FieldPolicy.REQUIRED)
    private String currentLocation;
    @TaggedField
    private String institutionId;
    @TaggedField(FieldPolicy.REQUIRED)
    private String itemIdentifier;
    @TaggedField(FieldPolicy.REQUIRED)
    private String terminalPassword;
    @TaggedField(FieldPolicy.NOT_REQUIRED)
    private String itemProperties;
    @TaggedField
    private Boolean cancel;

    public Boolean isCancel() {
        return this.cancel;
    }

    public String getCurrentLocation() {
        return this.currentLocation;
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

    public Boolean isNoBlock() {
        return this.noBlock;
    }

    public Date getReturnDate() {
        return this.returnDate;
    }

    public String getTerminalPassword() {
        return this.terminalPassword;
    }

    public Date getTransactionDate() {
        return this.transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public void setTerminalPassword(String terminalPassword) {
        this.terminalPassword = terminalPassword;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public void setNoBlock(Boolean noBlock) {
        this.noBlock = noBlock;
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

    public void setCurrentLocation(String currentLocation) {
        this.currentLocation = currentLocation;
    }

    public void setCancel(Boolean cancel) {
        this.cancel = cancel;
    }
}
