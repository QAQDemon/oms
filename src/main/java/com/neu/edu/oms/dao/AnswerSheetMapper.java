package com.neu.edu.oms.dao;

import com.neu.edu.oms.entity.AnswerSheet;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AnswerSheetMapper {
    int deleteByPrimaryKey(Integer answerSheetId);

    int insert(AnswerSheet record);

    int insertSelective(AnswerSheet record);

    AnswerSheet selectByPrimaryKey(Integer answerSheetId);

    int updateByPrimaryKeySelective(AnswerSheet record);

    int updateByPrimaryKey(AnswerSheet record);
}