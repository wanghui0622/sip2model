package com.wanghui.sip2model.model;

import com.wanghui.sip2model.annotations.*;
import com.wanghui.sip2model.fields.FieldPolicy;
import com.wanghui.sip2model.types.enumerations.Language;
import com.wanghui.sip2model.types.flagfields.PatronStatus;

/**
 * @auther wanghui
 * @create 2018-11-13 0:36
 * @Description
 */
@Command("26")
@TestCaseDefault("26              00019700101    010000AA|AE|AO|")
@TestCasePopulated("26YYYYYYYYYYYYYY02719700101    010000AApatronIdentifier|AEpersonalName|AFscreenMessage|AGprintLine|AOinstitutionId|BLY|CQY|")
public class PatronEnableResponse extends Message {
    private static final long serialVersionUID = 5941325479001778479L;
    @PositionedField(start = 2, end = 15)
    private PatronStatus patronStatus = new PatronStatus();
    @PositionedField(start = 16, end = 18)
    private Language language;
    @PositionedField(start = 19, end = 36)
    private java.util.Date transactionDate;
    @TaggedField
    private String institutionId;
    @TaggedField(FieldPolicy.REQUIRED)
    private String patronIdentifier;
    @TaggedField
    private String personalName;
    @TaggedField
    private Boolean validPatronPassword;
    @TaggedField
    private String screenMessage;
    @TaggedField
    private String printLine;
    @TaggedField
    private Boolean validPatron;

    public PatronStatus getPatronStatus() {
        return this.patronStatus;
    }

    /**
     * Use getPatronStatus().set(PatronStatus.FIELD)
     * getPatronStatus().unset(PatronStatus.FIELD) getPatronStatus().unsetAll()
     */
    @Deprecated
    public void setPatronStatus(PatronStatus patronStatus) {
        this.patronStatus = patronStatus;
    }

    public Language getLanguage() {
        return this.language;
    }

    public void setLanguage(Language language) {
        this.language = language;
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

    public String getPersonalName() {
        return this.personalName;
    }

    public void setPersonalName(String personalName) {
        this.personalName = personalName;
    }

    public Boolean isValidPatron() {
        return this.validPatron;
    }

    public void setValidPatron(Boolean validPatron) {
        this.validPatron = validPatron;
    }

    public Boolean isValidPatronPassword() {
        return this.validPatronPassword;
    }

    public void setValidPatronPassword(Boolean validPatronPassword) {
        this.validPatronPassword = validPatronPassword;
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
