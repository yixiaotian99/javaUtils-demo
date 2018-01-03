package com.my.basic;

import java.util.concurrent.CountDownLatch;

/**
 * Created by sunjinwei on 2018/1/3.
 * 测试使用同步方法计数器每次递减，每执行一次 down() 方法计算数就减少1 直到为0
 */
public class CountDownLatchTest {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("主线程开始了...");
        CountDownLatch countDownLatch = new CountDownLatch(10);  //开启一个计数器，里面有10个数

        //开 10 个线程跑，开的线程数必须和你定义的计数器个数相等，否则起不到锁定效果，意思就是开10个锁定线程
        for(int i=0; i<10; i++){
            new Thread(new ThreadCountTest(countDownLatch, i)).start();
        }

        countDownLatch.await();  //必须使用 await 方法等待所有线程执行完毕

        System.out.println("主线程结束了...");
    }

}


class ThreadCountTest implements Runnable{

    private CountDownLatch countDownLatch;  //将外部的 CountDownLatch 对象传递进来，为了调用down()方法
    private int num;

    public ThreadCountTest() {
    }

    public ThreadCountTest(CountDownLatch countDownLatch, int num) {
        this.countDownLatch = countDownLatch;
        this.num = num;
    }

    @Override
    public void run() {
        try {

            Thread.sleep(1000 * num);
            countDownLatch.countDown(); //必须先执行锁定，否则前面的信息会先被执行
            System.out.println("当前线程:" + Thread.currentThread().getName() + ",num=" + num);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}