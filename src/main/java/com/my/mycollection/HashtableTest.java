package com.my.mycollection;

import java.util.Hashtable;

/**
 * @Author sunjinwei
 * @Date 2020-03-10 15:21
 * @Description
 **/
public class HashtableTest {


    public static void main(String[] args) {
//        Hashtable ht = new Hashtable(0);
//        ht.put(null, "");

//        Student s = new Student();
//        ht.put("s1", s);
//
////        System.out.println(ht.entrySet());
//
//        System.out.println(Math.abs(Integer.MAX_VALUE));  // 2147483647
//        System.out.println(Math.abs(Integer.MAX_VALUE + 1));// -2147483648
//        System.out.println(-1 * (-2147483648)); // -2147483648


        Hashtable ht = new Hashtable();
        ht.put(24, "");
        ht.put(20, "");
        ht.put(31, "");
//        ht.put(9, "");

        System.out.println(ht); //输出 {31=, 20=, 24=}

        for (Object key : ht.keySet()) {
            System.out.print(key + "->"); //输出 31->20->24->
        }
    }
}

//
//@Data
//class Student {
//    private String name;
//}
