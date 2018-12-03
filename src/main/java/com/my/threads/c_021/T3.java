package com.my.threads.c_021;

import cn.hutool.core.thread.ThreadUtil;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by sunjinwei on 2018/11/30.
 *
 * @author sunjinwei
 * <p>
 * 面试题：写一个固定容量同步容器，拥有put和get方法，以及getCount方法，
 * 能够支持2个生产者线程以及10个消费者线程的阻塞调用
 * <p>
 * 使用 wait 和 notifyAll 实现.
 * <p>
 * 使用 lock 和 condition 来实现
 */
public class T3<T> {

    //1. 模拟一个 固定 容器，使用List
    LinkedList<T> list = new LinkedList();
    int count = 0;
    int MAX = 10;

    //2. 使用 lock 和 condition 精确指定生产者、消费者线程
    Lock lock = new ReentrantLock();
    Condition producer = lock.newCondition();
    Condition consumer = lock.newCondition();

    //3. 同步 put 方法, 注意不需要写 synchronized 关键字
    void put(T t) {
        //3.1 如果容器数量不等于最大值，一直生产
        try {
            lock.lock();
            while (count == MAX) {
                producer.await(); //一定注意，是调用的 await() 而不是 wait()
            }

            list.add(t);
            ++count;
            consumer.signalAll(); //通知消费者消费
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    //4. 同步 get 方法
    public T get() {
        T o = null;
        try {
            lock.lock();
            while (count == 0) {
                consumer.await();
            }

            o = list.removeFirst();
            count--;
            producer.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return o;
    }


    public static void main(String[] args) {
        //5. 启动线程消费、生产
        T3 t = new T3();
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(() -> {
                //模拟不断的消费
                while (true) {
                    Object o = t.get();
                    System.out.println("c.." + o);
                    ThreadUtil.sleep(500);  //防止cpu过高
                }
            });
            thread.start();
        }

        ThreadUtil.sleep(1000);

        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(() -> {
                //模拟不断的消费
                while (true) {
                    t.put(new Object());
                    System.out.println(Thread.currentThread().getName() + " p..");
                    ThreadUtil.sleep(500); //防止cpu过高
                }
            });
            thread.start();
        }
    }
}
