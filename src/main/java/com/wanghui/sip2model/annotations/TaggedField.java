package com.wanghui.sip2model.annotations;

import com.wanghui.sip2model.fields.FieldPolicy;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @auther wanghui
 * @create 2018-11-13 0:36
 * @Description  自定义注解，定义SIP2命令中字段的强制性要求
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface TaggedField {
    FieldPolicy value() default FieldPolicy.DEFAULT;
}