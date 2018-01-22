package com.my.basic;

import java.util.ArrayList;
import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 线程相关操作
 */
public class ThreadTest {

}


/**
 * 使用继承的方法实现多线程
 */
class ThreadExtends extends Thread{
    public ThreadExtends(String name) {
        super(name);
    }

    //重写 run() 方法，此方法由 jvm 自动调用
    public void run(){
        System.out.println("线程名称:" + Thread.currentThread().getName() + ", id= " + Thread.currentThread().getId());
    }

    public static void main(String[] args) {
        System.out.println("主线程启动：" + Thread.currentThread().getId());
        ThreadExtends threadOne = new ThreadExtends("Thread01");
        ThreadExtends threadTwo = new ThreadExtends("Thread02");
        threadOne.start();
        threadTwo.start();
        System.out.println("主线程退出："+ Thread.currentThread().getId());
        /**
         * 主线程启动：1
         * 主线程退出：1
         * 线程名称:Thread01, id= 11
         * 线程名称:Thread02, id= 12
         */
    }
}


/**
 * 比较 start() 与 run() 方法不同
 * 线程的 ID 相同，说明直接调用 run() 方法并不会启动新线程
 */
class ThreadStartAndRun extends Thread{
    public void run(){
        System.out.println("Thread run : " + Thread.currentThread().getId());
    }

    public static void main(String[] args) {
        ThreadStartAndRun thread = new ThreadStartAndRun();
        thread.start();
        ThreadStartAndRun thread02 = new ThreadStartAndRun();
        thread02.run();
        System.out.println("Main :" + Thread.currentThread().getId());
        /**
         * Thread run : 1
         * Main :1
         * Thread run : 11
         */
    }
}


/**
 * 实现 runnable 接口实现多线程
 */
class ThreadInterface implements Runnable{
    private String name;

    public ThreadInterface(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        System.out.println("当前线程: " + this.name + " id: " + Thread.currentThread().getId());
    }

    public static void main(String[] args) {
        ThreadInterface interface01 = new ThreadInterface("Thread01");
        ThreadInterface interface02 = new ThreadInterface("Thread02");  //创建接口对象

        System.out.println("Main: " + Thread.currentThread().getId());
        Thread thread01 = new Thread(interface01);  //调用 Thread 的构造方法，传递Runnable对象
        Thread thread02 = new Thread(interface02);
        thread01.start();
        thread02.start();
    }
}


/**
 * 使用 Callable 、Future 获取有返回值的多线程 阻塞返回
 */
class CallableReturnValue implements Callable{
    private String task;
    public CallableReturnValue(String name) {
        this.task = name;
    }

    //实现 Callable 接口必须重写 call() 方法
    @Override
    public Object call() throws Exception {
        System.out.println("Thread: " + this.task );
        Thread.sleep(1000);  //睡眠1秒
        return this.task;
    }


    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ArrayList<Future> futureList = new ArrayList<>();
        //使用线程池创建2个固定线程
        ExecutorService pool = Executors.newFixedThreadPool(2);
        for(int i=0; i<3; i++){
            Callable cc = new CallableReturnValue("i="  + i);
            Future future = pool.submit(cc);
            futureList.add(future);   //阻塞获取线程返回值
        }
        pool.shutdown(); //关闭线程池

        for(Future future : futureList){
            System.out.println("线程返回值: " + future.get());
        }
        System.out.println("线程全部关闭了");
    }
}


/**
 * 当 synchronized 修饰代码块
 */
class ThreadSync implements Runnable{
    private static int count ;

    public ThreadSync() {
        count = 0;
    }

