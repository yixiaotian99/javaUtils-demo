package com.my.mycollection;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author sunjinwei
 * @Date 2020-03-03 11:28
 * @Description 单向链表
 **/
@Slf4j
public class MySingle1WayList<E> {

    /**
     * 单向链表，只有头指针，插入时只能从头开始找
     * 注意，head只是个空壳子，里面不存储任何数据，只是做为头出现
     */
    private Node<E> head;

    private int size = 0;

    /**
     * 构造方法初始化头结点
     */
    public MySingle1WayList() {
        head = new Node<>();
    }

    /**
     * 插入元素
     */
    public void insertLast(E eleme) {
        //1. 定义插入的元素Node
        Node<E> nNode = new Node<>(eleme);

        //2. 找到最后一个元素插入数据域
        Node<E> temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }

        //3. 修改原有结点最后一个结点为当前新结点
        temp.next = nNode;

        //4. 长度+1
        size++;
    }


    /**
     * 插入第 index 位置元素
     *
     * @param index
     * @param eleme
     */
    public void insert(int index, E eleme) {
        //1. 先判断 index 是否合法
        if (index < 0 || index > size) {
            log.error("index illegality, index:{}", index);
            return;
        }

        //2. 从头开始遍历链表，找到第 index 位置元素
        Node<E> nNode = new Node<>(eleme);
        Node temp = head;

        for (int i = 0; i < size; i++) {
            if (index == i) {
                //3. 将新节点指针next指向index后节点
                nNode.next = temp.next;

                //4. 将index位置元素指针指向新节点
                temp.next = nNode;
                break;
            }
            temp = temp.next;
        }

        //5. 长度+1
        size++;
    }


    /**
     * 打印输出
     */
    public void display() {
        //因为头不存储数据，所有从 head.next 开始
        Node<E> temp = head.next;

        for (int i = 0; i < size; i++) {
            System.out.println("数据:" + temp.getData());
            temp = temp.next;
        }
    }

    public static void main(String[] args) {
        MySingle1WayList m1 = new MySingle1WayList();
        m1.insertLast("张三");
        m1.insertLast("李四");
        m1.insertLast("王五");

        m1.display();
        System.out.println("===========");
        m1.insert(1, "小飞虾");
        m1.display();
    }

}


@Data
class Node<E> {
    E data;  //数据域
    Node<E> next; //下一节点指针域

    public Node(E data) {
        this.data = data;
    }

    public Node() {
    }
}
