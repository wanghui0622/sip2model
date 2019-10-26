package com.wanghui.sip2model.types.enumerations;

/**
 * @auther wanghui
 * @create 2018-11-13 0:36
 * @Description
 */
public enum StatusCode implements AbstractEnumeration {
    OK("0"),
    OUT_OF_PAPER("1"),
    SHUTTING_DOWN("2");

    private final String code;

    private StatusCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return this.getCode();
    }

    @Override
    public final String getCode() {
        return this.code;
    }

    @Override
    public final AbstractEnumeration getKey(String code) {
        for (AbstractEnumeration i : StatusCode.values()) {
            if (i.getCode().equals(code)) {
                return i;
            }
        }
        return null;
    }
}
