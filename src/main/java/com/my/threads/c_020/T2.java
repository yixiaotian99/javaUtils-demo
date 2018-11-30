package com.my.threads.c_020;

import cn.hutool.core.thread.ThreadUtil;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by sunjinwei on 2018/11/30.
 *
 * @author sunjinwei
 * <p>
 * 使用 synchronized 锁定this对象，只有当m1执行完成后，才能执行m2
 *
 * 使用reentrantlock代替synchronized
 * 但是必须必须必须手动释放锁，重要的事说三遍，synchronized 可以自动释放锁，但是reentrantlock 必须在finally中手动释放
 */
public class T2 {

    Lock lock = new ReentrantLock();


    void m1() {
        lock.lock(); //锁定 synchronized(this)
        try {
            for (int i = 0; i < 5; i++) {
                ThreadUtil.sleep(1000);
                System.out.println(i);
            }
        }finally {
            lock.unlock();
        }
    }

    void m2() {
        lock.lock();
        System.out.println("m2....");
        lock.unlock();
    }

    public static void main(String[] args) throws InterruptedException {
        T2 t1 = new T2();
//        new Thread(t1::m1, "t1").start();
//
//        ThreadUtil.sleep(1000);
//
//        new Thread(t1::m2, "t2").start();

        Thread td1 = new Thread(t1::m1);
        td1.start();

        ThreadUtil.sleep(1000);

        Thread td2 = new Thread(t1::m2);
        td2.start();

        //主线程阻塞，等待子线程结束
        td1.join();
        td2.join();
    }
}
