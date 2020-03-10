package com.my.mycollection;


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

        myNode.head.test(); //数据：张三，李四，王五
        System.out.println("===========");
        myNode.second.test(); //数据：王五
    }

}


class MyNode<T> {

    T data;
    MyNode<T> next;

    MyNode<String> head;
    MyNode<String> tail;
    MyNode<String> first;
    MyNode<String> second;
    MyNode<String> three;

    public void test() {
        MyNode node = this;
        while (node.next != null) {
            node = node.next;
            System.out.println("数据:" + node.data);
        }
    }


    public MyNode() {
    }

    public MyNode(T data) {
        this.data = data;
    }


    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public MyNode<T> getNext() {
        return next;
    }

    public void setNext(MyNode<T> next) {
        this.next = next;
    }

    public MyNode<String> getHead() {
        return head;
    }

    public void setHead(MyNode<String> head) {
        this.head = head;
    }

    public MyNode<String> getTail() {
        return tail;
    }

    public void setTail(MyNode<String> tail) {
        this.tail = tail;
    }

    public MyNode<String> getFirst() {
        return first;
    }

    public void setFirst(MyNode<String> first) {
        this.first = first;
    }

    public MyNode<String> getSecond() {
        return second;
    }

    public void setSecond(MyNode<String> second) {
        this.second = second;
    }

    public MyNode<String> getThree() {
        return three;
    }

    public void setThree(MyNode<String> three) {
        this.three = three;
    }
}