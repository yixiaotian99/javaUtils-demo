package com.my.basic;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by sunjinwei on 2018/1/23.
 *
 * @author sunjinwei
 *         测试使用自增
 */
public class ThreadTest2 {

    public static void main(String[] args) {

        for (int i = 0; i < 1000; i++) {
            new Thread(new ThreadTest3()).start();
        }
    }
}


class ThreadTest3 implements Runnable {

    /**
     * 静态变量计数铁定有问题
     */
    //private static int count = 0;

    /**
     * 局部变量可以正常计算
     */
    //private int count = 0;

    /**
     * 静态变量 AtomicInteger 可以正常计数
     */
    private static AtomicInteger count = new AtomicInteger(0);

    @Override
    public void run() {
        System.out.println("当前线程: " + Thread.currentThread().getName() + ", started..");

        for (int i = 0; i < 5; i++) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //count++;
            count.incrementAndGet();
        }

        System.out.println("当前线程: " + Thread.currentThread().getName() + ", 计数: " + count);
    }
}