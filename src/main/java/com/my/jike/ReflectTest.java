package com.my.jike;

import lombok.Data;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @Author sunjinwei
 * @Date 2019-12-17 14:18
 * @Description
 * @see https://www.cnblogs.com/chanshuyi/p/head_first_of_reflection.html
 **/
public class ReflectTest {
}



@Data
class Apple{
    private int price;
    public String name;

    public static void main(String[] args) throws Exception {

        /*//直接初始化，正射
        Apple apple = new Apple();
        apple.setPrice(5);
        System.out.println("Apple Price:" + apple.getPrice());

        //使用反射调用
        //1. 先反射获取对象实例
        Class<?> clz = Class.forName("com.my.jike.Apple");

        //2. 反射获取方法名
        Method setPriceMethod = clz.getMethod("setPrice", int.class);

        //3. 根据Class对象获取构造对象
        Constructor<?> constructor = clz.getConstructor();

        //4. 获取反射类对象
        Object appleObj = constructor.newInstance();

        //5. 反射类方法
        setPriceMethod.invoke(appleObj, 14);

        Method getPriceMethod = clz.getMethod("getPrice");
        System.out.println("Apple Price:" + getPriceMethod.invoke(appleObj));

        Class<? extends Apple> aClass = apple.getClass();
        System.out.println(aClass);*/

        /*//通过反射创建类对象
        //第一种方式通过 Class 对象的 newInstance() 方法
        Class<Apple> clz = Apple.class;
        Apple apple = clz.newInstance();  //只能调用无参构造方法
        System.out.println(apple);

        //第二种方式通过 Constructor 对象的 newInstance() 方法
        Class<Apple> clz2 = Apple.class;
        Constructor<Apple> constructor = clz2.getConstructor();
        //Constructor constructor = clz.getConstructor(String.class, int.class); //可以指定有参构造方法
        Apple apple1 = constructor.newInstance();
        System.out.println(apple1);*/

        //通过反射获取类属性、方法、构造器
        Class<Apple> clz = Apple.class;
        Field[] fields = clz.getFields();
        for(Field field : fields){
            System.out.println("public field:" + field.getName());
        }

        Class clz2 = Apple.class;
        Field[] fields2 = clz.getDeclaredFields();
        for (Field field : fields2) {
            System.out.println("all field:" + field.getName());
        }
    }
}