package com.my.threads.c_024;

import cn.hutool.core.thread.ThreadUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * Created by sunjinwei on 2018/12/3.
 *
 * @author sunjinwei
 * <p>
 * 有N张火车票，每张票都有一个编号
 * 同时有10个窗口对外售票
 * 请写一个模拟程序
 *
 * 使用 Vector 能解决问题吗？不能！
 */
public class TicketSeller02 {

    //1. 使用同步容器 Vector 存储火车票，能解决问题吗？
    static Vector<String> list = new Vector<>();

    static {
        for (int i = 0; i < 10000; i++) {
            list.add("tick: " + i);
        }
    }

    public static void main(String[] args) {
        //模拟10个窗口卖票
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                while (list.size() > 0) {
                    ThreadUtil.sleep(10);
                    System.out.println("卖的票编号: " + list.remove(0)); //java.lang.ArrayIndexOutOfBoundsException: Array index out of range: 0
                }
            }).start();
        }

        //试问为什么使用了同步容器 Vector 但还是不能解决同步问题？
        //因为 list.size 判断与 list.remove 是分离的，并不是原子操作
    }

}
