package com.wanghui.sip2model.model;


import com.wanghui.sip2model.annotations.Command;
import com.wanghui.sip2model.annotations.PositionedField;
import com.wanghui.sip2model.annotations.TestCaseDefault;
import com.wanghui.sip2model.annotations.TestCasePopulated;

/**
 * @auther wanghui
 * @create 2018-11-13 0:36
 * @Description
 */
@Command("94")
@TestCaseDefault("940")
@TestCasePopulated("941")
public class LoginResponse extends Message {
    private static final long serialVersionUID = -7739633345494042411L;
    @PositionedField(start = 2, end = 2)
    private Boolean ok;

    public Boolean isOk() {
        return this.ok;
    }

    public void setOk(Boolean ok) {
        this.ok = ok;
    }

}
