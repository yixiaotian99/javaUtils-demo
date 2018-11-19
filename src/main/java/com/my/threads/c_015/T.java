package com.my.threads.c_015;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by sunjinwei on 2018/11/19.
 *
 * @author sunjinwei
 *
 * 使用原子类对象 AtomicXX 性能更好
 */
public class T {

    /**
     * 对数字进行递增操作
     */
    //int count = 0;
    AtomicInteger count = new AtomicInteger();

    void add() {
        for (int i = 0; i < 10000; i++) {
            count.incrementAndGet();
            /*if(count.get()<9999){  //千万不要这样写，因为这样会导致本来的原子操作变成非原子操作
                count.incrementAndGet();
            }*/
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

        //使用 AtomicInteger 一定输出100000
        System.out.println("最后输出count: " + t.count);
    }
}
