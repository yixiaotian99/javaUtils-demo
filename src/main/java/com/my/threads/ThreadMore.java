package com.my.threads;

/**
 * Created by sunjinwei on 2018/12/3.
 *
 * @author sunjinwei
 * <p>
 * 启动100个线程执行完成后打印"完成"
 */
public class ThreadMore {

    public static void main(String[] args) {
        for(int i=0; i<100; i++){
            Thread tt = new Thread(new ThreadPrint());
            tt.start();
            try {
                tt.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("完成");
    }

}

class ThreadPrint implements Runnable {

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "--print");
    }
}
