package com.neu.edu.oms.service.impl;

import com.neu.edu.oms.dao.AnswerSheetMapper;
import com.neu.edu.oms.entity.AnswerSheet;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@SpringBootTest
@Transactional
class MakeTemplateServiceImplTest {
    @Resource
    AnswerSheetMapper answerSheetMapper;

    @Test
    void setNewAnswerSheet() {
        //测试插入并返回主键
        AnswerSheet answerSheet=new AnswerSheet();
        answerSheet.setObjNum((short) 5);
        answerSheet.setAdministratorId(1);
        answerSheetMapper.insertSelective(answerSheet);
        return;
    }
}