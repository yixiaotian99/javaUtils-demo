package com.my.threads.c_016;

/**
 * Created by sunjinwei on 2018/11/19.
 *
 * @author sunjinwei
 *
 * 锁定范围要尽量缩小，提高性能
 */
public class T {

    synchronized void m1(){
        System.out.println("我锁定在方法上，性能比较低");
    }

    void m2(){
        System.out.println("我锁定在对象上，只对需要同步的地方同步，性能高");
        synchronized (this){
            System.out.println("我是锁定的内容");
        }
    }

}
