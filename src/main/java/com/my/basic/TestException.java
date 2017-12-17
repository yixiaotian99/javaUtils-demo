package com.my.basic;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sunjw on 2017/11/30 0030.
 */
public class TestException {


}


/**
 * 测试堆内存溢出
 */
class TestHeapOOM{

    static class OOMObject{  //定义静态类

    }

    public static void main(String[] args) {
        List<OOMObject> arrayList = new ArrayList<>();
        while (true){
            arrayList.add(new OOMObject());
        }
    }
}














