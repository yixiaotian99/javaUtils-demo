package com.my.jike;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.TreeMap;

/**
 * @Author sunjinwei
 * @Date 2020-01-09 14:45
 * @Description
 **/
public class MapTest {
}


class MapDemoTest{
    public static void main(String[] args) {
        //testSort();

        testNull();
    }

    private static void testSort() {
        HashMap hashMap = new HashMap();
        Hashtable hashtable = new Hashtable();
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        TreeMap treeMap = new TreeMap();

        //添加字符数组测试
        String[] letterArr = new String[]{"B", "A", "D", "C", "E"};
        for(String s: letterArr){
            hashMap.put(s, s);
            hashtable.put(s, s);
            linkedHashMap.put(s, s);
            treeMap.put(s, s);
        }

        System.out.println("HashMap我不保证顺序: " + hashMap);  //{A=A, B=B, C=C, D=D, E=E}
        System.out.println("hashtable我不保证顺序: " + hashtable); //{A=A, E=E, D=D, C=C, B=B}
        System.out.println("linkedHashMap我保证插入顺序: " + linkedHashMap); //{B=B, A=A, D=D, C=C, E=E}
        System.out.println("treeMap我保证排序规则顺序: " + treeMap); //{A=A, B=B, C=C, D=D, E=E}
    }


    public static void testNull(){
        HashMap hashMap = new HashMap();
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        Hashtable hashtable = new Hashtable();
        TreeMap treeMap = new TreeMap();

        hashMap.put(null, null);
        linkedHashMap.put(null, null);
        hashtable.put(null, null);
        //treeMap.put(null, null);
    }
}

