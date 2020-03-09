package com.my.mycollection;

import lombok.Data;

/**
 * @Author sunjinwei
 * @Date 2020-03-09 18:53
 * @Description
 **/
public class ThisTest<T> {

    public static void main(String[] args) {
        MyNode myNode = new MyNode();
        myNode.head = new MyNode();
        myNode.first = new MyNode("张三");
        myNode.second = new MyNode("李四");
        myNode.three = new MyNode("王五");

        myNode.head.setNext(myNode.first);
        myNode.first.setNext(myNode.second);
        myNode.second.setNext(myNode.three);
        myNode.three.setNext(myNode.tail);

        myNode.head.test();
        System.out.println("===========");
        myNode.second.test();
    }

}


@Data
class MyNode<T> {

    T data;
    MyNode<T> next;

    MyNode<String> head;
    MyNode<String> tail;
    MyNode<String> first;
    MyNode<String> second;
    MyNode<String> three;


    public MyNode() {
    }

    public MyNode(T data) {
        this.data = data;
    }


    public void test() {
        MyNode node = this;
        while (node.next != null) {
            node = node.next;
            System.out.println("数据:" + node.data);
        }
    }

}