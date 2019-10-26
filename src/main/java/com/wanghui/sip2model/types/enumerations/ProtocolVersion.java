package com.wanghui.sip2model.types.enumerations;

/**
 * @auther wanghui
 * @create 2018-11-13 0:36
 * @Description
 */
public enum ProtocolVersion implements AbstractEnumeration {
    VERSION_2_00("2.00"),
    VERSION_1_00("1.00");

    private final String code;

    private ProtocolVersion(String code) {
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
        for (AbstractEnumeration i : ProtocolVersion.values()) {
            if (i.getCode().equals(code)) {
                return i;
            }
        }
        return null;
    }
}
