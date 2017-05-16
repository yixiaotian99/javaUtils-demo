package com.my;

import com.my.json.FastjsonUtils;
import org.junit.Ignore;
import org.junit.Test;

/**
 * Created by jinwei.sun on 2017/4/27.
 */
public class TestFastJson {

    private FastjsonUtils fastjsonUtils = new FastjsonUtils();


    /**
     * java -> json
     */
    @Ignore
    @Test
    public void testJavaToJson(){
        fastjsonUtils.javaToJson();
    }


    @Test
    public void testJsonToJava(){
        fastjsonUtils.jsonToJava();
    }


    @Test
    public void testBeanToArray(){
        fastjsonUtils.beanToArray();
    }

    @Test
    public void testBeanToArrayPart(){
        fastjsonUtils.beanToArrayPart();
    }


    @Test
    public void testFileSerial(){
        fastjsonUtils.fileSerial();
    }


    @Test
    public void testBeanUsingSerialize(){
        fastjsonUtils.beanUsingSerialize();
    }


    @Test
    public void testMoreNameSerial(){
        fastjsonUtils.moreNameSerial();
    }


    @Test
    public void testJSON2Map(){
        fastjsonUtils.json2Map();
    }
}