    @Override
    public void run() {
        synchronized (this){  //同步代码块，要求是同一个对象
            for(int i=0; i<2; i++){
                System.out.println("当前线程: " + Thread.currentThread().getName() + ", id= " + Thread.currentThread().getId());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        /**
         * 修饰为块代码时，当为同一个对象时同步锁生效
         当前线程: Thread-0, id= 11
         当前线程: Thread-0, id= 11
         当前线程: Thread-1, id= 12
         当前线程: Thread-1, id= 12
         */
//        ThreadSync sync = new ThreadSync();
//        Thread thread01 = new Thread(sync);
//        Thread thread02 = new Thread(sync);
//        thread01.start();
//        thread02.start();

        /**
         * 修饰为块代码时，当为多个对象时同步锁不生效
         当前线程: Thread-0, id= 11
         当前线程: Thread-1, id= 12
         当前线程: Thread-0, id= 11
         当前线程: Thread-1, id= 12
         */
        Thread thread03 = new Thread(new ThreadSync());
        Thread thread04 = new Thread(new ThreadSync());
        thread03.start();
        thread04.start();
    }
}


/**
 * 多个线程访问  一个访问synchronized 另一个访问非synchronized
 */
class ThreadSyncAndNoSync implements Runnable{
    private static int count =0 ;

    @Override
    public void run() {
        String name = Thread.currentThread().getName();
        if("A".equals(name)){
            countAdd();
        }else if("B".equals(name)){
            printCount();
        }
    }

    //定义静态方法
    public void countAdd(){
        synchronized (this){   //同步代码块
            for(int i=0; i<3; i++){
                System.out.println("当前线程: " + Thread.currentThread().getName() + ",count=" + (count++));
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    //定义非静态方法
    public void printCount(){
        for(int i=0; i<3; i++){
            System.out.println("当前线程: " + Thread.currentThread().getName() + ",count=" + count);
        }
    }

    public static void main(String[] args) {
        /**
         当前线程: A,count=0
         当前线程: B,count=1
         当前线程: B,count=1
         当前线程: B,count=1
         当前线程: A,count=1
         当前线程: A,count=2
         */
        ThreadSyncAndNoSync sync = new ThreadSyncAndNoSync();
        Thread thread01 = new Thread(sync, "A");
        Thread thread02 = new Thread(sync, "B");
        thread01.start();
        thread02.start();
    }
}


/**
 * 使用 synchronized 修饰普通方法
 */
class ThreadSyncFunction implements Runnable{
    private static int count = 0;

    @Override
    public synchronized void run() { //修改普通方法，与修饰代码块 synchronized(this)等价
        for(int i=0; i<2; i++){
            System.out.println("当前线程: " + Thread.currentThread().getName() + ",count=" + (count++));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        /**
         当前线程: Thread-0,count=0
         当前线程: Thread-0,count=1
         当前线程: Thread-1,count=2
         当前线程: Thread-1,count=3
         */
        ThreadSyncFunction syncFunction = new ThreadSyncFunction();
        Thread thread01 = new Thread(syncFunction);
        Thread thread02 = new Thread(syncFunction);
        thread01.start();
        thread02.start();
    }
}


/**
 * 修饰静态方法
 */
class ThreadStaticFunction implements Runnable{
    private static int count = 0;

    //修饰静态方法
    public synchronized static void add() {
        for(int i=0; i<4; i++){
            System.out.println("当前线程: " + Thread.currentThread().getName() + ",count=" + (count++));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public synchronized void run() {
        add();
    }

    public static void main(String[] args) {
        /** 以下两种方式均会打印如下 ：
         当前线程: Thread-0,count=0
         当前线程: Thread-0,count=1
         当前线程: Thread-0,count=2
         当前线程: Thread-0,count=3
         当前线程: Thread-1,count=4
         当前线程: Thread-1,count=5
         当前线程: Thread-1,count=6
         当前线程: Thread-1,count=7
         */
//        ThreadStaticFunction staticFunction = new ThreadStaticFunction();
//        Thread thread01 = new Thread(staticFunction);
//        Thread thread02 = new Thread(staticFunction);
//        thread01.start();
//        thread02.start();

        Thread thread03 = new Thread(new ThreadStaticFunction());
        Thread thread04 = new Thread(new ThreadStaticFunction());
        thread03.start();
        thread04.start();
    }
}


/**
 * 使用 synchronized 修饰类
 */
class ThreadClass implements Runnable{
    private static int count = 0;

    @Override
    public void run() {
        synchronized (ThreadClass.class){ //同步整个类
            for(int i=0; i<3; i++){
                System.out.println("当前线程: " + Thread.currentThread().getName() + ",count=" + (count++));
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        /**
         当前线程: Thread-0,count=0
         当前线程: Thread-0,count=1
         当前线程: Thread-0,count=2
         当前线程: Thread-1,count=3
         当前线程: Thread-1,count=4
         当前线程: Thread-1,count=5
         */
        Thread thread01 = new Thread(new ThreadClass());
        Thread thread02 = new Thread(new ThreadClass());
        thread01.start();
        thread02.start();
    }
}


/**
 * 加锁
 */
class ThreadAddLock{
    Lock lock = new ReentrantLock();  //锁对象

    int i = 1;

    public void testAddLock(){
        lock.lock();
        try {
            i ++;
        } finally {
            lock.unlock();
        }
    }





    public static void main(String[] args) {
        ThreadAddLock lockDemo = new ThreadAddLock();
        //lockDemo.testAddLock();

//        for(int i=0; i<10; i++){
//            Runnable runnable = () -> lockDemo.testAddLock();
//
//            Thread thread = new Thread(runnable);
//            System.out.println("线程名称: " + thread.getName());
//            thread.start();
//        }


        for(int i=0; i<10000; i++){
            System.out.println(i);
        }

    }
}



class TestThreadPool{
    public static void main(String[] args) {
        Executors.newSingleThreadExecutor();

        Executors.newFixedThreadPool(50);
    }
}
