package com.my.threads.c_009;

import cn.hutool.core.thread.ThreadUtil;

/**
 * Created by sunjinwei on 2018/9/19.
 *
 * @author sunjinwei
 *
 * 一个同步方法可以调用另外一个同步方法吗？可以
 * 一个线程已经拥有某个对象的锁，再次申请的时候还会获取到对象锁吗？可以
 */
public class T {

    //同步方法一 方法调用另一个同步方法
    synchronized void m1(){
        System.out.println(Thread.currentThread().getName() + " m1"); //Thread-0 m1
        ThreadUtil.sleep(1000);
        m2();
    }

    //同步方法二
    synchronized void m2(){
        ThreadUtil.sleep(2000);
        System.out.println(Thread.currentThread().getName() + " m2"); //Thread-0 m1
    }

    public static void main(String[] args) {
        T t = new T();
        new Thread(t::m1).start();
    }
}
