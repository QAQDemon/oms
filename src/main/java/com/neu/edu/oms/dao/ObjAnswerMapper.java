package com.neu.edu.oms.dao;

import com.neu.edu.oms.entity.ObjAnswer;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface ObjAnswerMapper {

    //获得选定试卷的客观题答案
    List<ObjAnswer> selectByAnswerId(Integer answerId);
    //更新客观题答案，如果主键不存在则插入
    int updateAndInsert(List<ObjAnswer> objAnswers);

    int deleteByPrimaryKey(Integer objAnswerId);

    int insert(ObjAnswer record);

    int insertSelective(ObjAnswer record);

    ObjAnswer selectByPrimaryKey(Integer objAnswerId);

    int updateByPrimaryKeySelective(ObjAnswer record);

    int updateByPrimaryKey(ObjAnswer record);
}