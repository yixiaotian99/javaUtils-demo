package com.my.jike;

import com.my.basic.ThreadTest;

/**
 * @Author sunjinwei
 * @Date 2019-12-19 10:36
 * @Description 测试线程安全性
 **/
public class ThreadSafeTest {

}


class ThreadDemo {
    private int x = 0;  //相关于主内存共享变量

    private void count() {
        x++;     //非原子操作，线程不安全
    }

    public void runTest() throws Exception {

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                count();
            }
            System.out.println("Thread 1 x: " + x);
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                count();
            }
            System.out.println("Thread 2 x: " + x);
        });

        t1.start();
        t1.join();
        t2.start();
        t2.join();
    }

    public static void main(String[] args) throws Exception {
        new ThreadDemo().runTest();
    }
}


class ThreadTrain implements Runnable {
    private int trainCount = 10;

    @Override
    public void run() {
        while (trainCount > 0) {
            try {
                Thread.sleep(500);
                sale();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private /*synchronized*/ void sale() {
        if (trainCount > 0) {
            --trainCount;
            System.out.println(Thread.currentThread().getName() + ",出售第" + (10 - trainCount) + "张票");
        }
    }

    public static void main(String[] args) {
        ThreadTrain threadTrain = new ThreadTrain();
        Thread t1 = new Thread(threadTrain, "1台");
        Thread t2 = new Thread(threadTrain, "2台");
        t1.start();
        t2.start();
    }
}


class ThreadLocalTest implements Runnable {

    private Integer count;

    private static final ThreadLocal<Integer> threadLocal =
            new ThreadLocal<Integer>() {
                @Override
                protected Integer initialValue() {
                    return initialValue();
                }
            };

    public int getNum(){
        int num = threadLocal.get() + 1;
        threadLocal.set(num);
        return num;
    }

    @Override
    public void run() {
        for(int i=0; i<3; i++){
            System.out.println(Thread.currentThread().getName() + ", " + getNum());
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new ThreadLocalTest());
        Thread t2 = new Thread(new ThreadLocalTest());

        t1.start();
        t2.start();

        t1.join();
        t2.join();
    }
}


class Test222 {
    public volatile int inc = 0;

    public void increase() {
        inc++;
    }

    public static void main(String[] args) {
        final Test222 test = new Test222();
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                for (int j = 0; j < 1000; j++){
                    test.increase();
                }
            }).start();
        }

        while (Thread.activeCount() > 1){  //保证前面的线程都执行完
            Thread.yield(); //让掉CPU执行时间，让其他线程或自己线程执行（谁先抢到谁先执行）
        }
        System.out.println(test.inc); //8767  9000 可能是任意数
    }
}


