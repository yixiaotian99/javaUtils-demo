package com.my.threads.c_007;

import cn.hutool.core.thread.ThreadUtil;

/**
 * Created by sunjinwei on 2018/9/18.
 *
 * @author sunjinwei
 * <p>
 * 在同步方法执行时是否可以执行非同步方法? 可以的，可以想像你在厕所蹲坑，外面有阿姨打扫卫生
 */
public class T {

    public synchronized void m() {
        System.out.println(Thread.currentThread().getName() + " start");
        ThreadUtil.sleep(3000);
    }

    public void mm() {
        System.out.println(Thread.currentThread().getName() + " start");
        ThreadUtil.sleep(1000);
    }

    public static void main(String[] args) {
        T t = new T();
        new Thread(t::m, "t1").start();
        new Thread(t::mm, "t2").start();
    }
}
