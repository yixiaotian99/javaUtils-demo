package com.my.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by sunjw on 2018/1/22 0022.
 */
public class TestLog4j {

    private static final Logger logger = LoggerFactory.getLogger(TestLog4j.class);


    public static void main(String[] args) {

        try {
            System.out.println(1/0);
        } catch (Exception e) {
            logger.error("测试打印输出{} {} {}", e, "点位符1", "占位符2");
        }


    }

}
