package com.my.threads.c_019;

import cn.hutool.core.thread.ThreadUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 实现一个容器，提供两个方法，add，size
 * 写两个线程，线程1添加10个元素到容器中，线程2实现监控元素的个数，当个数到5个时，线程2给出提示并结束
 * Created by sunjinwei on 2018/11/19.
 *
 * @author sunjinwei
 */
public class MyContainer2 {

    //1.定义集合用于实现容器  添加volatile内存可见性
    volatile List lists = new ArrayList();

    //2.实现size方法
    public int getSize() {
        return lists.size();
    }

    //3. 添加方法
    public void add() {
        for (int i = 0; i < 10; i++) {
            lists.add(new Object());
            System.out.println("add " + i);
            ThreadUtil.sleep(500);
        }
    }


    //4. 监控方法
    public void watch() {
        while (true) {
            if (getSize() == 5) {
                System.out.println("兄弟,到第 5 个了啊！");
                break;
            }
        }
        System.out.println("watch 结束...");
    }

    public static void main(String[] args) {
        MyContainer2 m1 = new MyContainer2();
        new Thread(m1::add, "t1").start();
        new Thread(m1::watch, "t2").start();
    }
}
