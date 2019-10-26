package com.wanghui.sip2model.model;

import com.wanghui.sip2model.annotations.*;
import com.wanghui.sip2model.fields.FieldPolicy;
import com.wanghui.sip2model.types.enumerations.CurrencyType;
import com.wanghui.sip2model.types.enumerations.Language;
import com.wanghui.sip2model.types.flagfields.PatronStatus;

import java.util.Date;

@Command("24")
@TestCaseDefault("24              00019700101    010000AA|AE|AO|")
@TestCasePopulated("24YYYYYYYYYYYYYY02719700101    010000AApatronIdentifier|AEpersonalName|AFscreenMessage|AGprintLine|AOinstitutionId|BHGBP|BLY|BVfeeAmount|CQY|")
public class PatronStatusResponse extends Message {
    private static final long serialVersionUID = 163945073911230183L;
    @PositionedField(start = 2, end = 15)
    private PatronStatus patronStatus = new PatronStatus();
    @PositionedField(start = 16, end = 18)
    private Language language;
    @PositionedField(start = 19, end = 36)
    private Date transactionDate;
    @TaggedField
    private String institutionId;
    @TaggedField(FieldPolicy.REQUIRED)
    private String patronIdentifier;
    @TaggedField
    private String personalName;
    @TaggedField
    private Boolean validPatron;
    @TaggedField
    private Boolean validPatronPassword;
    @TaggedField(FieldPolicy.NOT_REQUIRED)
    private CurrencyType currencyType;
    @TaggedField(FieldPolicy.NOT_REQUIRED)
    private String feeAmount;
    @TaggedField
    private String screenMessage;
    @TaggedField
    private String printLine;

    public CurrencyType getCurrencyType() {
        return this.currencyType;
    }

    public String getFeeAmount() {
        return this.feeAmount;
    }

    public String getInstitutionId() {
        return this.institutionId;
    }

    public Language getLanguage() {
        return this.language;
    }

    public String getPrintLine() {
        return this.printLine;
    }

    public String getPatronIdentifier() {
        return this.patronIdentifier;
    }

    public PatronStatus getPatronStatus() {
        return this.patronStatus;
    }

    public String getPersonalName() {
        return this.personalName;
    }

    public String getScreenMessage() {
        return this.screenMessage;
    }

    public Date getTransactionDate() {
        return this.transactionDate;
    }

    public Boolean isValidPatron() {
        return this.validPatron;
    }

    public Boolean isValidPatronPassword() {
        return this.validPatronPassword;
    }

    public void setValidPatronPassword(Boolean validPatronPassword) {
        this.validPatronPassword = validPatronPassword;
    }

    public void setValidPatron(Boolean validPatron) {
        this.validPatron = validPatron;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public void setScreenMessage(String screenMessage) {
        this.screenMessage = screenMessage;
    }

    public void setPrintLine(String printLine) {
        this.printLine = printLine;
    }

    public void setPersonalName(String personalName) {
        this.personalName = personalName;
    }

    @Deprecated
    public void setPatronStatus(PatronStatus patronStatus) {
        this.patronStatus = patronStatus;
    }

    public void setPatronIdentifier(String patronIdentifier) {
        this.patronIdentifier = patronIdentifier;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public void setInstitutionId(String institutionId) {
        this.institutionId = institutionId;
    }

    public void setFeeAmount(String feeAmount) {
        this.feeAmount = feeAmount;
    }

    public void setCurrencyType(CurrencyType currencyType) {
        this.currencyType = currencyType;
    }
}
