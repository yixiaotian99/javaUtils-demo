package com.my.threads.c_019;

import cn.hutool.core.thread.ThreadUtil;

import java.util.ArrayList;
import java.util.List;

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
 *
 * @author sunjinwei
 */
public class MyContainer3 {

    //1.定义集合用于实现容器  添加volatile内存可见性
    volatile List lists = new ArrayList();

    //2.实现size方法
    public int getSize() {
        return lists.size();
    }

    //3.定义锁对象
    final Object o = new Object();

    //4. 监控方法先启动，一直等待
    public void watch() {
        synchronized (o) {
            System.out.println("t2启动");
            if (getSize() != 5) {
                //释放锁, 此线程不再执行
                try {
                    o.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("兄弟，到第5个了");
        }
    }

    //5. 添加方法
    public void add() {
        synchronized (o) {
            System.out.println("t1启动");
            for (int i = 0; i < 10; i++) {
                lists.add(new Object());
                System.out.println("add " + i);

                //不释放锁，唤醒其他线程
                if (getSize() == 5) {
                    o.notify();
                }
                ThreadUtil.sleep(1000);
            }
        }
    }


    public static void main(String[] args) {
        MyContainer3 m1 = new MyContainer3();
        new Thread(m1::watch, "t2").start();
        ThreadUtil.sleep(1000);
        new Thread(m1::add, "t1").start();
    }
}
