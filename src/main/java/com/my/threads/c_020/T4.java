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
 * <p>
 * 使用reentrantlock代替synchronized
 * 但是必须必须必须手动释放锁，重要的事说三遍，synchronized 可以自动释放锁，但是reentrantlock 必须在finally中手动释放
 * <p>
 * 可以使用 reentrantlock 进行tryLock 锁定操作，如果超过指定时间未锁定，线程可以决定是否继续等待，
 * 需要特别注意的是，使用tryLock进行锁定，无论是否锁定，方法都将继续执行
 * <p>
 * 使用 reentrantlock 调用 lockInterruptibly 方法， 可以对线程interrupt方法做出响应
 * 在一个线程等待锁的过程中，可以被打断
 */
public class T4 {


    public static void main(String[] args) {
        Lock lock = new ReentrantLock();

        //线程1锁定后睡觉了
        Thread t1 = new Thread(() -> {
            lock.lock();

            try {
                System.out.println("t1 start...");
                ThreadUtil.sleep(Integer.MAX_VALUE);
                System.out.println("t1 end...");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        });
        t1.start();

        //线程2对于打断事件做出响应
        Thread t2 = new Thread(() -> {
            try {
                lock.lockInterruptibly();
                System.out.println("t2 start...");
                ThreadUtil.sleep(5000);
                System.out.println("t2 end...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        });
        t2.start();

        //打断线程2
        ThreadUtil.sleep(2000);
        t2.interrupt();
    }
}
