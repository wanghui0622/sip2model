package com.wanghui.sip2model.model;


import com.wanghui.sip2model.annotations.*;

/**
 * @auther wanghui
 * @create 2018-11-13 0:36
 * @Description
 */
@Command("93")
@TestCaseDefault("93  CN|CO|")
@TestCasePopulated("93UPCNloginUserId|COloginPassword|CPlocationCode|")
public class Login extends Message {
    private static final long serialVersionUID = -5732581787865741081L;
    @PositionedField(start = 2, end = 2)
    private String UIDAlgorithm;
    @PositionedField(start = 3, end = 3)
    private String PWDAlgorithm;
    @TaggedField
    private String loginUserId;
    @TaggedField
    private String loginPassword;
    @TaggedField
    private String locationCode;

    public String getUIDAlgorithm() {
        return this.UIDAlgorithm;
    }

    public void setUIDAlgorithm(String UIDAlgorithm) {
        this.UIDAlgorithm = UIDAlgorithm;
    }

    public String getPWDAlgorithm() {
        return this.PWDAlgorithm;
    }

    public void setPWDAlgorithm(String PWDAlgorithm) {
        this.PWDAlgorithm = PWDAlgorithm;
    }

    public String getLoginUserId() {
        return this.loginUserId;
    }

    public void setLoginUserId(String loginUserId) {
        this.loginUserId = loginUserId;
    }

    public String getLoginPassword() {
        return this.loginPassword;
    }

    public void setLoginPassword(String loginPassword) {
        this.loginPassword = loginPassword;
    }

    public String getLocationCode() {
        return this.locationCode;
    }

    public void setLocationCode(String locationCode) {
        this.locationCode = locationCode;
    }

}
