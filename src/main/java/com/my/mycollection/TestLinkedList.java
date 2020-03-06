package com.my.mycollection;

import java.util.LinkedList;
import java.util.List;

/**
 * @Author sunjinwei
 * @Date 2020-03-05 13:32
 * @Description
 **/
public class TestLinkedList {
    public static void main(String[] args) {
        List list = new LinkedList<>();
        list.add(0, "苹果");
        list.add(0, "火龙果");

        System.out.println(list);

        list.remove(0);
        System.out.println(list);
    }
}
