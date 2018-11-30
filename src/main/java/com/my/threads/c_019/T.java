package com.my.threads.c_019;

/**
 * Created by sunjinwei on 2018/11/19.
 *
 * @author sunjinwei
 */
public class T {
    public static void main(String[] args) {
        String a = "hello2";

        final String b = "hello";
        String d = "hello";

        String c = b + 2;
        String e = d + 2;


        System.out.println((a == c));
        System.out.println((a == e));
    }
}
