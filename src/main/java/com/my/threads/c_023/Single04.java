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
 * <p>
 * 使用内部类实现懒加载
 */
public class Single04 {

    //私有构造方法
    private Single04() {
        System.out.println("private constructor");
    }

    //私有内部类
    private static class Inner{
        private static Single04 single04 = new Single04();
    }

    //公有getXX 方法 懒加载
    public static Single04 getSingle() {
        System.out.println(Inner.single04);
        return Inner.single04;
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
