package com.my.threads.c_025;

import java.util.*;
import java.util.concurrent.CountDownLatch;

/**
 * Created by sunjinwei on 2018/12/4.
 *
 * @author sunjinwei
 * <p>
 * 高并发下Map问题
 * @see http://blog.csdn.net/sunxianghuang/article/details/52221913
 * <p>
 * 仔细看下面的打印会出问题吗？
 * <p>
 * <p>
 * 如果不添加阻塞，打印时其实线程并没有完全启动完成，可能只启动了50个线程就开始打印了
 * <p>
 * 使用 跳表 保证高并发排序, 但是注意对于字符串排序比较问题
 * key--82069,value--2519
 * key--8207,value--1233
 * key--82070,value--7173
 *
 * 使用同步hashtable
 *
 * 使用非同步hashMap 导致死链
 * @see https://joshuaastraypw.github.io/2017/04/03/HashMap%E6%98%AF%E5%A6%82%E4%BD%95%E4%BA%A7%E7%94%9F%E6%AD%BB%E9%94%81/
 */
public class ConcurrentMapTest5 {

    public static void main(String[] args) {
        //非同步容器hashmap 很容易就死掉了，因为形成死链
        Map<String, String> map = new HashMap<>();

        //转换为同步容器map
        Map<String, String> synchronizedMap = Collections.synchronizedMap(map);

        //定义100个线程，每个线程随机存储 1000个 int数字
        Random random = new Random();
        Thread[] ths = new Thread[100];

        //定义100个门闩
        CountDownLatch latch = new CountDownLatch(ths.length);

        for (int i = 0; i < ths.length; i++) {
            ths[i] = new Thread(() -> {
                for (int j = 0; j < 10000; j++) {
                    synchronizedMap.put("key--" + random.nextInt(100000), "value--" + random.nextInt(100000));
                }
                latch.countDown();
            });
        }

        //启动线程
        Arrays.asList(ths).forEach(tt -> tt.start());

        //门闩等待
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //打印map值
        Set<Integer> mapSize = new HashSet<>();
        synchronizedMap.forEach((k, v) -> {
            System.out.println(k + "," + v);
            mapSize.add(synchronizedMap.size());
        });

        System.out.println("map大小:" + mapSize.size());
    }


}
