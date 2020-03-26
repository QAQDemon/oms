package com.neu.edu.oms.controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.neu.edu.oms.entity.AnswerSheet;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Type;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class MakeTemplateControllerTest {

    @Test
    void uploadExcel() {
        Gson gson = new Gson();
        Type type=new TypeToken<Map<String,Object>>(){}.getType();
        String ss="{\"answerSheetName\":\"sj\",\"objNum\":2,\"administratorId\":3,\"subjNum\":4}";
        AnswerSheet aa=gson.fromJson(ss,AnswerSheet.class);
        //AnswerSheet aa=map1.get("answerSheet").;
        return;
    }
}