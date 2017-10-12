package com.my.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class JacksonUtil {
    private static final JacksonUtil jacksonUtil = new JacksonUtil();
    private ObjectMapper objectMapper = new ObjectMapper();

    private JacksonUtil() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.objectMapper.setDateFormat(dateFormat);
    }

    public static final JacksonUtil Builder() {
        return jacksonUtil;
    }

    public String obj2Json(Object obj) throws Exception {
        return obj == null ? null : this.objectMapper.writeValueAsString(obj);
    }

    public <T> T json2Model(String jsonStr, Class<T> clazz) throws Exception {
        return jsonStr == null ? null : this.objectMapper.readValue(jsonStr, clazz);
    }

    public <T> T json2Model(InputStream jsonStream, Class<T> clazz) throws Exception {
        return jsonStream == null ? null : this.objectMapper.readValue(jsonStream, clazz);
    }

    public String map2Json(Map<String, Object> map) throws Exception {
        return map == null ? null : this.objectMapper.writeValueAsString(map);
    }

    public Map<String, Object> json2Map(String jsonStr) throws Exception {
        return jsonStr == null ? null : (Map)this.objectMapper.readValue(jsonStr, Map.class);
    }

    public Map<String, Object> json2Map(InputStream jsonStream) throws Exception {
        return jsonStream == null ? null : (Map)this.objectMapper.readValue(jsonStream, Map.class);
    }

    public <T> Map<String, T> json2Map(String jsonStr, Class<T> clazz) throws Exception {
        return (Map)this.objectMapper.readValue(jsonStr, new TypeReference<Map<String, T>>() {
        });
    }

    public <T> Map<String, T> json2Map(InputStream jsonStream, Class<T> clazz) throws Exception {
        return (Map)this.objectMapper.readValue(jsonStream, new TypeReference<Map<String, T>>() {
        });
    }

    public <T> List<T> json2List(String jsonStr, Class<T> clazz) throws Exception {
        List<T> result = new ArrayList();
        List<Map<String, Object>> list = (List)this.objectMapper.readValue(jsonStr, new TypeReference<List<T>>() {
        });
        Iterator i$ = list.iterator();

        while(i$.hasNext()) {
            Map<String, Object> map = (Map)i$.next();
            result.add(this.map2Model(map, clazz));
        }

        return result;
    }

    public <T> List<T> json2List(InputStream jsonStream, Class<T> clazz) throws Exception {
        List<T> result = new ArrayList();
        List<Map<String, Object>> list = (List)this.objectMapper.readValue(jsonStream, new TypeReference<List<T>>() {
        });
        Iterator i$ = list.iterator();

        while(i$.hasNext()) {
            Map<String, Object> map = (Map)i$.next();
            result.add(this.map2Model(map, clazz));
        }

        return result;
    }

    public <T> T map2Model(Map<?, ?> map, Class<T> clazz) {
        return this.objectMapper.convertValue(map, clazz);
    }

    public Map<String, Object> model2Map(Object obj) {
        return (Map)this.objectMapper.convertValue(obj, Map.class);
    }
}


class TestJacksonUtil{
    public static void main(String[] args) throws Exception {
        String activityId = "{\"activityId\":\"22\"}";
        Map<String, Object> json2Map = JacksonUtil.Builder().json2Map(activityId);
        System.out.println(json2Map);
    }
}