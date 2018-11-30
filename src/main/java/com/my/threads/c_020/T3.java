package com.my.threads.c_020;

import cn.hutool.core.thread.ThreadUtil;

import java.util.concurrent.TimeUnit;
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
 *
 * 可以使用 reentrantlock 进行tryLock 锁定操作，如果超过指定时间未锁定，线程可以决定是否继续等待，
 * 需要特别注意的是，使用tryLock进行锁定，无论是否锁定，方法都将继续执行
 */
public class T3 {

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

        boolean isLocked = false;
        try {
            //尝试锁定，无论是否锁定到指定时间程序都将继续往下执行
            isLocked = lock.tryLock(2, TimeUnit.SECONDS);
            System.out.println("m2...."); //如果没有判断是否锁定，会在等待2秒后直接打印结果

            if(!isLocked){
                System.out.println("未锁定，程序退出！");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if(isLocked){
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        T3 t1 = new T3();
        new Thread(t1::m1, "t1").start();

        ThreadUtil.sleep(1000);

        new Thread(t1::m2, "t2").start();
    }
}
