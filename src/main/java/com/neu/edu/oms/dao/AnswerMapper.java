package com.neu.edu.oms.dao;

import com.neu.edu.oms.entity.Answer;
import java.util.List;

public interface AnswerMapper {
    //获得所有当前科目的试卷，未删除的
    List<Answer> selectBySubjectId(Integer subjectId);

    int deleteByPrimaryKey(Integer answerId);

    int insert(Answer record);

    int insertSelective(Answer record);

    Answer selectByPrimaryKey(Integer answerId);

    int updateByPrimaryKeySelective(Answer record);

    int updateByPrimaryKey(Answer record);
}