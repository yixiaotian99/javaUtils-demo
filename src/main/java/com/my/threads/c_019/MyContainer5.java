package com.my.threads.c_019;

import cn.hutool.core.thread.ThreadUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * 实现一个容器，提供两个方法，add，size
 * 写两个线程，线程1添加10个元素到容器中，线程2实现监控元素的个数，当个数到5个时，线程2给出提示并结束
 * Created by sunjinwei on 2018/11/19.
 * <p>
 * 给lists添加volatile之后，t2能够接到通知，但是，t2线程的死循环很浪费cpu，如果不用死循环，该怎么做呢？
 * <p>
 * 这里使用wait和notify做到，wait会释放锁，而notify不会释放锁
 * 需要注意的是，运用这种方法，必须要保证t2先执行，也就是首先让t2监听才可以
 * <p>
 * 阅读下面的程序，并分析输出结果
 * 可以读到输出结果并不是size=5时t2退出，而是t1结束时t2才接收到通知而退出
 * 想想这是为什么？
 * <p>
 * notify之后，t1必须释放锁，t2退出后，也必须notify，通知t1继续执行
 * 整个通信过程比较繁琐
 * <p>
 * 使用Latch（门闩）替代wait notify来进行通知
 * 好处是通信方式简单，同时也可以指定等待时间
 * 使用await和countdown方法替代wait和notify
 * CountDownLatch不涉及锁定，当count的值为零时当前线程继续运行
 * 当不涉及同步，只是涉及线程通信的时候，用synchronized + wait/notify就显得太重了
 * 这时应该考虑countdownlatch/cyclicbarrier/semaphore
 *
 * @author sunjinwei
 */
public class MyContainer5 {

    //1.定义集合用于实现容器  添加volatile内存可见性
    volatile List lists = new ArrayList();

    //2.实现size方法
    public int getSize() {
        return lists.size();
    }

    //3.定义门闩
    CountDownLatch lock = new CountDownLatch(1);

    //4. 监控方法先启动，一直等待
    public void watch() {
        if (getSize() != 5) {
            try {
                //如果size!=5门闩就一直关着，等待被唤醒
                lock.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("嗨，兄弟，5个了啊");
        }
    }

    //5. 添加方法
    public void add() {
        for (int i = 0; i < 10; i++) {
            lists.add(new Object());
            System.out.println("add " + i);

            //当 size=5 时打开门闩，唤醒其他线程
            if (getSize() == 5) {
                lock.countDown();
            }
        }
    }


    public static void main(String[] args) {
        MyContainer5 m1 = new MyContainer5();
        new Thread(m1::watch, "t2").start();
        ThreadUtil.sleep(1000);
        new Thread(m1::add, "t1").start();
    }
}
