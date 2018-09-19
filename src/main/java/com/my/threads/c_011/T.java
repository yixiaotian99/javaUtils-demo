package com.my.threads.c_011;

import cn.hutool.core.thread.ThreadUtil;

/**
 * Created by sunjinwei on 2018/9/19.
 * 程序在执行过程中，如果出现异常，默认情况锁会被释放
 * 所以，在并发处理的过程中，有异常要多加小心，不然可能会发生不一致的情况。
 * 比如，在一个web app处理过程中，多个servlet线程共同访问同一个资源，这时如果异常处理不合适，
 * 在第一个线程中抛出异常，其他线程就会进入同步代码区，有可能会访问到异常产生时的数据。
 * 因此要非常小心的处理同步业务逻辑中的异常
 *
 * @author sunjinwei
 */
public class T {


    int count = 0;

    //模拟线程一直做处理，中间出异常锁会释放
    synchronized void m() {
        while (true) {
            count++;
            ThreadUtil.sleep(500);

            try {
                System.out.println(Thread.currentThread().getName() + " count: " + count);
            } catch (Exception e) {
                e.printStackTrace();
            }

            //模拟出现异常，并没有被try catch
            if (count == 3) {
                int i = 1 / 0;
            }
        }
    }


    public static void main(String[] args) {
        T t = new T();

        //模拟第一个线程
        new Thread(t::m, "t1").start();

        //模拟第二个线程，如果锁不释放，第二个线程永远也不会进入
        new Thread(t::m, "t2").start();
        //输出如下，说明当线程出现异常时，锁会自动释放
        //t1 count: 1
        //t1 count: 2
        //t1 count: 3
        //Exception in thread "t1" java.lang.ArithmeticException: / by zero
        //	at com.my.threads.c_011.T.m(T.java:34)
        //	at java.lang.Thread.run(Thread.java:748)
        //t2 count: 4
        //t2 count: 5
    }
}
