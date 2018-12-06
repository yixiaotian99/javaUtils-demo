package com.my.threads.c_026;

import cn.hutool.core.thread.ThreadUtil;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by sunjinwei on 2018/12/6.
 *
 * @author sunjinwei
 */
public class ThreadPool {

    public static void main(String[] args) {
        //定义只有5个线程的线程池
        ExecutorService service = Executors.newFixedThreadPool(5);

        //启动6个任务
        for (int i = 0; i < 6; i++) {
            service.submit(()->{
                System.out.println("thread name: " + Thread.currentThread().getName());
                ThreadUtil.sleep(1000);
            });
        }

        //java.util.concurrent.ThreadPoolExecutor@5792a0[Running, pool size = 5, active threads = 5, queued tasks = 1, completed tasks = 0]
        System.out.println(service);

        service.shutdown();
        System.out.println(service.isShutdown());  //true
        System.out.println(service.isTerminated()); //false


    }

}
