package com.neu.edu.oms.dao;

import com.neu.edu.oms.entity.AnswerSheet;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AnswerSheetMapper {
    //获得所有记录，除了删除了的
    List<AnswerSheet> selectExceptDeleted();

    int deleteByPrimaryKey(Integer answerSheetId);

    int insert(AnswerSheet record);

    int insertSelective(AnswerSheet record);

    AnswerSheet selectByPrimaryKey(Integer answerSheetId);

    int updateByPrimaryKeySelective(AnswerSheet record);

    int updateByPrimaryKey(AnswerSheet record);
}