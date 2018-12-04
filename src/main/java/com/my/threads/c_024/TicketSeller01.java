package com.my.threads.c_024;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sunjinwei on 2018/12/3.
 *
 * @author sunjinwei
 * <p>
 * 有N张火车票，每张票都有一个编号
 * 同时有10个窗口对外售票
 * 请写一个模拟程序
 */
public class TicketSeller01 {

    //1. 使用容器List存储火车票
    static List<String> list = new ArrayList<>();

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
                    System.out.println("卖的票编号: " + list.remove(0));
                }
            }).start();
        }

        //因为没有线程同步，卖的票会重复，或者超售
    }

}
