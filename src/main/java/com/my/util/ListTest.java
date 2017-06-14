package com.my.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by jinwei.sun on 2017/5/31.
 */
public class ListTest {

    public void testRemove(){
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");

        for(String temp : list){
            if("2".equals(temp)){
                list.remove(temp);  //java.util.ConcurrentModificationException
            }
        }

        list.stream().forEach(s -> System.out.println(s));
    }


    public void testRemove2(){
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");

        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()){
            String temp = iterator.next();
            if("2".equals(temp)){
                iterator.remove();
            }
        }

        list.stream().forEach(s -> System.out.println(s));
    }


    public static void main(String[] args) {
        ListTest lt = new ListTest();
        //lt.testRemove();
        lt.testRemove2();
    }
}
