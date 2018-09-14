package com.my.jvm;

import lombok.Data;

/**
 * Created by sunjinwei on 2018/9/11.
 * -XX:-DoEscapeAnalysis -XX:-EliminateAllocations -XX:-UseTLAB -XX:+PrintGC -server
 * 不做逃逸分析、不做标量替换、不使用线程本地缓存、打印gc信息
 *
 * @author sunjinwei
 */
public class T01 {

    /**
     * 内部类
     */
    @Data
    class User {
        int id;
        String name;

        public User(int id, String name) {
            this.id = id;
            this.name = name;
        }
    }

    /**
     * 测试方法 不断创建新对象
     */
    void alloc(int i) {
        new User(i, "name" + i);
    }

    /**
     * 不断创建1千万的内部 user 对象，比较用时
     *
     * @param args
     */
    public static void main(String[] args) {
        T01 t01 = new T01();
        long s1 = System.currentTimeMillis();
        for (int i = 0; i < 10000000; i++) {
            t01.alloc(i);
        }
        long s2 = System.currentTimeMillis();
        System.out.println(s2 - s1);
    }
}
