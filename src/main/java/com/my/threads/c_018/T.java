package com.my.threads.c_018;

/**
 * Created by sunjinwei on 2018/11/19.
 *
 * @author sunjinwei
 * <p>
 * 不要使用字符串作为锁定对象
 */
public class T {

    String s = "hello";

    void m() {
        synchronized (s) {
            System.out.println("千万不要以字符串当做锁定对象，否则可能出现莫名其妙的问题");
        }
    }

}
