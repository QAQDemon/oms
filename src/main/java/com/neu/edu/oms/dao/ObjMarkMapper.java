package com.neu.edu.oms.dao;

import com.neu.edu.oms.entity.ObjMark;

public interface ObjMarkMapper {
    int deleteByPrimaryKey(Integer objMarkId);

    int insert(ObjMark record);

    int insertSelective(ObjMark record);

    ObjMark selectByPrimaryKey(Integer objMarkId);

    int updateByPrimaryKeySelective(ObjMark record);

    int updateByPrimaryKey(ObjMark record);
}