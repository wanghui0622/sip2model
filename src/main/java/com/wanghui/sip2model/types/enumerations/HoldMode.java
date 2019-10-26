package com.wanghui.sip2model.types.enumerations;

/**
 * @auther wanghui
 * @create 2018-11-13 0:36
 * @Description
 */
public enum HoldMode implements AbstractEnumeration {
    CHANGE("*"),
    ADD("+"),
    DELETE("-");

    private final String code;

    private HoldMode(String code) {
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
        for (AbstractEnumeration i : HoldMode.values()) {
            if (i.getCode().equals(code)) {
                return i;
            }
        }
        return null;
    }
}
