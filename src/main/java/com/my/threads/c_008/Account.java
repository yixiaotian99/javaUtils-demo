package com.my.threads.c_008;

import cn.hutool.core.thread.ThreadUtil;

/**
 * Created by sunjinwei on 2018/9/19.
 *
 * @author sunjinwei
 *
 * 脏读 (写操作没有完成，读操作已经发生并完成)
 */
public class Account {

    /**
     * 用户名、账号余额
     */
    String name;
    double account;

    /**
     * 模拟存钱
     */
    public synchronized void setAccount(String name, double account){
        this.name = name;

        //关键步骤，模拟设置值时逻辑处理延时
        ThreadUtil.sleep(2000);

        this.account = account;
    }

    /**
     * 模拟取钱
     */
    public double getAccount(String name){
        return this.account;
    }

    public static void main(String[] args) {
        Account account = new Account();

        //启动一个线程去存钱
        new Thread(() -> account.setAccount("zhangsan", 100)).start();

        System.out.println("模拟立即取钱:" + account.getAccount("zhangsan")); //模拟立即取钱:0.0

        ThreadUtil.sleep(3000);

        System.out.println("模拟稍后取钱:" + account.getAccount("zhangsan")); //模拟稍后取钱:100.0
    }
}
