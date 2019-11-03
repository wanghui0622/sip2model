package com.wanghui.sip2model.fields;

/**
 * @auther wanghui
 * @create 2018-11-13 0:36
 * @Description 标记字段定义
 */
public class TaggedFieldDefinition extends FieldDefinition {
    /**
     * 分隔符，标记字段与字段需要通过竖线"|"分割。
     */
    public static final char TERMINATOR = '|';

    protected TaggedFieldDefinition(String name, FieldDefinition d, FieldPolicy policy) {
        super(name, d, policy);
    }

    public TaggedFieldDefinition(FieldPolicy policy) {
        this.policy = policy;
    }
}
