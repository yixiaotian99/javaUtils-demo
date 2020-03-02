package com.my.basic;

import cn.hutool.core.collection.CollUtil;
import lombok.Data;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Author sunjinwei
 * @Date 2020-02-29 16:38
 * @Description
 **/
@Data
public class TestArrayList implements Serializable {

    private Integer width;
    private Integer height;
    private transient Integer area;

    public TestArrayList(Integer width, Integer height) {
        this.width = width;
        this.height = height;
        this.area = this.width * this.height;
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        TestArrayList t1 = new TestArrayList(3, 4);
        System.out.println("原始对象:" + t1);


        ObjectOutputStream o = new ObjectOutputStream(new FileOutputStream("t1"));
        // 往流写入对象
        o.writeObject(t1);
        o.close();

        // 从流读取对象
        ObjectInputStream in = new ObjectInputStream(new FileInputStream("t1"));
        TestArrayList t2 = (TestArrayList) in.readObject();
        System.out.println("2.反序列化后的对象\n" + t2);

        t2.setArea(null);
        System.out.println("3.恢复成原始对象\n" + t2);
        in.close();
    }
}


class T1 implements Cloneable{
    public static void main(String[] args) throws CloneNotSupportedException {
//        Set set = new HashSet<>();
//        set.add("xx");
//        set.add("yy");


        List list01 = new ArrayList<>();
        list01.add("a");
        list01.add("b");
        list01.add("c");
        list01.add("d");

//        System.out.println(list01.remove(1));
//        System.out.println(list01.remove(null));
//        System.out.println(list01);

        List list02 = new ArrayList();
        list02.add("b");
        list02.add("c");

        list01.removeAll(list02);


//        System.out.println(list01.get(1));
//
//        T1 t1 = new T1();
//        Object clone = t1.clone();
//        System.out.println(clone);

//        list01.addAll(set);


        //list01.add(2, "xx");
//        list01.addAll(1, set);
//
//        List list02 = new ArrayList<>(set);
////        System.out.println(list01);
//        System.out.println(list02);
        List list03 = new ArrayList<>();
        list03.add("a");
        list03.add("b");
        list03.add("c");

        System.out.println(list03);
        final List list04 = list03;
        System.out.println(list04);
        list04.add("d");
        System.out.println(list03);
        System.out.println(list04);

    }


}

