package com.my.threads.c_012;

import cn.hutool.core.thread.ThreadUtil;

/**
 * volatile 关键字，使一个变量在多个线程间可见
 * A B线程都用到一个变量，java默认是A线程中保留一份copy，这样如果B线程修改了该变量，则A线程未必知道
 * 使用volatile关键字，会让所有线程都会读到变量的修改值
 * <p>
 * 在下面的代码中，running是存在于堆内存的t对象中
 * 当线程t1开始运行的时候，会把running值从内存中读到t1线程的工作区，在运行过程中直接使用这个copy，并不会每次都去
 * 读取堆内存，这样，当主线程修改running的值之后，t1线程感知不到，所以不会停止运行
 * <p>
 * 使用volatile，将会强制所有线程都去堆内存中读取running的值
 * <p>
 * 可以阅读这篇文章进行更深入的理解
 * http://www.cnblogs.com/nexiyi/p/java_memory_model_and_thread.html
 * <p>
 * volatile并不能保证多个线程共同修改running变量时所带来的不一致问题，也就是说volatile不能替代synchronized
 * Created by sunjinwei on 2018/9/21.
 *
 * @author sunjinwei
 * <p>
 * 内存可见性
 */
public class T {

    /*volatile*/ boolean running = true;

    void m() {
        System.out.println("m start");
        while (running) {

        }
        System.out.println("m end");
    }


    public static void main(String[] args) {
        T t = new T();

        //启动新线程
        new Thread(t::m, "t1").start();

        //按理说我将标志位设置为false那么while就会停止了，但实际会是这样吗？
        ThreadUtil.sleep(2000);
        t.running = false;
    }
}
