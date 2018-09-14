package com.my.jvm;

/**
 * Created by sunjinwei on 2018/9/11.
 *
 * @author sunjinwei
 */
public class T03 {

    public static void main(String[] args) {
        System.out.println("total memory:" + Runtime.getRuntime().totalMemory());
        System.out.println("free memory:" + Runtime.getRuntime().freeMemory());

        byte[] bytes = new byte[1024 * 1024];

        System.out.println("total memory:" + Runtime.getRuntime().totalMemory());
        System.out.println("free memory:" + Runtime.getRuntime().freeMemory());
    }

}
