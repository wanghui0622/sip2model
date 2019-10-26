package com.wanghui.sip2model.model;

import com.wanghui.sip2model.annotations.*;
import com.wanghui.sip2model.fields.FieldPolicy;
import com.wanghui.sip2model.types.enumerations.MediaType;

import java.util.Date;

/**
 * @auther wanghui
 * @create 2018-11-13 0:36
 * @Description
 */
@Command("10")
@TestCaseDefault("100NUN19700101    010000AB|AO|AQ|")
@TestCasePopulated("101YYY19700101    010000AApatronIdentifier|ABitemIdentifier|AFscreenMessage|AGprintLine|AJtitleIdentifier|AOinstitutionId|AQpermanentLocation|CHitemProperties|CK010|CLsortBin|")
public class CheckInResponse extends Message {
    private static final long serialVersionUID = -3403534383487215711L;
    @PositionedField(start = 2, end = 2)
    private Boolean ok;
    @PositionedField(start = 3, end = 3)
    private Boolean resensitize;
    @PositionedField(start = 4, end = 4)
    private Boolean magneticMedia;
    @PositionedField(start = 5, end = 5)
    private Boolean alert;
    @PositionedField(start = 6, end = 23)
    private Date transactionDate;
    @TaggedField
    private String institutionId;
    @TaggedField(FieldPolicy.REQUIRED)
    private String itemIdentifier;
    @TaggedField(FieldPolicy.REQUIRED)
    private String permanentLocation;
    @TaggedField(FieldPolicy.NOT_REQUIRED)
    private String titleIdentifier;
    @TaggedField
    private String sortBin;
    @TaggedField(FieldPolicy.NOT_REQUIRED)
    private String patronIdentifier;
    @TaggedField
    private MediaType mediaType;
    @TaggedField(FieldPolicy.NOT_REQUIRED)
    private String itemProperties;
    @TaggedField
    private String screenMessage;
    @TaggedField
    private String printLine;

    public Boolean isAlert() {
        return this.alert;
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

    public String getPermanentLocation() {
        return this.permanentLocation;
    }

    public String getPrintLine() {
        return this.printLine;
    }

    public Boolean isResensitize() {
        return this.resensitize;
    }

    public String getScreenMessage() {
        return this.screenMessage;
    }

    public String getSortBin() {
        return this.sortBin;
    }

    public String getTitleIdentifier() {
        return this.titleIdentifier;
    }

    public Date getTransactionDate() {
        return this.transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public void setTitleIdentifier(String titleIdentifier) {
        this.titleIdentifier = titleIdentifier;
    }

    public void setSortBin(String sortBin) {
        this.sortBin = sortBin;
    }

    public void setScreenMessage(String screenMessage) {
        this.screenMessage = screenMessage;
    }

    public void setResensitize(Boolean resensitize) {
        this.resensitize = resensitize;
    }

    public void setPrintLine(String printLine) {
        this.printLine = printLine;
    }

    public void setPermanentLocation(String permanentLocation) {
        this.permanentLocation = permanentLocation;
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

    public void setAlert(Boolean alert) {
        this.alert = alert;
    }
}
