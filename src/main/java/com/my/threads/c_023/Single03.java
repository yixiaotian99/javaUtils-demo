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
 * <p>
 * 使用颗粒更细的方法锁定
 */
public class Single03 {

    //类加载即创建
    private static Single03 single03;

    //私有构造方法
    private Single03() {
        System.out.println("private constructor");
    }

    //公有getXX 方法 懒加载
    public static Single03 getSingle() {

        if (single03 == null) {
            synchronized (Single03.class) {
                if (single03 == null) {
                    single03 = new Single03();
                }
            }
        }

        System.out.println(single03);
        return single03;
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
