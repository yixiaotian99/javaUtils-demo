package com.my.basic;

import java.util.ArrayList;
import java.util.concurrent.*;

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

















