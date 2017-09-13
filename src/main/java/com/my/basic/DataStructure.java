package com.my.basic;

import java.util.*;

/**
 * 数据结构： 枚举、位集合，向量，栈，字典，哈希表，属性
 */
public class DataStructure {

}


/**
 * 高级枚举类型的用法
 */
enum Day{
    MONDAY("星期一"),
    TUESDAY("星期二"),
    WEDNESDAY("星期三"),
    THURSDAY("星期四");

    private String desc;

    //如果是带有描述的枚举，必须有带参数的构造函数
    Day(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public static void main(String[] args) {
        System.out.println(Day.MONDAY);   //MONDAY
        System.out.println(Day.MONDAY.desc);   //星期一
    }
}


/**
 * 向量 Vector ArrayList
 */
class VectorAndArrayList{
    public void test(){
        Vector<Object> vector = new Vector<>();

        ArrayList<Object> list = new ArrayList<>();

        for(int i=0; i<1000; i++){
            list.add(i);
        }
        System.out.println("集合list大小: "+ list.size());

        LinkedList linkedList = new LinkedList();
        linkedList.add(1);
    }

    public void removeTest(){
        List<Object> list = new ArrayList<>();
        list.add("one");
        list.add("two");
        list.add("two");
        System.out.println("原始值: " + list);


        /*for(int i=0; i<list.size(); i++){
            if(list.get(i).equals("two")){
                list.remove(i);
            }
        }
        System.out.println(list);   //[one, two]*/

        /*for(Object s : list){
            if(s.toString().equals("two")){
                list.remove(s);
            }
        }
        System.out.println(list); //[one, two]*/

        Iterator<Object> iterator = list.iterator();
        while (iterator.hasNext()){
            Object o = iterator.next();
            if(o.toString().equals("two")){
                //list.remove(o);
                iterator.remove();   //注意是使用 iteator的 remove 方法
            }
        }
        System.out.println(list); // [one]
    }


    public static void main(String[] args) {
        VectorAndArrayList list = new VectorAndArrayList();
//        list.test();
        list.removeTest();
    }
}


/**
 * 栈 stack
 */
class StackDemo{
    public void test(){
        Stack<Object> stack = new Stack<>();
        stack.push("zhangsan");
        stack.push("lisi");
        System.out.println(stack.peek());
        System.out.println(stack.size());
    }

    public static void main(String[] args) {
        StackDemo demo = new StackDemo();
        demo.test();
    }
}


/**
 * 测试使用 HashTable 和 HashMap
 */
class HashTableAndMap{
    public void test(){
        Hashtable<Object, Object> hashtable = new Hashtable<>();
        HashMap<Object, Object> hashMap = new HashMap<>();

//        hashtable.put(null, null);
//        hashMap.put(null, null);
//        hashMap.put(null, 1);
//        hashMap.put(null, null);
//        System.out.println(hashMap.size());

        for(int i=0; i<100; i++){
            hashMap.put(i, "i= " + i);
        }
        System.out.println(hashMap.size());

        for(int i=0; i<hashMap.size(); i++){
            System.out.println(hashMap.get(i));
            if(i == 50)
                hashMap.remove(i);
        }
    }

    public static void main(String[] args) {
        HashTableAndMap tableAndMap = new HashTableAndMap();
        tableAndMap.test();
    }
}
