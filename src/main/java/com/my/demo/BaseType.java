package com.my.demo;

import java.io.Serializable;

/**
 * 输出 java 基本数据类型
 */
public class BaseType {

    /**
     * 测试打印输出基本数据类型
     */
    public void typeTest(){
        System.out.println("bype二进制位大小:" + Byte.SIZE);   //8位  1字节
        System.out.println("byte类型最小值:" + Byte.MIN_VALUE);//-128 -2^7
        System.out.println("byte类型最大值:" + Byte.MAX_VALUE);//127 2^7-1

        System.out.println("short二进制位大小:" + Short.SIZE);   //16位  2字节
        System.out.println("short类型最小值:" + Short.MIN_VALUE);//-2^15
        System.out.println("short类型最大值:" + Short.MAX_VALUE);//2^15-1

        System.out.println("int二进制位大小:" + Integer.SIZE);   //32位  4字节
        System.out.println("int类型最小值:" + Integer.MIN_VALUE);//-2^31
        System.out.println("int类型最大值:" + Integer.MAX_VALUE);//2^31-1

        System.out.println("long二进制位大小:" + Long.SIZE);   //64位  8字节
        System.out.println("long类型最小值:" + Long.MIN_VALUE);//-2^63
        System.out.println("long类型最大值:" + Long.MAX_VALUE);//2^63-1

        System.out.println("float二进制位大小:" + Float.SIZE);   //32位  4字节
        System.out.println("float类型最小值:" + Float.MIN_VALUE);//-2^31
        System.out.println("float类型最大值:" + Float.MAX_VALUE);//2^31-1

        System.out.println("double二进制位大小:" + Double.SIZE);   //64位  8字节
        System.out.println("double类型最小值:" + Double.MIN_VALUE);//-2^63
        System.out.println("double类型最大值:" + Double.MAX_VALUE);//2^63-1

        System.out.println("char二进制位大小:" + Character.SIZE);   //16位  2字节
        System.out.println("char类型最小值:" + (int)Character.MIN_VALUE);//0
        System.out.println("char类型最大值:" + (int)Character.MAX_VALUE);//65535
    }

    public static void main(String[] args) {
        BaseType bt = new BaseType();
        bt.typeTest();
    }
}


class Employee{
    private static double salary;
    private static final String DEPARTMENT = "Development";

    public static void main(String[] args) {
        salary = 1000;
        System.out.println(DEPARTMENT +  " avg salary:" + salary);
    }
}


class FinalTest{
    final int value = 10;
    public static final String TITLE =  "Manager";

    public void test(){
        //value = 11;  编译错误 不能修改 final 类型的变量值
    }

    public final void test2(){

    }
}

class FinalTest2 extends FinalTest{
    public void test(){
        super.test2();   //父类中final方法子类可以继承，但是不能修改
    }
}


/**
 * 序列化
 */
class SeriTest implements Serializable{
    private int num = 5;
    private String user = "zhangsan";

    public static void main(String[] args) {
        SeriTest st = new SeriTest();

    }
}




