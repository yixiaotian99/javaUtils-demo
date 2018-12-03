package com.my.threads.c_023;

import java.util.Arrays;

/**
 * Created by sunjinwei on 2018/12/3.
 *
 * @author sunjinwei
 * @see http://www.cnblogs.com/xudong-bupt/p/3433643.html
 * <p>
 * 不使用同步锁的单例模式
 * <p>
 * 使用同步方法锁定
 */
public class Single02 {

    //类加载即创建
    private static Single02 single02;

    //私有构造方法
    private Single02() {
        System.out.println("private constructor");
    }

    //公有getXX 方法 懒加载
    public static synchronized Single02 getSingle() {
        if (single02 == null) {
            single02 = new Single02();
        }
        System.out.println(single02);
        return single02;
    }

    /**
     * 创建100个线程并调用getSingle方法
     *
     * @param args
     */
    public static void main(String[] args) {
        Thread[] myArr = new Thread[100];
        for (int i = 0; i < myArr.length; i++) {
            myArr[i] = new Thread(() -> {
                getSingle();
            });
        }

        Arrays.asList(myArr).forEach(tt -> tt.start());
    }
}
