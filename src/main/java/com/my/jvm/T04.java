package com.my.jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sunjinwei on 2018/9/11.
 * -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=d:\dump\jvm.dump -XX:+PrintGCDetails -Xms10m -Xmx10m -server
 * @author sunjinwei
 */
public class T04 {

    public static void main(String[] args) {
        List<Object> list = new ArrayList<>();
        for (int i = 0; i < 100000000; i++) {
            list.add(new byte[1024 * 1024]);
        }
    }

}
