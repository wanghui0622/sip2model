package com.wanghui.sip2model.types.enumerations;

/**
 * @auther wanghui
 * @create 2018-11-13 0:36
 * @Description
 */
public enum HoldType implements AbstractEnumeration {
    OTHER("1"),
    ANY_COPY("2"),
    SPECIFIC_COPY("3"),
    SUBLOCATION_COPY("4");

    private final String code;

    private HoldType(String code) {
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
        for (AbstractEnumeration i : HoldType.values()) {
            if (i.getCode().equals(code)) {
                return i;
            }
        }
        return null;
    }
}
