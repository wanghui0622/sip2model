package com.wanghui.sip2model.model;

import com.wanghui.sip2model.annotations.Command;
import com.wanghui.sip2model.annotations.PositionedField;
import com.wanghui.sip2model.annotations.TestCaseDefault;
import com.wanghui.sip2model.annotations.TestCasePopulated;
import com.wanghui.sip2model.types.enumerations.ProtocolVersion;
import com.wanghui.sip2model.types.enumerations.StatusCode;

/**
 * @auther wanghui
 * @create 2018-11-13 0:36
 * @Description
 */
@Command("99")
@TestCaseDefault("9900002.00")
@TestCasePopulated("9921231.00")
public class SCStatus extends Message {
    private static final long serialVersionUID = -6198644705404364776L;
    @PositionedField(start = 2, end = 2)
    private StatusCode statusCode;
    @PositionedField(start = 3, end = 5)
    private Integer maxPrintWidth;
    @PositionedField(start = 6, end = 9)
    private ProtocolVersion protocolVersion;

    public Integer getMaxPrintWidth() {
        return this.maxPrintWidth;
    }

    public ProtocolVersion getProtocolVersion() {
        return this.protocolVersion;
    }

    public StatusCode getStatusCode() {
        return this.statusCode;
    }

    public void setStatusCode(StatusCode statusCode) {
        this.statusCode = statusCode;
    }

    public void setProtocolVersion(ProtocolVersion protocolVersion) {
        this.protocolVersion = protocolVersion;
    }

    public void setMaxPrintWidth(Integer maxPrintWidth) {
        this.maxPrintWidth = maxPrintWidth;
    }
}
