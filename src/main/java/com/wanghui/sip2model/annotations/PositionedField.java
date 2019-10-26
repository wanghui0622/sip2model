package com.wanghui.sip2model.annotations;


import com.wanghui.sip2model.fields.FieldPolicy;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @auther wanghui
 * @create 2018-11-13 0:36
 * @Description  自定义注解，定义SIP2命令中的长度固定的字段的强制性和开始和结束位置
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface PositionedField {
    FieldPolicy policy() default FieldPolicy.DEFAULT;
    int start();
    int end();
}
