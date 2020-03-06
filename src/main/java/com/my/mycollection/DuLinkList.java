package com.my.mycollection;


/**
 * 双向链表
 *
 * @author Dylan
 */
public class DuLinkList<T> {

    // 定义一个内部类Node，Node实例代表链表的节点
    private class Node {

        // 保存节点的数据
        private T data;
        // 保存上个节点的引用
        private Node prev;
        // 指向下一个节点的引用
        private Node next;

        // 无参构造器
        public Node() {
        }

        // 初始化全部属性的构造器
        public Node(T data, Node prev, Node next) {

            this.data = data;
            this.prev = prev;
            this.next = next;

        }

    }

    // 保存该链表的头节点
    private Node header;
    // 保存该链表的尾节点
    private Node tail;
    // 保存该链表中已包含的节点数
    private int size;

    // 创建空链表
    public DuLinkList() {

        // 空链表，header和tail都是null
        header = null;
        tail = null;

    }

    // 以指定数据元素来创建链表，该链表只有一个元素
    public DuLinkList(T element) {

        header = new Node(element, null, null);
        // 只有一个节点，header、tail都指向该节点
        tail = header;
        size++;

    }

    // 返回链表的长度
    public int length() {

        return size;

    }

    // 获取链式线性表中索引为index处的元素
    public T get(int index) {

        return getNodeByIndex(index).data;

    }

    // 根据索引index获取指定位置的节点
    public Node getNodeByIndex(int index) {

        if (index < 0 || index > size - 1) {

            throw new IndexOutOfBoundsException("线性表索引越界");

        }
        if (index <= size / 2) {

            // 从header节点开始
            Node current = header;
            for (int i = 0; i <= size / 2 && current != null; i++, current = current.next) {
                if (i == index) {

                    return current;

                }
            }

        } else {

            // 从tail节点开始搜索
            Node current = tail;
            for (int i = size - 1; i > size / 2 && current != null; i++, current = current.prev) {
                if (i == index) {

                    return current;

                }
            }

        }

        return null;
    }

    // 查找链式线性表中指定元素的索引
    public int locate(T element) {

        // 从头结点开始搜索
        Node current = header;
        for (int i = 0; i < size && current != null; i++, current = current.next) {

            if (current.data.equals(element)) {
                return i;
            }

        }
        return -1;

    }

    // 向线性链表的指定位置插入一个元素
    public void insert(T element, int index) {

        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("线性表索引越界");
        }

        // 如果还是空链表
        if (header == null) {

            add(element);

        } else {

            // 当index为0时，也就是在链表头处插入
            if (index == 0) {

                addAtHeader(element);

            } else {

                // 获取插入点的前一个节点
                Node prev = getNodeByIndex(index - 1);
                // 获取插入点的节点
                Node next = prev.next;
                // 让新节点的next引用指向next节点，prev引用指向prev节点
                Node newNode = new Node(element, prev, next);
                // 让prev的next节点指向新节点
                prev.next = newNode;
                // 让prev的下一个节点的prev指向新节点
                next.prev = newNode;
                size++;
            }

        }

    }

    // 采用尾插法为链表添加新节点
    public void add(T element) {

        // 如果该链表还是空链表
        if (header == null) {

            header = new Node(element, null, null);
            // 只有一个节点，header、tail都指向该节点
            tail = header;

        } else {

            // 创建新节点，新节点的pre指向原tail节点
            Node newNode = new Node(element, tail, null);
            // 让尾节点的next指向新增的节点
            tail.next = newNode;
            // 以新节点作为新的尾节点
            tail = newNode;

        }
        size++;
    }

    // 采用头插法为链表添加新节点
    public void addAtHeader(T element) {
        // 创建新节点，让新节点的next指向原来的header
        // 并以新节点作为新的header
        header = new Node(element, null, header);
        // 如果插入之前是空链表
        if (tail == null) {

            tail = header;

        }
        size++;
    }

    // 删除链式线性表中指定索引处的元素
    public T delete(int index) {

        if (index < 0 || index > size - 1) {

            throw new IndexOutOfBoundsException("线性表索引越界");

        }
        Node del = null;
        // 如果被删除的是header节点
        if (index == 0) {

            del = header;
            header = header.next;
            // 释放新的header节点的prev引用
            header.prev = null;

        } else {

            // 获取删除节点的前一个节点
            Node prev = getNodeByIndex(index - 1);
            // 获取将要被删除的节点
            del = prev.next;
            // 让被删除节点的next指向被删除节点的下一个节点
            prev.next = del.next;
            // 让被删除节点的下一个节点的prev指向prev节点
            if (del.next != null) {

                del.next.prev = prev;

            }

            // 将被删除节点的prev、next引用赋为null
            del.prev = null;
            del.next = null;

        }
        size--;
        return del.data;
    }

    // 删除链式线性表中最后一个元素
    public T remove() {

        return delete(size - 1);

    }

    // 判断链式线性表是否为空表
    public boolean empty() {

        return size == 0;

    }

    // 清空线性表
    public void clear() {

        // 将底层数组所有元素赋为null
        header = null;
        tail = null;
        size = 0;

    }

    public String toString() {

        // 链表为空链表
        if (empty()) {

            return "[]";

        } else {

            StringBuilder sb = new StringBuilder("[");
            for (Node current = header; current != null; current = current.next) {

                sb.append(current.data.toString() + ", ");

            }
            int len = sb.length();
            return sb.delete(len - 2, len).append("]").toString();

        }

    }

    // 倒序toString
    public String reverseToString() {

        if (empty()) {

            return "[]";

        } else {

            StringBuilder sb = new StringBuilder("[");
            for (Node current = tail; current != null; current = current.prev) {

                sb.append(current.data.toString() + ", ");

            }
            int len = sb.length();
            return sb.delete(len - 2, len).append("]").toString();

        }

    }




    /**
     * 测试类
     *
     * @author Dylan
     */


    public static void main(String[] args) {

        DuLinkList<String> list = new DuLinkList<String>();
        list.insert("aaaa", 0);
        list.insert("bb", 1);
//        list.add("bbbb");
//        list.insert("cccc", 0);
//        // 在索引为1处插入一个新元素
//        list.insert("dddd", 1);
//        // 输出顺序线性表的元素
        System.out.println(list);
//        // 删除索引为2处的元素
//        list.delete(2);
//        System.out.println(list);
//        System.out.println(list.reverseToString());
//        // 获取cccc字符串在顺序线性表中的位置
//        System.out.println("cccc在顺序线性表中的位置：" + list.locate("cccc"));
//        System.out.println("链表中索引1处的元素：" + list.get(1));
//        list.remove();
//        System.out.println("调用remove方法后的链表：" + list);
//        list.delete(0);
//        System.out.println("调用delete(0)后的链表：" + list);

    }


}