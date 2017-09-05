package com.my.basic;

import java.io.*;

/**
 * 测试序列化
 */
public class SerializeDemo {

    public static void main(String[] args) {
        Employ emp = new Employ();
        emp.name = "Reyan Ali";
        emp.address = "Phokka Kuan, Ambehta Peer";
        emp.SSN = 11122333;
        emp.number = 101;

        //序列化对象到硬盘
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("e:/demo/employ.ser"); //.ser的扩展名可以任意
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(emp);
            objectOutputStream.flush();
            objectOutputStream.close();
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}


/**
 * 反序列化操作
 */
class DeserializeDemo{

    public static void main(String[] args) {
        try {
            FileInputStream fileInputStream = new FileInputStream("e:/demo/employ.ser");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            Object o = objectInputStream.readObject();
            Employ emp = (Employ) o;
            System.out.println(emp);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}