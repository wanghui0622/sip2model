package com.wanghui.sip2model.exceptions;

/**
 * @auther wanghui
 * @create 2018-11-13 0:36
 * @Description  非空字段校验异常
 */
public class MandatoryFieldOmitted extends Exception {

    private static final long serialVersionUID = -152536357897100116L;

    private String field;

    public MandatoryFieldOmitted(String field) {
        this.field = field;
    }

    @Override
    public String getMessage() {
        return this.field + " cannot be empty";
    }
}
