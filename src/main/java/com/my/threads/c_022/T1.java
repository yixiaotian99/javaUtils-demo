package com.my.threads.c_022;

import cn.hutool.core.thread.ThreadUtil;

/**
 * Created by sunjinwei on 2018/12/3.
 *
 * @author sunjinwei
 * <p>
 * threadlocl 线程局部变量
 */
public class T1 {

    volatile static Person p = new Person();

    public static void main(String[] args) {
        System.out.println(p.name);  //zhangsan

        new Thread(() -> {
            ThreadUtil.sleep(2000);
            System.out.println(Thread.currentThread().getName() + "--" + p.name); //t1--lisi
        }, "t1").start();

        new Thread(() -> {
            ThreadUtil.sleep(1000);
            p.name = "lisi";
            System.out.println(Thread.currentThread().getName() + "--" + p.name); //t2--lisi
        }, "t2").start();
    }
}


class Person {
    String name = "zhangsan";
}