package com.wanghui.sip2model.types.enumerations;

/**
 * @auther wanghui
 * @create 2018-11-13 0:36
 * @Description 资费类型
 */
public enum FeeType implements AbstractEnumeration {
    OTHER("01"),
    ADMINISTRATIVE("02"),
    DAMAGE("03"),
    OVERDUE("04"),
    PROCESSING("05"),
    RENTAL("06"),
    REPLACEMENT("07"),
    COMPUTER_ACCESS_CHARGE("08"),
    HOLD_FEE("09");

    private final String code;

    private FeeType(String code) {
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
        for (AbstractEnumeration i : FeeType.values()) {
            if (i.getCode().equals(code)) {
                return i;
            }
        }
        return null;
    }
}
