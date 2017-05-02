package com.my.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.my.model.*;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by jinwei.sun on 2017/4/27.
 * Fastjons 是 Java 语言编写的 JSON 库，是目前最快的 JSON 库
 */
public class FastjsonUtils {

    /**
     * 将普通嵌套对象 Group 转为 json
     */
    public void javaToJson(){
        Group group = new Group(0l, "组1");
        User user1 = new User(1l, "人员1");
        User user2 = new User(2l, "人员2");

        group.setUsers(Arrays.asList(user1, user2));
        System.out.println(group.toString());

        String jsonString = JSON.toJSONString(group);
        System.out.println(jsonString);
    }


    /**
     * 将普通 json 转为 Java 对象
     */
    public void jsonToJava(){
        String json = "{\"groupName\":\"组1\",\"id\":0,\"users\":[{\"id\":1,\"userName\":\"人员1\"},{\"id\":2,\"userName\":\"人员2\"}]}";
        System.out.println(json);

        Group group = JSON.parseObject(json, Group.class);
        System.out.println(group.toString());
        System.out.println(group.getUsers().get(0).getUserName());
    }

    /**
     * BeanToArray 映射
     */
    public void beanToArray(){
        Model model = new Model(1001, "gaotie");
        String text_normal = JSON.toJSONString(model);
        System.out.println("普通模式:" + text_normal);   //普通模式:{"id":1001,"name":"gaotie"}

        String text_beanToArray = JSON.toJSONString(model, SerializerFeature.BeanToArray);
        System.out.println("省略属性名模式:" + text_beanToArray); //省略属性名模式:[1001,"gaotie"]
    }


    /**
     * 嵌套属性，只局部进行 BeanToArray
     */
    public void beanToArrayPart(){
        Company company = new Company(100);
        company.departments.add(new Department(1001, "Sales"));
        company.departments.add(new Department(1002, "Financial"));

        String jsonString = JSON.toJSONString(company);
        System.out.println("局部省略属性名:" + jsonString);  //局部省略属性名:{"code":100,"departments":[[1001,"Sales"],[1002,"Financial"]]}
    }


    /**
     * 按字段序列化，添加序列化别名，格式化转换
     */
    public void fileSerial(){
        AA aa = new AA(1, new Date(), new Date(), "f0", "f1");
        String jsonString = JSON.toJSONString(aa);
        System.out.println(jsonString);
    }


    /**
     * 对类中某个属性定制序列化
     */
    public void beanUsingSerialize(){
        Model model = new Model();
        model.setName("哈哈");
        model.setValue(100);

        String string = JSON.toJSONString(model);
        System.out.println(string);
    }


    /**
     * 反序列化使用多个不同的字段名进行属性映射
     */
    public void moreNameSerial(){
        String s1 = "{\"id\":5001,\"phone\":\"123\"}";
        String s2 = "{\"id\":5001,\"mobile\":\"456\"}";
        String s3 = "{\"id\":5001,\"email\":\"789\"}";

        System.out.println(JSON.parseObject(s1, Model.class));
        System.out.println(JSON.parseObject(s2, Model.class));
        System.out.println(JSON.parseObject(s3, Model.class));
    }

}
