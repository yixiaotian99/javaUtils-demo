package com.my.threads.c_025;

import cn.hutool.core.thread.ThreadUtil;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * Created by sunjinwei on 2018/12/4.
 *
 * @author sunjinwei
 * <p>
 * 延时队列 指定元素按时间顺序取出
 */
public class DelayQueueTest {

    //延时队列
    static DelayQueue delayQueue = new DelayQueue();

    //需要实现Delayed接口,重写 getDelay、compareTo方法
    static class MyTask implements Delayed {
        long rt;

        public MyTask(long rt) {
            this.rt = rt;
        }

        @Override
        public long getDelay(TimeUnit unit) {
            return unit.convert(rt - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
        }

        @Override
        public int compareTo(Delayed o) {
            if (this.getDelay(TimeUnit.MILLISECONDS) < o.getDelay(TimeUnit.MILLISECONDS)) {
                return -1;
            } else if (this.getDelay(TimeUnit.MILLISECONDS) > o.getDelay(TimeUnit.MILLISECONDS)) {
                return 1;
            } else {
                return 0;
            }
        }

        @Override
        public String toString() {
            return "rt=" + rt;
        }
    }

    public static void main(String[] args) {
        long now = System.currentTimeMillis();
        MyTask myTask = new MyTask(now + 1000);
        MyTask myTask2 = new MyTask(now + 5000);
        MyTask myTask3 = new MyTask(now + 3000);
        MyTask myTask4 = new MyTask(now + 2000);

        //添加到延时队列
        delayQueue.put(myTask);
        delayQueue.put(myTask2);
        delayQueue.put(myTask3);
        delayQueue.put(myTask4);
        System.out.println(delayQueue);

        //打印队列
        ThreadUtil.sleep(2000);
        for (int i = 0; i < 4; i++) {
            try {
                System.out.println(delayQueue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
