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
 * 表名注解
 * @see https://blog.csdn.net/tanyunlong_nice/article/details/53560280
 */
@Target(ElementType.TYPE)  //作用域是类或接口
@Retention(RetentionPolicy.RUNTIME)   //注解类型，运行时注解
public @interface Table {

    String value();  //当只有一个变量时，属性名必须是value
}
