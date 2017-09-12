package com.my.basic;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * 测试使用 IO 流相关
 */
public class IOTest {

    public static void main(String[] args) throws Exception {
        FileInputOutputStreamDemo fileInputOutputStreamDemo = new FileInputOutputStreamDemo();
//        fileInputOutputStreamDemo.copyFile("F:\\新建文件夹\\aa.txt",  "F:\\新建文件夹\\bb.txt");
        fileInputOutputStreamDemo.copyFile("F:\\新建文件夹\\cc.wmv",  "F:\\新建文件夹\\mm.wmv");
    }

}


/**
 * 字节流复制文件
 */
class FileInputOutputStreamDemo{
    public void copyFile(String oldpath, String newpath) throws Exception {
        FileInputStream in = new FileInputStream(oldpath);
        FileOutputStream out = new FileOutputStream(newpath);

        int size = in.available();  //获取可读取到的字节
        byte[] bytes = new byte[size];  //将字节放入到数组中
        in.read(bytes);

        String result = new String(bytes);
        System.out.println(result);  //输出读取到的字节流数组

        out.write(bytes);

        out.flush();
        out.close();
        in.close();
    }
}
