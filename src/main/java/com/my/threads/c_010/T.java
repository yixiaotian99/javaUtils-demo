package com.my.threads.c_010;

import cn.hutool.core.thread.ThreadUtil;

/**
 * Created by sunjinwei on 2018/9/19.
 *
 * @author sunjinwei
 * <p>
 * 子类调用父类同步方法，方法的重入
 */
public class T {

    //父类同步方法
    synchronized void m() {
        System.out.println("m started");
        ThreadUtil.sleep(1000);
        System.out.println("m end");
    }
}

class TT extends T{
    @Override
    synchronized void m() {
        System.out.println("child started");
        super.m();
        System.out.println("child end");
    }

    public static void main(String[] args) {
        new TT().m();
        //child started
        //m started
        //m end
        //child end
    }
}


