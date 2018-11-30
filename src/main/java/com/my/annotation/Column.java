package com.my.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by sunjinwei on 2018/11/29.
 *
 * @author sunjinwei
 * <p>
 * 列名注解
 *
 * @see https://blog.csdn.net/tanyunlong_nice/article/details/53560280
 */
@Target(ElementType.FIELD)          //作用域为字段
@Retention(RetentionPolicy.RUNTIME) //运行时注解
public @interface Column {

    String name(); //注解只有一个时必须为 value

    String value();

    String desc() default "";
}
