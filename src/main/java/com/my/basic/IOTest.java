package com.my.basic;

import java.io.*;

/**
 * 测试使用 IO 流相关
 */
public class IOTest {

    public static void main(String[] args) throws Exception {
//        TestFileInputOutputStreamDemo fileInputOutputStreamDemo = new TestFileInputOutputStreamDemo();
//        fileInputOutputStreamDemo.copyFile("F:\\新建文件夹\\aa.txt",  "F:\\新建文件夹\\bb.txt");
//        fileInputOutputStreamDemo.copyFile("F:\\新建文件夹\\cc.wmv",  "F:\\新建文件夹\\mm.wmv");

//        TestFileDemo testFileDemo = new TestFileDemo();
//        testFileDemo.testFile();


//        TestBufferInputStreamDemo testBufferInputStreamDemo = new TestBufferInputStreamDemo();
//        testBufferInputStreamDemo.test();

//        TestBufferedReader testBufferedReader = new TestBufferedReader();
//        testBufferedReader.test();


//        TestInputStreamReaderDemo testBufferInputStreamDemo = new TestInputStreamReaderDemo();
//        testBufferInputStreamDemo.test();

            TestByteArrayInputStream byteArrayInputStream = new TestByteArrayInputStream();
            byteArrayInputStream.test();
    }

}


/**
 * 字节流复制文件
 */
class TestFileInputOutputStreamDemo{
    public void copyFile(String oldpath, String newpath) {
        FileInputStream in = null;
        FileOutputStream out = null;


        try {
            in = new FileInputStream(oldpath);
            out = new FileOutputStream(newpath);

            int size = in.available();  //获取可读取到的字节
            byte[] bytes = new byte[size];  //将字节放入到数组中
            in.read(bytes);

            String result = new String(bytes);
            System.out.println(result);  //输出读取到的字节流数组

            out.flush();
            out.write(bytes);
        } catch (Exception e){
            e.printStackTrace();
        }  finally{
            if(out!=null){
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}


/**
 * 创建使用 File
 */
class TestFileDemo{
    public void testFile(){
        File file = new File("F:\\");
        String[] list = file.list();  //循环输出文件列表名字
        for(String s : list){
            System.out.println(s);
        }
    }
}


/**
 * 使用缓冲流
 */
class TestBufferInputStreamDemo{
    public void test(){
        BufferedInputStream bin = null;
        BufferedOutputStream bout = null;
        try {
            InputStream in = new FileInputStream("F:\\新建文件夹\\cc.wmv");   //InputStream 是抽象类，使用多态特性
            FileOutputStream out = new FileOutputStream("F:\\新建文件夹\\dd.wmv");

            bin = new BufferedInputStream(in);  //将节点流上套上装饰流
            bout = new BufferedOutputStream(out);
            byte[] b = new byte[1024];   //限制每次读取 1024=1M 大小的数据

            int length = 0;   //实际存储读取的字节大小
            while ( (length = bin.read(b)) != -1){
                bout.write(b, 0, length);
            }

            bout.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(bout!=null){
                try {
                    bout.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(bin!=null){
                try {
                    bin.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}


/**
 * 使用 BufferReader 读取文本
 */
class TestBufferedReader{
    public void test(){
        BufferedReader in = null;
        BufferedWriter out = null;

        try {
            FileReader fr = new FileReader("F:\\新建文件夹\\aa.txt");
            in = new BufferedReader(fr);  //套装上字符流

            FileWriter fw = new FileWriter("F:\\新建文件夹\\bb.txt");
            out = new BufferedWriter(fw);

            String line = null;
            while ( (line = in.readLine()) !=null){  //每次读取一行
                out.write(line);
                out.newLine();   //每次读取完需要换行
            }

            out.flush();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(out != null){
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(in != null){
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}


/**
 * 字节流到字符流的转换
 */
class TestInputStreamReaderDemo{
    public void test(){
        InputStreamReader in = null;
        OutputStreamWriter out = null;

        try {
            FileInputStream fileInputStream = new FileInputStream("F:\\新建文件夹\\aa.txt");
            FileOutputStream fileOutputStream = new FileOutputStream("F:\\新建文件夹\\cc.txt");

            in = new InputStreamReader(fileInputStream);
            out = new OutputStreamWriter(fileOutputStream);

            int size = 0;
            while( (size = in.read()) != -1){  //一次只能读取一个字符
                out.write(size);   //一次只能写入一个字符
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if( out!=null ){
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}


/**
 * 字节流输入输出
 */
class TestByteArrayInputStream{
    public void test(){
        String s = "Hello world!张三";
        byte[] bytes = s.getBytes();  //转为字节数组

        ByteArrayInputStream bin = new ByteArrayInputStream(bytes);
        int size = 0;
        while ( (size = bin.read()) != -1){    //一个字节字节读取，中文会乱码哦
            System.out.println((char)size);
        }

    }
}


