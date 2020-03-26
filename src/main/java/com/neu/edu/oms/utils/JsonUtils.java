package com.neu.edu.oms.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.neu.edu.oms.entity.AnswerSheet;

import java.util.Map;

public class JsonUtils {

    /*
     * @Description 将json转换为map类型
     * @Param [json]
     * @return java.util.Map<java.lang.String,java.lang.String>
     **/
    public static Map<String,String> parseToMap(String json){
        return new Gson().fromJson(json,new TypeToken<Map<String,String>>(){}.getType());
    }

    /*
     * @Description 将json转换为AnswerSheet类型
     * @Param [json]
     * @return com.neu.edu.oms.entity.AnswerSheet
     **/
    public static AnswerSheet parseToAnswerSheet(String json){
        return new Gson().fromJson(json,AnswerSheet.class);
    }
}
