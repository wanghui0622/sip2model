package com.wanghui.sip2model.types.enumerations;

/**
 * @auther wanghui
 * @create 2018-11-13 0:36
 * @Description 枚举接口，用于获取枚举值。枚举编码等
 */
public interface AbstractEnumeration {
    @Override
    public abstract String toString();

    public abstract String getCode();

    public abstract AbstractEnumeration getKey(String code);
}
