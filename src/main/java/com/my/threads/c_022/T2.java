package com.my.threads.c_022;

import cn.hutool.core.thread.ThreadUtil;

/**
 * Created by sunjinwei on 2018/12/3.
 *
 * @author sunjinwei
 * <p>
 * threadlocl 线程局部变量
 * <p>
 * threadlocal 是使用空间换时间，synchronized 是使用时间换空间
 */
public class T2 {

    static ThreadLocal<Person2> p = new ThreadLocal<>();

    public static void main(String[] args) {

        new Thread(() -> {
            ThreadUtil.sleep(2000);
            System.out.println(Thread.currentThread().getName() + "--" + p.get().name); //NPE
        }, "t1").start();

        new Thread(() -> {
            ThreadUtil.sleep(1000);
            p.set(new Person2());
            System.out.println(Thread.currentThread().getName() + "--" + p.get().name); //t2--zhangsan
        }, "t2").start();
    }

    static class Person2 {
        String name = "zhangsan";
    }

}

