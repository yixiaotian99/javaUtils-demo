package com.my.util;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xiaoleilu.hutool.json.JSONArray;
import com.xiaoleilu.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by sunjinwei on 2018/2/6.
 *
 * @author sunjinwei
 *         <p>
 *         调取其他人的接口返回的 json 解析
 */
@Slf4j
public class Json2Map {

    public static void main(String[] args) {
        String json = "{ data=[userName='张三', passwd='123456'], success=true, xxx}";
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("json", json);

        String data = resultMap.get("data").toString();
        JSONArray jsonArray = JSONUtil.parseArray(data.replaceAll("=", ":"));
        log.info("### method:{}, jsonArray:{}",
                "appMsgService.queryMsgSendArriveOpenData==3", jsonArray);


        //排除没有查询到记录，或者返回记录为空的数据
        if (jsonArray.size() > 0) {

            //将字符串转换标准json
            String jsonStr = jsonArray.get(0).toString();
            JSONObject jsonObject = JSONObject.parseObject(jsonStr);


            //解析成字符串取值
            try {
                ObjectMapper objectMapper = new ObjectMapper();
                Map outputMap = objectMapper.readValue(JSONUtil.toJsonStr(jsonObject), Map.class);
                String userName = outputMap.get("userName").toString();

                log.info("userName:{}", userName);


            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            log.warn("### method:{}, cause:{}, resultMap:{}",
                    "appMsgService.queryMsgSendArriveOpenData==6", "resultMap.get(data) is null", data);
        }
    }

}
