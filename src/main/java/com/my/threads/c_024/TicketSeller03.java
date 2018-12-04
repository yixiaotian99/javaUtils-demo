package com.my.threads.c_024;

import cn.hutool.core.thread.ThreadUtil;

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
 * <p>
 * 使用 Vector 能解决问题吗？不能！
 * <p>
 * 必须来保证判断与删除是同步操作，才能保证线程间没有问题
 */
public class TicketSeller03 {

    //1. 使用 List 存储火车票
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
                while (true) {  //模拟一直在卖
                    synchronized (list) { //同步操作，锁定list
                        if (list.size() <= 0) { //卖完了退出线程
                            break;
                        }

                        ThreadUtil.sleep(10);
                        System.out.println("卖的票编号: " + list.remove(0)); //按顺序出票
                    }
                }
            }).start();
        }
    }

}
