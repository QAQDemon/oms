package com.neu.edu.oms.dao;

import com.neu.edu.oms.entity.Answer;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface AnswerMapper {
    //获得所有当前科目的试卷，未删除的
    List<Answer> selectBySubjectId(Integer subjectId);

    int deleteByPrimaryKey(Integer answerId);

    int insert(Answer record);

    int insertSelective(Answer record);

    Answer selectByPrimaryKey(Integer answerId);

    int updateByPrimaryKeySelective(Answer record);

    int updateByPrimaryKey(Answer record);
    //选取所有的答案
    List<Answer> getAllAnswer();
//    根据answerId列表选取answer列表
    List<Answer> getAnswerListByanswerIdList(List<Integer> answerIdList);
}