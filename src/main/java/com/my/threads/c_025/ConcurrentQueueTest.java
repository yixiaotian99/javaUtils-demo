package com.my.threads.c_025;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by sunjinwei on 2018/12/4.
 *
 * @author sunjinwei
 * <p>
 * 使用普通的队列功能
 */
public class ConcurrentQueueTest {

    public static void main(String[] args) {
        //使用普通的队列功能
        Queue<String> linkedQueue = new ConcurrentLinkedQueue<>();


        //添加10个元素
        for (int i = 0; i < 10; i++) {
            linkedQueue.add("A" + i);
        }

        //打印队列
        System.out.println("queue: " + linkedQueue);

        //移除并返回队列头部元素
        String poll = linkedQueue.poll();

        System.out.println("remove and return header value: " + poll);
        System.out.println("queue list: " + linkedQueue.size());

        //仅仅返回队列头部元素
        String peek = linkedQueue.peek();
        System.out.println("return header value: " + peek);
        System.out.println("queue list: " + linkedQueue.size());
    }

}
