package com.my.jike;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author sunjinwei
 * @Date 2019-12-13 17:51
 * @Description
 **/
public class FinalKeyTest {

    public final void m1() {
        System.out.println(1);
    }


    public static void main(String[] args) {

        //finalTest();


//        finallyTest();

        int b = doSomething();
        System.out.println("b=" + b);

    }

    private static void finallyTest() {
//        try{
//            System.exit(1);
//        }finally {
//            System.out.println(11); //不会输出
//        }

//        try {
//            while (true){
//                System.out.println(1);
//            }
//        } finally {
//            System.out.println("Ab");
//        }
    }

    private static void finalTest() {
        final List strList = new ArrayList<>();
        strList.add("Hello");
        strList.add("world");  //final 只能保证  strList 引用不可被赋值，本身对象操作不受影响
        System.out.println(strList);

        //strList = new ArrayList(); //引用不可修改

        List unmodifiableStrList = Arrays.asList("hello", "world");
        System.out.println(unmodifiableStrList);
        unmodifiableStrList.add("again"); //创建的是不可变list，调用会报 UnsupportedOperationException
    }


    public static int doSomething() {
        int a = 10;

        try {
            a = 8 / 0;
        } catch (Exception e) {
            System.out.println("catch error");
            throw e;

        } finally {
            return a; //会被执行到吗？
        }
    }


}


class BlewIt extends Exception {
    BlewIt() {
    }

    BlewIt(String s) {
        super(s);
    }
}

class Test {
    static void blowUp() throws BlewIt {
        throw new NullPointerException();
    }

    public static void main(String[] args) {
        try {
            blowUp();
        } catch (BlewIt b) {
            System.out.println("Caught BlewIt");
        } finally {
            System.out.println("Uncaught Exception");
        }
    }
}



class FinallyTest {
    public int method() {
        int x = 1;
        try{
            ++ x;
            return x;
        }catch(Exception e){

        }finally{
            return ++x;
        }
    }

    public static void main(String[] args) {
        FinallyTest t = new FinallyTest();
        int y = t.method();
        System.out.println(y);
    }
}
