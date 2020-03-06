package com.my.mycollection;

import lombok.Data;

/**
 * @Author sunjinwei
 * @Date 2020-03-04 20:31
 * @Description 双链接实现
 * @see https://blog.csdn.net/javazejian/article/details/53047590
 **/
public class MyDoubleLinkedList<T> {

    //定义不带数据的头节点、尾节点
    private DNode<T> head;
    private DNode<T> tail;

    private int size = 0;

    public MyDoubleLinkedList() {
        //初始化头节点与尾节点为空节点
        this.head = this.tail = new DNode<>();
    }


    /**
     * 双链表插入分两种情况
     * 1. 空链表/尾部插入
     * 2. 中间位置插入
     *
     * @param index 索引位置
     * @param t     参数t
     */
    public void insert(int index, T t) {
        //1.判断索引值是否不合法，未插入之前size=4 插入index=4从0开始插入第5个元素
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("index越界:" + index);
        }

        //2.重要的是找到插入的前节点front,初始指向head
        DNode front = this.head;

        //循环找到index前节点, 直到 pos=index-1
        int pos = 0;
        while (front.next != null && pos < index) {
            pos++;
            front = front.next;
        }

        //构建新节点，并设置前节点，后节点
        DNode<T> nNode = new DNode<>(t, front, front.next);

        //3. 如果是空链表,head==tail
        if (front == this.tail) {
            front.next = nNode;
            this.tail = nNode;
        }

//原来自己的想法，不通
//        //2.判断插入的位置是属于哪种情况
//        DNode temp = head;
//
//        //2.1 如果是尾部插入，
//        // 将尾节点pre指向新节点，新节点next指向尾节点，
//        // 新节点pre指向front节点，front节点next指向新节点
//        if (head == tail) {
//            head.setNext(nNode);
//            tail = nNode;
//
//        } else {
//            //2.2 如果是中间插入
//            //将 front节点next指向新节点，新节点pre指向front
//            //将 新节点next指向后节点，后节点pre指向新节点
//            int pos = 0;
//            while (temp != null) {
//                if ((index - 1) == pos) {
//                    DNode front = temp.pre;
//                    DNode next = temp.getNext();
//
//                    nNode.setNext(next);
//                    nNode.setPre(front);
//
//                    front.setNext(nNode);
//                    next.setPre(nNode);
//                    break;
//                }
//
//                pos++;
//                temp = temp.getNext();
//            }
//        }

        //2.3 长度+1
        size++;
    }


    public boolean add(int index, T data) {
        if (index < 0 || data == null)
            throw new NullPointerException("index < 0 || data == null");

        int j = 0;
        DNode<T> front = this.head;
        //查找要插入结点位置的前一个结点
        while (front.next != null && j < index) {
            j++;
            front = front.next;
        }

        //创建需要插入的结点,并让其前继指针指向front,后继指针指向front.next
        DNode<T> q = new DNode<T>(data, front, front.next);

        //空双链表插入和尾部插入，无需此操作
        if (front.next != null) {
            //更改front.next的前继指针
            front.next.prev = q;
        }
        //更改front的后继指针
        front.next = q;

        //在尾部插入时需要注意更新tail指向
        if (front == this.tail) {
            this.tail = q;
        }
        return true;
    }


    public void display() {
        System.out.println(head);
        DNode temp = head.next;
        while (temp != null) {
            System.out.println("数据:" + temp.getData());
            temp = temp.getNext();
        }
    }

    public static void main(String[] args) {
        MyDoubleLinkedList m3 = new MyDoubleLinkedList();
        m3.add(0, "苹果");
        m3.add(1, "火龙果");
        m3.display();
    }
}


@Data
class DNode<T> {
    //数据域
    public T data;

    //前节点、后节点指针域
    public DNode<T> prev;
    public DNode<T> next;

    public DNode(T data, DNode<T> prev, DNode<T> next) {
        this.data = data;
        this.prev = prev;
        this.next = next;
    }

    public DNode() {
    }


}