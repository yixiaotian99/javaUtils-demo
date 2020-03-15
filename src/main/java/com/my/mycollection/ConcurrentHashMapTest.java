package com.my.mycollection;

import lombok.Data;

/**
 * @Author sunjinwei
 * @Date 2020-03-11 20:58
 * @Description
 **/
public class ConcurrentHashMapTest {


    private CNode<String>[] table;


    public static void main(String[] args) {
//        ConcurrentHashMap hashMap = new ConcurrentHashMap(15);
//        hashMap.put("zhangsan", "张三");
//
//        System.out.println("size: " + hashMap.size());
//
//        System.out.println(Math.pow(2, 7));
//
//        HashMap map = new HashMap(63);

//        ConcurrentHashMapTest tt = new ConcurrentHashMapTest();
//        tt.table = new CNode[10];
//        tt.table[0] = new CNode<>("AA");
//        tt.table[1] = new CNode<>("BB");
//
//        FwdNode f1 = new FwdNode(tt.table);
//        System.out.println(f1);
//        FwdNode f2 = new FwdNode(tt.table);
//        System.out.println(f2);
//
//        System.out.println(f1 == f2);
//        System.out.println(f1.equals(f2));

//        System.out.println(1 << 2);
//        System.out.println(3 + 1<<2);
//
//        String p = "zhangsan";
//        int n = 16;
//        System.out.println(p.hashCode());
//        System.out.println(Integer.toBinaryString(p.hashCode()));
//        System.out.println(Integer.toBinaryString(16));
//        System.out.println( p.hashCode() & n);

//        System.out.println(Integer.toBinaryString((int) Math.pow(2, 0)));
//        System.out.println(Integer.toBinaryString((int) Math.pow(2, 1)));
//        System.out.println(Integer.toBinaryString((int) Math.pow(2, 2)));
//        System.out.println(Integer.toBinaryString((int) Math.pow(2, 3)));
//        System.out.println(Integer.toBinaryString((int) Math.pow(2, 4)));
//        System.out.println(Integer.toBinaryString((int) Math.pow(2, 5)));


        System.out.println(Integer.MAX_VALUE);
        System.out.println((long)Integer.MAX_VALUE);
        long n = 2147483649L;

//        System.out.println(n);
        System.out.println(
                (n > (long)Integer.MAX_VALUE) ? Integer.MAX_VALUE :
                        (int)n);

    }


}


@Data
class CNode<T> {
    private T data;
    private Node<T> next;

    public CNode(T data) {
        this.data = data;
    }

    public CNode() {
    }
}


@Data
class FwdNode<T> extends CNode {

    private CNode[] nextTable;

    public FwdNode(CNode[] nextTable) {
        this.nextTable = nextTable;
    }

}
