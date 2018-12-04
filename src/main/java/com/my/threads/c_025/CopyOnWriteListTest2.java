package com.my.threads.c_025;

import java.util.*;

/**
 * Created by sunjinwei on 2018/12/4.
 *
 * @author sunjinwei
 * <p>
 * 使用 copy on write list
 * 适合于读多写少的情况，比如缓存
 * <p>
 * 启动100个线程，每个线程添加1000个元素
 *
 * 使用 Collections.synchronizedXX 做同步容器
 */
public class CopyOnWriteListTest2 {

    public static void main(String[] args) {
        Random random = new Random();

        //将非同步容器，包装成同步容器
        List<String> list = new ArrayList<>();
        List<String> synchronizedList = Collections.synchronizedList(list);

        //创建100个线程
        Thread[] ths = new Thread[100];
        for (int i = 0; i < ths.length; i++) {
            ths[i] = new Thread(() -> {
                for (int j = 0; j < 10000; j++) {
                    synchronizedList.add("a" + random.nextInt(1000));
                }
            });
        }

        long start = System.currentTimeMillis();
        Arrays.asList(ths).forEach(tt -> tt.start());
        Arrays.asList(ths).forEach(tt -> {
            //非常重要，直接会导致结果的正确性。阻塞线程直到主线程结束，与countdownlatch效果一样
            try {
                tt.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        long end = System.currentTimeMillis();
        System.out.println("用时: " + (end - start) + ",list size: " + synchronizedList.size());
    }

}
