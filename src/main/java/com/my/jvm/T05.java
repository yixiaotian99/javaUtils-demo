package com.my.jvm;

/**
 * Created by sunjinwei on 2018/9/12.
 * 模拟栈内存溢出  -Xss512k
 * @author sunjinwei
 */
public class T05 {

    static int count = 0;

    /**
     * 递归调用
     */
    static void r(){
        count++;
        r();
    }

    public static void main(String[] args) {
        try {
            r();
        } catch (Throwable e) {
            System.out.println("执行:" + count);
            System.out.println(e);
        }
    }
}
