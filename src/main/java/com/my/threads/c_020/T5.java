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
 * <p>
 * 指定为公平锁，每个线程分配一次，性能铁定没有非公平锁效率好，因为要计算哪个线程执行了哪一步
 */
public class T5 {

    //定义公平锁 true 为公平锁 默认为非公平锁
    Lock lock = new ReentrantLock(true);

    void run() {
        for (int i = 0; i < 5; i++) {
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + "----- 获取锁");
                ThreadUtil.sleep(500);
            } finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        T5 t5 = new T5();
        new Thread(t5::run, "t1").start();
        new Thread(t5::run, "t2").start();
    }
}
