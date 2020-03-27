package com.neu.edu.oms.dao;

import com.neu.edu.oms.entity.AnswerSheet;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AnswerSheetMapperTest {

    @Resource
    AnswerSheetMapper answerSheetMapper;

    @Test
    void selectExceptDeleted(){
        List<AnswerSheet> list=answerSheetMapper.selectExceptDeleted();
        return;
    }
}