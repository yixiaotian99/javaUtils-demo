package com.my.jike;

/**
 * @Author sunjinwei
 * @Date 2019-12-26 17:34
 * @Description
 **/
public class TestVolatile {

    public volatile int inc = 0;

    public void increase() {
        inc++;
    }

    public static void main(String[] args) {
        final TestVolatile test = new TestVolatile();
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
