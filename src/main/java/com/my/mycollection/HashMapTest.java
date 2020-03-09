package com.my.mycollection;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author sunjinwei
 * @Date 2020-03-06 15:05
 * @Description
 **/
public class HashMapTest {

    public static void main(String[] args) {
//        Map<String, Object> map = new HashMap<>(6);
//        map.put("k1", "v1");
//        map.put("k2", "v2");
//        map.put("k3", "v3");
//        map.put("k4", "v4");
//        map.put("k5", "v5");
//
//        System.out.println(7 & 9);
//        System.out.println(7 | 9);
//        System.out.println(7 ^ 9);
//        System.out.println(~ 9);
//
//        System.out.println(Integer.toBinaryString(10));

        /*System.out.println("十进制 | 二进制:    2|"+ Integer.toBinaryString(2));
        System.out.println("十进制 | 二进制:    4|"+ Integer.toBinaryString(4));
        System.out.println("十进制 | 二进制:    8|"+ Integer.toBinaryString(8));
        System.out.println("十进制 | 二进制:   16|"+ Integer.toBinaryString(16));
//        System.out.println(Integer.toBinaryString(32));

        System.out.println("十进制 | 二进制:  2-1|"+ Integer.toBinaryString(2 - 1));
        System.out.println("十进制 | 二进制:  4-1|"+ Integer.toBinaryString(4 - 1));
        System.out.println("十进制 | 二进制:  8-1|"+ Integer.toBinaryString(8 - 1));
        System.out.println("十进制 | 二进制: 16-1|"+ Integer.toBinaryString(16 - 1));*/
//        System.out.println(Integer.toBinaryString(32 - 1));



    /*    //(h = key.hashCode()) ^ (h >>> 16)
        String key = null;
//        String key = "zhangsan";
        int h;
        int hash = (h = key.hashCode()) ^ (h >>> 16);
        System.out.println("key: " + key);
        System.out.println("Object hashCode: " + h);
        System.out.println("hashCode>>>16: " + (h >>> 16));
        System.out.println("xor: " + hash);
        System.out.println("bucket: " + (15 & hash));
        System.out.println(Math.abs(hash % 16));*/


//        /** 正数演示 **/
//        System.out.println("-----------------正数演示-----------------");
//        int num = 9;
//        int tmp = 0;
//        System.out.println("原值（十进制）：" + num + ";原值（二进制）" + Integer.toBinaryString(num));
////        tmp = num >> 1;
////        System.out.println("右移1位（十进制）：" + tmp + ";右移1位（二进制）" + Integer.toBinaryString(tmp));
////
//        tmp = num >>> 1;
//        System.out.println("无符号右移1位（十进制）：" + tmp + ";无符号1位（二进制）" + Integer.toBinaryString(tmp));

//        tmp = num << 1;
//        System.out.println("左移1位（十进制）：" + tmp + ";左移1位（二进制）" + Integer.toBinaryString(tmp));
//        /** 正数演示 **/
//
//        /** 负数演示 **/
//        System.out.println("-----------------负数演示-----------------");
//        num = -7;
//        System.out.println("原值（十进制）：" + num + ";原值（二进制）" + Integer.toBinaryString(num));
//        tmp = num >> 1;
//        System.out.println("右移1位（十进制）：" + tmp + ";右移1位（二进制）" + Integer.toBinaryString(tmp));
//
//        tmp = num >>> 1;
//        System.out.println("无符号右移1位（十进制）：" + tmp + ";无符号1位（二进制）" + Integer.toBinaryString(tmp));
//
//        tmp = num << 1;
//        System.out.println("左移1位（十进制）：" + tmp + ";左移1位（二进制）" + Integer.toBinaryString(tmp));
//        /** 负数演示 **/


//        HashMap<Integer, String> map = new HashMap(16);
//        map.put(7, "");
//        map.put(11, "");
//        map.put(43, "");
//        map.put(59, "");
//        map.put(19, "");
//        map.put(3, "");
//        map.put(35, "");
//
//        System.out.println("遍历结果：");
//        for (Integer key : map.keySet()) {
//            System.out.print(key + " -> ");
//        }


        HashMap<String, Object> map = new HashMap<>(6);
        map.put("zhangsan", "张三");
        map.put("lisi", "李四");
        map.put("wangwu", "王五");
        map.put("maliu", "马六");

        for (Object key : map.keySet()) {
            System.out.println("key->" + map.get(key));
        }


    }

}
