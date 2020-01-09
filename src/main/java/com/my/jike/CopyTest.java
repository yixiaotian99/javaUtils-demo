package com.my.jike;

import lombok.Data;

/**
 * @Author sunjinwei
 * @Date 2019-12-16 14:04
 * @Description
 **/
public class CopyTest {

    public static void main(String[] args) throws CloneNotSupportedException {
        //原始对象
        Student stu1 = new Student();
        stu1.setName("杨过");

        Subject sub = new Subject();
        sub.setName("数学");
        stu1.setSubject(sub);
        System.out.println(stu1);

        //浅拷贝
        Student stu2 = (Student) stu1.clone();
        System.out.println(stu2);

        //引用对象变更
        stu2.getSubject().setName("小龙女");

        System.out.println(stu1);
        System.out.println(stu2);
    }
}


@Data
class Subject {
    private String name;
}

@Data
class Student implements Cloneable {

    //引用类型
    private Subject subject;

    //基本类型
    private String name;

    @Override
    protected Object clone() throws CloneNotSupportedException {
        //原始对象
        Student stu1 = new Student();
        stu1.setName("杨过2");

        Subject sub = new Subject();
        sub.setName("数学2");
        stu1.setSubject(sub);
        return stu1;
    }

    /*@Override
    protected Object clone() throws CloneNotSupportedException {
        //浅拷贝
        return super.clone();
    }*/
}



