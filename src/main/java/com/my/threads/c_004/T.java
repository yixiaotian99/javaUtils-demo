package com.my.threads.c_004;

import cn.hutool.core.thread.ThreadUtil;

/**
 * Created by sunjinwei on 2018/9/18.
 *
 * @author sunjinwei
 *
 * 使用 synchroinzed 锁定静态方法
 */
public class T {

    private static int count = 0;

    public static synchronized void m(){  //相当于 synchroinzed(com.my.threads.c_004.T.class)
        count++;
        ThreadUtil.sleep(1000);
        System.out.println("count:" + count);
    }


    public static void mm(){
        synchronized (T.class){   //不能使用 this 关键字，static对象直接引用
            count++;
            ThreadUtil.sleep(1000);
            System.out.println("count:" + count);
        }
    }

    public static void main(String[] args) {
        //new Thread(() ->T.m()).start();   //count:1
        //new Thread(() ->T.m()).start();   //count:2
        //new Thread(() ->T.m()).start();   //count:3

        new Thread(() ->T.mm()).start();   //count:1
        new Thread(() ->T.mm()).start();   //count:2
        new Thread(() ->T.mm()).start();   //count:3
    }
}
