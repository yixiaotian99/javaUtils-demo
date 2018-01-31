package com.my.basic;

import com.xiaoleilu.hutool.thread.ThreadUtil;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.TimeUnit;

/**
 * Created by sunjinwei on 2018/1/29.
 *
 * @author sunjinwei
 *         <p>
 *         创建 spring thread pool
 */
public class ThreadSpringPool {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("spring-threadPool.xml");
        ThreadPoolTaskExecutor executor = (ThreadPoolTaskExecutor) classPathXmlApplicationContext.getBean("executor");

        System.out.println(executor);

        for (int i = 0; i < 27; i++) {
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName() + "-------");
                    ThreadUtil.sleep(10, TimeUnit.SECONDS);
                }
            });
        }

        System.out.println("done....");
    }

}
