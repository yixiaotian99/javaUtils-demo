package com.my.mycollection;

/**
 * @Author sunjinwei
 * @Date 2020-03-05 10:29
 * @Description 人家写的
 * @see https://www.jianshu.com/p/43a7d1c9bc48
 **/
public class DoubleLinkedList<T> {

    private int size;//链表大小
    //由于是双向，头尾任意选择一端即可
    private Node<T> head;//链表的头节点
    private Node<T> tail;//链表的尾节点

    /**
     * 内部类：节点
     *
     * @param <T>
     */
    public static class Node<T> {
        private Node prev;
        private Node next;
        private T data;

        public Node(T data) {
            this.data = data;
        }

        private Node() {
        }
    }

    /**
     * 添加到链尾
     *
     * @param data
     */
    public void add(T data) {
        add(size, data);
    }

    /**
     * 添加到任意index处
     *
     * @param index
     * @param data
     */
    public void add(int index, T data) {
        Node<T> node = new Node<>(data);
        if (isEmpty()) {//链表为空
            head = node;
            tail = node;
        } else {
            if (index > size - 1) {//索引超出当前链表大小，则添加到链尾
                Node<T> temp = tail;
                tail = node;
                temp.next = tail;
                tail.prev = temp;
            } else {//原index位置处有值，索引位置大于index的元素向链尾移动(实际并不是移动，只是看上去)
                Node<T> origin = getNode(index);
                Node<T> prev = origin.prev;
                prev.next = node;
                node.prev = prev;
                node.next = origin;
                origin.prev = node;
            }
        }

        size++;
    }

    /**
     * 更新index位置处元素的值
     *
     * @param index
     * @param data
     */
    public void set(int index, T data) {
        if (index > size - 1 || index < 0) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        getNode(index).data = data;
    }

    /**
     * 删除index位置处的元素
     *
     * @param index
     */
    public void delete(int index) {
        if (index > size - 1 || index < 0) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        if (index == 0) {//删除链头
            head = head.next;
            head.prev = null;
        } else if (index == size - 1) {//删除链尾
            tail = tail.prev;
            tail.next = null;
        } else {//普通节点
            Node<T> node = getNode(index);
            Node prev = node.prev;
            Node next = node.next;
            prev.next = next;
            next.prev = prev;
            node.prev = null;
            node.next = null;
        }
        size--;
    }

    /**
     * 获取index位置处的元素的值
     *
     * @param index
     * @return
     */
    public T getValue(int index) {
        return getNode(index) == null ? null : getNode(index).data;
    }

    public T getValue(Node<T> node) {
        return node == null ? null : node.data;
    }

    /**
     * 获取节点node的上一个节点
     *
     * @param node
     * @return
     */
    public Node<T> getPrevNode(Node<T> node) {
        return node == null ? null : node.prev;
    }

    /**
     * 获取节点node的下一个节点
     *
     * @param node
     * @return
     */
    public Node<T> getNextNode(Node<T> node) {
        return node == null ? null : node.next;
    }

    public Node<T> getHeadNode() {
        return head;
    }

    public Node<T> getTailNode() {
        return tail;
    }

    /**
     * 获取index位置处的元素
     *
     * @param index
     * @return
     */
    public Node<T> getNode(int index) {

        if (isEmpty() && (index > size - 1)) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        Node<T> result = head;
        int n = 0;
        while (n < index) {//注意这里是 n < index， 而不是n <= index
            result = result.next;
            n++;
        }
        return result;
    }

    /**
     * 获取值为data的元素在链表中的位置（第一次出现的位置，可能含有多个）
     *
     * @param data
     * @return
     */
    public int indexOf(T data) {
        if (isEmpty() || data == null) {
            return -1;
        }

        int n = 0;
        Node<T> node = head;
        while (n < size) {
            if (data.equals(node.data)) {
                return n;
            }
            n++;
        }

        return -1;
    }

    /**
     * 判断是否有值为data的元素
     *
     * @param data
     * @return
     */
    public boolean containValue(T data) {
        return indexOf(data) != -1;
    }

    /**
     * 获取链表的大小
     *
     * @return
     */
    public int size() {
        return size;
    }

    /**
     * 判断链表是否为空
     *
     * @return
     */
    public boolean isEmpty() {
        return size == 0;
    }


    public static void main(String[] args) {
        DoubleLinkedList dlist = new DoubleLinkedList();
        dlist.add(0, "苹果");
        dlist.add(1, "火龙果");

        for (int i = 0; i < dlist.size; i++) {
            System.out.println("数据:" + dlist.getNode(i).data);
        }
    }

}