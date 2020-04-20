package com.neu.edu.oms.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.neu.edu.oms.entity.AnswerSheet;
import com.neu.edu.oms.entity.SubjAnswer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/*
 * @Description
 * @author demon
 * @version v1.0
 **/
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

    /*
     * @Description 将json转换为List<SubjAnswer>类型,先转换成数组
     * @Param [json]
     * @return java.util.List<com.neu.edu.oms.entity.SubjAnswer>
     **/
    public static List<SubjAnswer> parseToSubjAnswerList(String json){
        SubjAnswer[] subjAnswers=new Gson().fromJson(json,SubjAnswer[].class);
        return new ArrayList<>(Arrays.asList(subjAnswers));
    }
}
