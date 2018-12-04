package com.my.threads.c_025;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by sunjinwei on 2018/12/4.
 *
 * @author sunjinwei
 * <p>
 * 高并发下Map问题
 * @see http://blog.csdn.net/sunxianghuang/article/details/52221913
 * <p>
 * 仔细看下面的打印会出问题吗？
 */
public class ConcurrentMapTest {

    public static void main(String[] args) {
        //高并发Map
        Map<String, String> map = new ConcurrentHashMap<>();

        //定义100个线程，每个线程随机存储 1000个 int数字
        Random random = new Random();
        Thread[] ths = new Thread[100];
        for (int i = 0; i < ths.length; i++) {
            ths[i] = new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    map.put("key--" + random.nextInt(100000), "value--" + random.nextInt(100000));
                }
            });
        }

        //启动线程
        Arrays.asList(ths).forEach(tt -> tt.start());

        //打印map值
        Set<Integer> mapSize = new HashSet<>();
        map.forEach((k, v) -> {
            System.out.println(k + "," + v);
            mapSize.add(map.size());
        });

        System.out.println("map大小:" + mapSize.size()); //map大小:4521 每次运行map大小都不固定，因为线程没有全启动，可能只启动50个线程
    }


}
