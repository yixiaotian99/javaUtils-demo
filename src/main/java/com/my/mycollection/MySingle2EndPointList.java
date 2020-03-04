package com.my.mycollection;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author sunjinwei
 * @Date 2020-03-03 11:28
 * @Description 双端单链表 头端+尾端
 **/
@Slf4j
public class MySingle2EndPointList<E> {

    /**
     * 双端单链表，有头端+尾端
     * 注意，head只是个空壳子，里面不存储任何数据，只是做为头出现
     */
    private Node<E> head;
    private Node<E> tail;

    private int size = 0;

    /**
     * 构造方法初始化头结点
     */
    public MySingle2EndPointList() {
        head = new Node<>();
        //初始化时尾端=前端
        tail = head;
    }

    /**
     * 从尾端插入节点
     *
     * @param e 插入元素
     */
    public void insertTail(E e) {
        //1.定义插入节点
        Node nNode = new Node(e);

        //2.将尾节点next指针指向新节点
        tail.setNext(nNode);

        //3.重置尾节点指针
        tail = tail.next;

        //4.长度+1
        size++;
    }


    /**
     * 从头端插入节点
     *
     * @param e
     */
    public void insertHead(E e) {
        //1. 定义插入节点
        Node nNode = new Node(e);

        //2. 找到head的next节点，并做为新节点的next指针指向
        Node firstNode = head;
        nNode.setNext(firstNode.next);

        //3. 新节点做为head的next指针节点
        head.setNext(nNode);

        //3.长度+1
        size++;
    }


    /**
     * 删除节点 应该与单向链表一样
     *
     * @param index
     */
    public void remove(int index) {
        //1. 索引下标校验
        if (index < 0 || index > size - 1) {
            throw new IndexOutOfBoundsException("索引值超出范围");
        }

        int curPos = 0;
        Node<E> temp = head.next;
        while (temp != null) {

            //2.找到 index 前一个节点
            if (curPos + 1 == index) {

                //3.获取 index 节点
                Node<E> curNode = temp.next;

                //4.调整 index 前next指针到 index 下节点指针
                temp.setNext(temp.next.next);

                //5.快速释放内存
                curNode = null;

                //6.长度-1
                size--;
            }

            curPos++;
            temp = temp.next;
        }
    }


    /**
     * 打印输出
     */
    public void display() {
        //因为头不存储数据，所有从 head.next 开始
        System.out.println(head);
        Node<E> temp = head.next;

        //注意，这是是用 temp!=null 而不是 temp.next
        while (temp != null) {
            System.out.println("数据:" + temp.getData());
            temp = temp.next;
        }
    }

    public static void main(String[] args) {
        MySingle2EndPointList m2 = new MySingle2EndPointList();
        m2.insertTail("AA");
        m2.insertTail("BB");
        m2.insertTail("CC");
        m2.display();
        System.out.println("===============");

        m2.insertHead("XX");
        m2.insertHead("YY");
        m2.display();
        System.out.println("===============");

        m2.remove(2);
        m2.display();
    }
}

