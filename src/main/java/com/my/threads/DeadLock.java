package com.my.threads;

import cn.hutool.core.thread.ThreadUtil;

import static com.my.threads.DeadLock.o1;
import static com.my.threads.DeadLock.o2;

/**
 * Created by sunjinwei on 2018/12/3.
 *
 * @author sunjinwei
 * @see http://www.runoob.com/java/thread-deadlock.html
 * 死锁：多个线程同时被锁定，一个线程等待另外一个线程的锁释放
 * <p>
 * 思路：定义两个对象o1, o2
 * 线程1执行时锁定对象o1, 需要资源o2
 * 线程2执行时锁定对象o2，需要资源o1
 */
public class DeadLock {

    public static Object o1 = new Object();
    public static Object o2 = new Object();

    public static void main(String[] args) {
        Thread t1 = new Thread(new ThreadA());
        Thread t2 = new Thread(new ThreadB());

        t1.start();
        ThreadUtil.sleep(500);
        t2.start();
    }
}


class ThreadA implements Runnable {
    @Override
    public void run() {
        synchronized (o1) {
            System.out.println(Thread.currentThread().getName() + " 执行");
            ThreadUtil.sleep(1000);
            synchronized (o2) {
                System.out.println(Thread.currentThread().getName() + " 锁定o1需要o2");
            }
        }
    }
}

class ThreadB implements Runnable {
    @Override
    public void run() {
        synchronized (o2) {
            System.out.println(Thread.currentThread().getName() + " 执行");
            ThreadUtil.sleep(1000);
            synchronized (o1) {
                System.out.println(Thread.currentThread().getName() + " 锁定o2需要o1");
            }
        }
    }
}