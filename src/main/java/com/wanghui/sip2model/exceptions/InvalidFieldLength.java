package com.wanghui.sip2model.exceptions;

/**
 * @auther wanghui
 * @create 2018-11-13 0:36
 * @Description  字段长度非法异常
 */
public class InvalidFieldLength extends Exception {

    private static final long serialVersionUID = 5690108640895112742L;
    private String field;
    private int maxLength;

    public InvalidFieldLength(String field, int maxLength) {
        this.field = field;
        this.maxLength = maxLength;
    }

    @Override
    public String getMessage() {
        return this.field + " - Field Length: " + this.maxLength + " characters";
    }
}
