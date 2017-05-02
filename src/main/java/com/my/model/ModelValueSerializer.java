package com.my.model;

import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.ObjectSerializer;

import java.io.IOException;
import java.lang.reflect.Type;

/**
 * Created by jinwei.sun on 2017/4/27.
 */
public class ModelValueSerializer implements ObjectSerializer{
    @Override
    public void write(JSONSerializer jsonSerializer, Object o, Object o1, Type type, int i) throws IOException {
        //System.out.println("object:" + o.toString());
        //System.out.println("object:" + o1.toString());

        if(o1.toString().equals("value")){
            Integer value = (Integer) o;
            String text = value + "元";
            jsonSerializer.write(text);
        }
    }
}
