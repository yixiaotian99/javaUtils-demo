package com.my.basic;

/**
 * Created by sunjinwei on 2018/1/8.
 */
public class DebugTest {

    public static void main(String[] args) {
        int temp = 300;

        temp = addNum(temp);
        System.out.println("打印输出temp:" + temp);


        for(int i=0; i<5; i++){
            System.out.println("当前i值: ==========================================================================================================================" + i);
        }

    }


    public static int addNum(int temp){
        System.out.println("111");
        System.out.println("111");
        return temp + 200;
    }
}
