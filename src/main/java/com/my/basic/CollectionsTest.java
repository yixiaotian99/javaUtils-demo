package com.my.basic;

import com.google.common.collect.MapMaker;

import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by sunjinwei on 2018/10/17.
 *
 * @author sunjinwei
 * 测试使用集合类
 */
public class CollectionsTest {

    public static void main(String[] args) {
        MapTest mapTest = new MapTest();
        mapTest.test();
    }
}


class MapTest {

    /**
     * 多线程安全
     */
    private static final ConcurrentMap<String, Object> jsfMap = new MapMaker().makeMap();


    ExecutorService executors = Executors.newFixedThreadPool(16);
    private AtomicInteger atomicLoop = new AtomicInteger(0);


    public void test() {
        for (int i = 0; i < 20; i++) {
            System.out.println("执行:" + i);

            executors.submit(() -> {
                jsfMap.putIfAbsent("key" + atomicLoop.incrementAndGet(), "value");
            });
        }

        //一定关闭线程，否则主线程一直不结束
        executors.shutdown();

        while (true) {
            //打印时一定要判断线程关闭再打印，否则打印的元素会少
            if (executors.isShutdown()) {
                jsfMap.forEach((k, v) -> System.out.println(k + "," + v));
                break;
            }
        }
    }
}