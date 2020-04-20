package com.neu.edu.oms.dao;

import com.neu.edu.oms.entity.SubjAnswer;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface SubjAnswerMapper {

    //获得选定试卷的主观题答案
    List<SubjAnswer> selectByAnswerId(Integer answerId);
    //更新主观题答案，如果主键不存在则插入
    int updateAndInsert(List<SubjAnswer> subjAnswers);

    int deleteByPrimaryKey(Integer subjAnswerId);

    int insert(SubjAnswer record);

    int insertSelective(SubjAnswer record);

    SubjAnswer selectByPrimaryKey(Integer subjAnswerId);

    int updateByPrimaryKeySelective(SubjAnswer record);

    int updateByPrimaryKey(SubjAnswer record);
}