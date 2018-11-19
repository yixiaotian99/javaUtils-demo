package com.my.threads.c_017;

import cn.hutool.core.thread.ThreadUtil;

/**
 * Created by sunjinwei on 2018/11/19.
 *
 * @author sunjinwei
 *
 * 锁定的是堆对象，不是栈对象
 */
public class T {


    /**
     * 锁定当前对象，死循环一直打印
     */
    Object o = new Object();

    void m() {
        synchronized (o) {
            while (true) {
                System.out.println(Thread.currentThread().getName() + " start");
                ThreadUtil.sleep(1000);
            }
        }
    }

    public static void main(String[] args) {
        T t = new T();

        //启动第一个线程执行死循环打印
        new Thread(t::m, "t1").start();
        ThreadUtil.sleep(2000);

        //重置对象o指向新堆内存对象，启动新线程
        t.o = new Object();
        new Thread(t::m, "t2").start();

        /**
         * 输出打印结果，说明重置了o对象，线程1的锁已经被释放
         t1 start
         t1 start
         t1 start
         t2 start
         */
    }
}
