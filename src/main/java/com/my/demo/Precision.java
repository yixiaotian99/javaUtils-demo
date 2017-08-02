package com.my.demo;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

/**
 * 比较 float 、double、 decimal 区别
 * float 单精度浮点型，4字节， 32 bit ， 7个有效位(实数部分 9999999.000)
 * double 双精度，8字节，64bit， 15个有效位
 * decimal 数字型， 128bit ，常用于银行账目计算 ， 28个有效位
 *
 * 对编程来说，double 与 float 区别是 double 精度高，有效数字是 15 位，float为 7位，但 double 消耗内存是 float 的两倍， double 的运算速度比 float 慢的多
 */

public class Precision {

    /**
     * 浮点计算的误差，应尽量避免浮点运算
     */
    public void floatTest(){
        System.out.println(0.05 + 0.01);   //0.060000000000000005
        System.out.println(1.0 - 0.42);    //0.5800000000000001
        System.out.println(4.015 * 100);   //401.49999999999994
        System.out.println(123.3 / 100);   //1.2329999999999999
    }

    /**
     * java默认不支持的四舍五入
     * 如果要使用四舍五入，可以使用 java.text.DecimalFormat
     */
    public void getThrowTest(){
        System.out.println(4.015 * 100);  //401.49999999999994
        System.out.println(4.015d * 100);  //401.49999999999994
        System.out.println(new DecimalFormat("0.00").format(4.025)); //4.03
    }


    /**
     * 浮点输出， java 默认将浮点类型大于 9999999.0 就自动转为科学计数法
     * 如果不想使用科学计数法使用 new BigDecimal(String) 不要使用 new BigDecimal(double)
     */
    public void floatCounter(){
        System.out.println(9969999999.04);  //9.96999999904E9
        System.out.println(199999999.04);   //1.9999999904E8
        System.out.println(1000000011.01);  //1.00000001101E9
        System.out.println(9999999.04);     //9999999.04

        System.out.println(new BigDecimal(123456789.01).toString());  //123456789.01000000536441802978515625
        System.out.println(new BigDecimal("123456789.01").toString());  //123456789.01
    }


    /**
     * BigDebimal 使用
     * @param args
     */
    public void bigDecimalTest(){
        BigDecimal num1 = new BigDecimal("4.152");
        BigDecimal num2 = new BigDecimal("6.345");

        System.out.println(num1.add(num2));   //10.497
        System.out.println(num1.subtract(num2));  //-2.193
        System.out.println(num1.multiply(num2));  //26.344440

        DecimalFormat format1 = new DecimalFormat("#.##"); //格式化输出，保留 2 位小数，如果不存在不显示0
        DecimalFormat format2 = new DecimalFormat("0.00"); //格式化输出，保留 2 位小数，如果存在不显示0.00
        //format1.setRoundingMode(RoundingMode.HALF_UP);  //设置舍入模式

        System.out.println(format1.format(26.345440));  //26.35
        System.out.println(format2.format(0.1));  //0.10


    }




    public static void main(String[] args) {
        Precision precision = new Precision();
        //precision.floatTest();
//        precision.getThrowTest();
//        precision.floatCounter();
        //precision.bigDecimalTest();
        System.out.println(1000/12);
    }

}
