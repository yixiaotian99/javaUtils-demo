package com.my.threads.c_025;

import cn.hutool.core.thread.ThreadUtil;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Created by sunjinwei on 2018/12/4.
 *
 * @author sunjinwei
 * <p>
 * 阻塞队列
 * <p>
 * ArrayBlockingQueue 数组队列锁是没有分离的，即生产与消费使用的是同一把锁
 * LinkedBlockingQueue 链表队列锁是分离的，生产是putLock 消费是takeLock
 * @see https://blog.csdn.net/USTC_Zn/article/details/54864244
 */
public class ArrayBlockingQueueTest {

    static BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(10);

    public static void main(String[] args) {

        //1个生产线程
        new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                try {
                    String msg = "a" + i;
                    blockingQueue.put(msg);  //如果队列满了就会等待，直到消费
                    ThreadUtil.sleep(10);
                    System.out.println("put+++ " + msg);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "p1").start();

//        //5个消费线程，不停消费
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                for (; ; ) {
                    try {
                        String msg = blockingQueue.take(); //当队列为空时一直等待
                        System.out.println("take-- " + msg);
                        ThreadUtil.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }, "c1").start();
        }

        ThreadUtil.sleep(10000);
        try {
            blockingQueue.put("再来一条");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
