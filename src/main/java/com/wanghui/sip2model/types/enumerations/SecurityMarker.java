package com.wanghui.sip2model.types.enumerations;

/**
 * @auther wanghui
 * @create 2018-11-13 0:36
 * @Description
 */
public enum SecurityMarker implements AbstractEnumeration {
    OTHER("00"),
    NONE("01"),
    TATTLE_TAPE("02"),
    WHISPER_TAPE("03");

    private final String code;

    private SecurityMarker(String code) {
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
        for (AbstractEnumeration i : SecurityMarker.values()) {
            if (i.getCode().equals(code)) {
                return i;
            }
        }
        return null;
    }
}
