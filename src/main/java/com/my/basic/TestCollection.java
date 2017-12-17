package com.my.basic;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RunnableFuture;
import java.util.concurrent.Semaphore;

/**
 * Created by sunjw on 2017/11/24 0024.
 */
public class TestCollection {

    public static void main(String[] args) {
//        Set set = new HashSet();
//
//        Map map = new HashMap();
//
//        Hashtable hashtable = new Hashtable();
//
//        ExecutorService executorService = Executors.newSingleThreadExecutor();
//
//
//        int i = 1;
//        ArrayList arrayList = new ArrayList();
//        arrayList.add(1);
//        arrayList.add(i);
//        System.out.println(arrayList);
//
//        HashSet hashSet = new HashSet();
//        hashSet.add(1);
//        hashSet.add(1);
//        System.out.println(hashSet);
//
//        HashMap<Object, Object> hashMap = new HashMap<>();
//        hashMap.put("one", "1");
//        hashMap.put("one", "1");
//        System.out.println(hashMap);
//
//        int[] ints = new int[2];
//        for(int m=0; m<10; m++){
//            ints[m] = m;
//        }
//        System.out.println(ints);


//        List<Integer> list = new ArrayList<>();
//        list.add(5);
//        list.add(3);
//        list.add(2);
//        System.out.println(list); //[5, 3, 2]
//        Collections.sort(list);
//        System.out.println(list); //[2, 3, 5]

//        List<String> list = Arrays.asList("java语言", "C语言", "C++语言");
//        Iterator<String> iterator = list.iterator();
//        while (iterator.hasNext()){
//            String next = iterator.next();
//            next = "修改值";
//            System.out.println(next); //修改值
//        }
//        System.out.println(list);  //[java语言, C语言, C++语言]


//        HashMap<String, Object> map = new HashMap<>();
//        map.put("one", "Java语言");
//        map.put("two", "C++语言");
//        map.put("three", "Python语言");
//
//        Set<String> set = map.keySet();
//        for(String key : set){
//            Object o = map.get(key);
//            System.out.println("key= " + key + ",value= " + o);
//        }

        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("one");
        arrayList.add("two");
        System.out.println(arrayList);
    }
}

/*
class SingleDemo{
    private static SingleDemo demo;

    private  SingleDemo() {

    }

    public static SingleDemo getDemo() {
        if(demo == null){
            demo = new SingleDemo();
        }
        return demo;
    }
}*/


/**
 * 测试静态属性或方法是否可覆盖
 */
class A { //父类
    public static String staticStr = "A静态属性";
    public String nonStaticStr = "A非静态属性";
    public static void staticMethod(){
        System.out.println("A静态方法");
    }
    public void nonStaticMethod(){
        System.out.println("A非静态方法");
    }
}

class B extends A{//子类B
    public static String staticStr = "B改写后的静态属性";
    public String nonStaticStr = "B改写后的非静态属性";
    public static void staticMethod(){
        System.out.println("B改写后的静态方法");
    }
}

class TestExtend{
    public static void main(String[] args) {
        B b = new B();
        System.out.println(b.staticStr);  //输出b子类信息
        System.out.println(b.nonStaticStr); //输出b子类信息
        b.staticMethod(); //输出b子类信息

        A a = new B();
        System.out.println(a.staticStr);  //输出a子类信息
        System.out.println(a.nonStaticStr); //输出a子类信息
        a.staticMethod(); //输出a子类信息
    }
}


/*
 测试一个方法最多被线程访问的次数
 */
class TestThread{

    static Semaphore semaphore = new Semaphore(5, true);  //设置最多5个线程访问

    public static void main(String[] args) throws Exception{
        for(int i=0; i<20; i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    test();
                }
            }).start();
        }
    }

    public static void test() {
        try {
            semaphore.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getId() + "线程进来了..");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getId() + "线程走了..");
        semaphore.release();
    }

}


class TestInit{
    public static void main(String[] args) {
        int i = 0;
        System.out.println(i);
    }
}



