package com.my.demo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.my.util.InitConfigure;
import okhttp3.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by jinwei.sun on 2017/5/16.
 */
public class OkhttpUtils {

    OkHttpClient client = new OkHttpClient();

    /**
     * 测试 get 请求
     */
    public void getRequestGetTest() throws IOException {
        String url = "http://" + InitConfigure.getProperty("url") + 3422;
        Request request = new Request.Builder().url(url).build();

        Response response = client.newCall(request).execute();

        System.out.println("响应:" + url );
        if(response.isSuccessful()){
            String s = response.body().string(); //注意是 string() 方法不是 toString()
            //Map<String, Object> map = JSON.parseObject(s, Map.class);
            Map<String, Object> map = JSON.parseObject(s, new TypeReference<Map<String, Object>>() { });

            map.forEach((k,v) -> System.out.println(k + "," + v));
        }
    }


    /**
     * 测试  post 请求
     * @param args
     * @throws IOException
     */
    public static final MediaType JSONSTR  = MediaType.parse("application/json; charset=utf-8");
    public void getRequestPostTest() throws IOException {
        String url = "http://" + InitConfigure.getProperty("url") + 3422;
        Map<String, Object> map = new HashMap<>();
        map.put("url", "url");
        String jsonString = JSON.toJSONString(map);
        RequestBody requestBody = RequestBody.create(JSONSTR, jsonString);

        Request request = new Request.Builder().url(url).post(requestBody).build();
        Response response = client.newCall(request).execute();
        System.out.println(response.body().string());
    }




    public static void main(String[] args) throws IOException {
        OkhttpUtils okhttpUtils = new OkhttpUtils();
        okhttpUtils.getRequestGetTest();
//        okhttpUtils.getRequestPostTest();
    }
}
