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


/**
 * 属性
 */
class PropertiesDemo{
    public void test(){
        Properties properties = new Properties();
        properties.put("one", "one properties");
        properties.put("two", "two properties");
        properties.put("three", "three properties");

        Set<Object> set = properties.keySet();
        Iterator<Object> iterator = set.iterator();
        while (iterator.hasNext()){
            Object next = iterator.next();
            System.out.println(next + ":" + properties.get(next));
        }
    }

    public static void main(String[] args) {
        PropertiesDemo demo = new PropertiesDemo();
        demo.test();
    }
}


/**
 * Collections
 */
class CollectionsDemo{
    public void test(){
        ArrayList<Integer> list = new ArrayList<>();
        list.add(2);
        list.add(1);
        list.add(5);

//        Collections.sort(list);
//        System.out.println(list); //[1, 2, 5]

//        System.out.println(Collections.min(list)); //输出 1

        ArrayList<Integer> list2 = new ArrayList<>(Arrays.asList(new Integer[list.size()])); //必须使用这种方式初始化，否则会报 not fit 错误
        Collections.copy(list2, list);
        System.out.println(list2);  //[2, 1, 5]
    }

    public static void main(String[] args) {
        CollectionsDemo demo = new CollectionsDemo();
        demo.test();
    }
}


/**
 * 泛型方法
 */
class GenericsFunctionDemo{

    public static void main(String[] args) {
//        Integer[] array = {1,3,5};
//        String[] array2 = new String[]{"zhangsan", "lisi", "wangwu"};
//        printArray(array);
//        printArray(array2);

//        getMax(2, 1, 6);
//        getMax(2.2, 5.3, 1.8);
//        getMax("apple", "orange", "banana");

        List<Integer> intList = new ArrayList<>();
        List<String> stringList = new ArrayList<>();
        getData(intList);
        //getData(stringList);  调用报错，不兼容? extends Number 只能为 Number 或子类
    }

    //泛型方法： 参数使用 E 来表示  在返回类型前使用 <E> 标志 表示 element 元素
    public static <E> void printArray(E[] array){
        for(E element : array){
            System.out.println(element);
        }
    }

    //返回最大值 使用 extends 表示继承
    public static <T extends Comparable<T>> T getMax(T a, T b, T c){
        T max = a;   //默认第一个数为最大值
        //System.out.println(b.compareTo(max));
        if( b.compareTo(max) > 0){
            max = b;
        }

        if( c.compareTo(max) > 0){
            max = c;
        }
        System.out.println(String.format("最大值: %s", max));
        return max;
    }

    //限定只定的类型
    public static <T> void getData(List<? extends Number> data){  //这里只能是任意类型 ? 来继承，不能使用 T 明确类型
        System.out.println("data: " + data);
    }
}


/**
 * 泛型类
 */
class GenericsClassDemo <T>{
    private T t;

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }

    @Override
    public String toString() {
        return "GenericsClassDemo{" +
                "t=" + t +
                '}';
    }

    public static void main(String[] args) {
        GenericsClassDemo<Integer> integerDemo = new GenericsClassDemo<>();
        GenericsClassDemo<String> stringDemo = new GenericsClassDemo<>();

        integerDemo.t = 123;
        stringDemo.t = "zhangsan";

        System.out.println(integerDemo);
        System.out.println(stringDemo);
    }
}












