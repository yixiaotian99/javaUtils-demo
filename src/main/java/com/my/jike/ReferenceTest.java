package com.my.jike;

import java.lang.ref.SoftReference;

/**
 * @Author sunjinwei
 * @Date 2019-12-16 15:15
 * @Description
 **/
public class ReferenceTest {
}


class Test1 {

    public static void main(String[] args){
        System.out.println("开始");
        A a = new A();

        //软引用，只有 jvm 认为内存不足时才会回收，OOM之前回收
        SoftReference<A> sr = new SoftReference<A>(a);
        a = null;
        if(sr!=null){
            a = sr.get(); //如果对象还没被销毁，获取原有对象
        }
        else{
            a = new A();
            sr = new SoftReference<A>(a);
        }
        System.out.println("结束");
    }

}

class A{
    int[] a ;
    public A(){
        a = new int[100000000];
    }
}
