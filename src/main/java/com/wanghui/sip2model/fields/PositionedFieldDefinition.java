package com.wanghui.sip2model.fields;

/**
 * @auther wanghui
 * @create 2018-11-13 0:36
 * @Description 制定位置字段定义
 */
public class PositionedFieldDefinition extends FieldDefinition {
    public int start;
    public int end;
    @SuppressWarnings("unused")
    private final String tag = null;

    protected PositionedFieldDefinition(String name, int start, int end, FieldDefinition d, FieldPolicy policy) {
        super(name, d, policy);
        this.start = start;
        this.end = end;
    }

    public PositionedFieldDefinition(int start, int end) {
        this.start = start;
        this.end = end;
        this.policy = FieldPolicy.DEFAULT;
    }

    public PositionedFieldDefinition(int start, int end, FieldPolicy policy) {
        this.start = start;
        this.end = end;
        this.policy = policy;
    }
}
