package com.my.threads.c_020;

import cn.hutool.core.thread.ThreadUtil;

/**
 * Created by sunjinwei on 2018/11/30.
 *
 * @author sunjinwei
 * <p>
 * 使用 synchronized 锁定this对象，只有当m1执行完成后，才能执行m2
 */
public class T1 {

    synchronized void m1() {
        for (int i = 0; i < 5; i++) {
            ThreadUtil.sleep(1000);
            System.out.println(i);
        }
    }

    synchronized void m2() {
        System.out.println("m2....");
    }

    public static void main(String[] args) {
        T1 t1 = new T1();
        new Thread(t1::m1, "t1").start();

        ThreadUtil.sleep(1000);

        new Thread(t1::m2, "t2").start();
    }
}
