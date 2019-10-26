package com.wanghui.sip2model.annotations;


import com.wanghui.sip2model.fields.FieldPolicy;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @auther wanghui
 * @create 2018-11-13 0:36
 * @Description  自定义注解，定义SIP2命令中的字段名称、长度以及强制性要求；
 * 其中，tag：SIP2协议规定的可变长度属性值的属性标识。
 *      length：标识属性值长度
 *      policy：属性要求，是否为必须、非必须、默认
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Field {
    String tag() default "";
    int length() default 0;
    FieldPolicy policy() default FieldPolicy.DEFAULT;
}
