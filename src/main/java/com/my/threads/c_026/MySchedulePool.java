package com.my.threads.c_026;

import cn.hutool.core.thread.ThreadUtil;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by sunjinwei on 2018/12/6.
 *
 * @author sunjinwei
 * <p>
 * 调度线程池
 */
public class MySchedulePool {

    public static void main(String[] args) {
        //调度线程池
        ScheduledExecutorService service = Executors.newScheduledThreadPool(5);

        for (int i = 0; i < 6; i++) {
            service.scheduleAtFixedRate(() -> {
                System.out.println(Thread.currentThread().getName() + "--");
            }, 0, 5, TimeUnit.SECONDS);
        }

        ThreadUtil.sleep(10000);
        service.shutdown();
    }

}
