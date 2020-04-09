package com.neu.edu.oms.dao;

import com.neu.edu.oms.entity.Answer;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface AnswerMapper {
    int deleteByPrimaryKey(Integer answerId);

    int insert(Answer record);

    int insertSelective(Answer record);

    Answer selectByPrimaryKey(Integer answerId);

    int updateByPrimaryKeySelective(Answer record);

    int updateByPrimaryKey(Answer record);
    //选取所有的答案
    List<Answer> getAllAnswer();
}