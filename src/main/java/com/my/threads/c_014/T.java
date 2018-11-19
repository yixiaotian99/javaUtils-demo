package com.my.threads.c_014;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sunjinwei on 2018/11/19.
 *
 * @author sunjinwei
 *
 * synchronizedv既保证了内存可见性，又保证原子性
 */
public class T {

    /**
     * 对数字进行递增操作
     */
    int count = 0;

    synchronized void add() {
        for (int i = 0; i < 10000; i++) {
            count++;
        }
    }


    /**
     * 开启10个线程对数字进行递增
     *
     * @param args
     */
    public static void main(String[] args) {
        T t = new T();
        List<Thread> threadList = new ArrayList();

        //开启10个线程放到集合中
        for (int j = 0; j < 10; j++) {
            Thread td = new Thread(t::add, "t");
            threadList.add(td);
        }

        //启动10个线程
        threadList.forEach(o -> o.start());

        //当前线程等待所有子线程结束,否则可能主线程已经退出，子线程还未执行完
        threadList.forEach(o -> {
            try {
                o.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        //使用 synchronized 一定输出100000
        System.out.println("最后输出count: " + t.count);
    }
}
