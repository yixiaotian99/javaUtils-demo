package com.my.threads.c_002;

import cn.hutool.core.thread.ThreadUtil;

/**
 * Created by sunjinwei on 2018/9/18.
 *
 * @author sunjinwei
 * <p>
 * 使用 synchroized 锁定 this 对象， 简化 c_001/T 写法
 */
public class T {

    private int count = 0;

    public void m() {
        synchronized (this) {
            count++;
            ThreadUtil.sleep(1000);
            System.out.println("count:" + count);
        }
    }

    public static void main(String[] args) {
        T t1 = new T();
        new Thread(t1::m, "t1").start();  //count:1
        new Thread(t1::m, "t1").start();  //count:2
        new Thread(t1::m, "t1").start();  //count:3

//        //使用 synchronized(this) 锁定的是堆对象，多个 new 出来对象是不同堆对象，synchronized 不起作用
//        T t1 = new T();
//        T t2 = new T();
//        T t3 = new T();
//        new Thread(t1::m, "t1").start();  //count:1
//        new Thread(t2::m, "t2").start();  //count:1
//        new Thread(t3::m, "t3").start();  //count:1
    }
}
