package com.my.threads.c_026;

import cn.hutool.core.thread.ThreadUtil;

import java.util.concurrent.*;

/**
 * Created by sunjinwei on 2018/12/6.
 *
 * @author sunjinwei
 * <p>
 * 认识 Future
 */
public class MyFuture {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //自己创建线程池，阻塞等待线程池返回结果
        FutureTask<Integer> intFutureTask = new FutureTask<Integer>(() -> {
            ThreadUtil.sleep(2000);
            return 1000;
        });

        //启动线程等待结果返回
        Thread tt = new Thread(intFutureTask);
        tt.start();
        tt.join();
        System.out.println("future done: " + intFutureTask.isDone()); //true
        System.out.println("blocking wait: " + intFutureTask.get());  //1000


        //使用线程池创建
        ExecutorService service = Executors.newSingleThreadExecutor();
        Future<Integer> ff = service.submit(() -> {
            ThreadUtil.sleep(3000);
            return 33;
        });
        System.out.println("executor return: "+ ff.get()); //33
        service.shutdown();

    }

}
