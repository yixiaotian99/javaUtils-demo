package com.my.threads.c_026;

import java.util.concurrent.Executor;

/**
 * Created by sunjinwei on 2018/12/6.
 *
 * @author sunjinwei
 *
 * 认识下 Executor
 */
public class MyExecutor implements Executor {
    @Override
    public void execute(Runnable command) {
        command.run();
    }

    public static void main(String[] args) {
        MyExecutor myExecutor = new MyExecutor();
        myExecutor.execute(()->{
            System.out.println("输出");
        });
    }
}
