package com.neu.edu.oms.dao;

import com.neu.edu.oms.entity.ObjAnswer;

public interface ObjAnswerMapper {
    int deleteByPrimaryKey(Integer objAnswerId);

    int insert(ObjAnswer record);

    int insertSelective(ObjAnswer record);

    ObjAnswer selectByPrimaryKey(Integer objAnswerId);

    int updateByPrimaryKeySelective(ObjAnswer record);

    int updateByPrimaryKey(ObjAnswer record);
}