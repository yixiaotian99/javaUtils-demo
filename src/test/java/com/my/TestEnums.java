package com.my;

import com.my.demo.ColorEnum;
import org.junit.Test;

/**
 * Created by jinwei.sun on 2017/5/31.
 */
public class TestEnums {

    @Test
    public void testColor(){
        System.out.println("测试枚举:" + ColorEnum.RED.getIndex());
        System.out.println("测试枚举:" + ColorEnum.RED.getName());
        System.out.println("测试枚举:" + ColorEnum.RED.getDesc());
    }
}
