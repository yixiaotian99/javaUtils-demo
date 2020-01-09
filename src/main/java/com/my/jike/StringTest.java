package com.my.jike;

/**
 * @Author sunjinwei
 * @Date 2019-12-16 19:53
 * @Description
 **/
public class StringTest {

    public static void main(String[] args) {
//        String s1 = "abc";
//        String s2 = "abc";
//        System.out.println(s1 == s2);
//
//        String s3 = new String("m1");
//        String s4 = new String("m1");
//        System.out.println(s3 == s4);
//
//        String s5 = new String("abc");
//        System.out.println(s1 == s5);

//        String s1 = "Hello";
//        String s2 = new StringBuffer("He").append("llo").toString();
//        String s3 = s2.intern();
//
//        System.out.println("s1==s2? " + (s1==s2));  //false
//        System.out.println("s1==s3? " + (s1==s3));  //true

        concat("cc");
    }

    public static String concat(String str) {
        return str + "aa" + "bb";
    }
}
