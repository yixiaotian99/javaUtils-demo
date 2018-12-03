package com.my.threads.c_021;

import cn.hutool.core.thread.ThreadUtil;

import java.util.LinkedList;

/**
 * Created by sunjinwei on 2018/11/30.
 *
 * @author sunjinwei
 * <p>
 * 面试题：写一个固定容量同步容器，拥有put和get方法，以及getCount方法，
 * 能够支持2个生产者线程以及10个消费者线程的阻塞调用
 *
 * 使用 wait 和 notifyAll 实现
 */
public class T2<T> {

    //1. 模拟一个 固定 容器，使用List
    LinkedList list = new LinkedList();
    int count = 0;
    int MAX = 10;

    //2. 同步方法put  首先必须判断是否已经容器最大，这里必须不能使用if判断，因为多线程容器会不同步
    synchronized void put(T t) {
        //2.1 已经达到最大容量，不能再放，等待消费线程
        while (count == MAX) {
            try {
                System.out.println("p.. wait");
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        //没有到达最大容量，继续添加元素,唤起消费线程
        list.add(t);
        count++;
        this.notifyAll();
    }


    //3. 同步方法get 判断容器是不是空了
    synchronized T get() {
        //3.1 容量已清0，不能再取，等待生产线程
        while (count == 0) {
            try {
                System.out.println("c.. wait");
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        //容器中按序取值, 唤起
        T first = (T) list.removeFirst();
        count--;
        this.notifyAll();
        return first;
    }


    public static void main(String[] args) {
        T2 t1 = new T2();
        //4. 启动10个消费者线程进行消费(必须先启动)
        for(int i=0; i<10; i++){
            Thread tt1 = new Thread(()->{

                //4.1 模拟不断的消费，如果不持续消费，线程退出就没有消费者了
                while (true){
                    Object o = t1.get();
                    System.out.println("c-->" + o);
                    ThreadUtil.sleep(500);
                }
            }, "c1");
            tt1.start();
        }

        ThreadUtil.sleep(2000);

        //5. 启动2个生产者线程进行生产(后启动)
        for(int i=0; i<3; i++){
            Thread tt2 = new Thread(()->{

                //5.1 模拟不断的生产，如果不持续生产，线程退出就没有生产者了
                while (true){
                    String s = Thread.currentThread().getName() + "-->AAA";
                    t1.put(s);
                    System.out.println("p input " + s);
                    ThreadUtil.sleep(500);
                }
            });
            tt2.start();
        }

        //注意，生产者与消费者使用 while(true) 小心cpu爆满
    }
}
