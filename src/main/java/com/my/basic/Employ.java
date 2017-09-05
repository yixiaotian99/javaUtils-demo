package com.my.basic;

import java.io.Serializable;

/**
 * 一个类如果想序列化，必须实现 Serializable 接口
 */
public class Employ implements Serializable{
    public String name;
    public String address;
    public transient int SSN;   //使用 transient 修饰的类不会被序列化
    public int number;

    @Override
    public String toString() {
        return "Employ{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", SSN=" + SSN +
                ", number=" + number +
                '}';
    }
}
