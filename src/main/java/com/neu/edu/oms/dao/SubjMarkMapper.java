package com.neu.edu.oms.dao;

import com.neu.edu.oms.entity.SubjMark;

public interface SubjMarkMapper {
    int deleteByPrimaryKey(Integer subjMarkId);

    int insert(SubjMark record);

    int insertSelective(SubjMark record);

    SubjMark selectByPrimaryKey(Integer subjMarkId);

    int updateByPrimaryKeySelective(SubjMark record);

    int updateByPrimaryKey(SubjMark record);
}