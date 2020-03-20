package com.neu.edu.oms.dao;

import com.neu.edu.oms.entity.SubjAnswer;

public interface SubjAnswerMapper {
    int deleteByPrimaryKey(Integer subjAnswerId);

    int insert(SubjAnswer record);

    int insertSelective(SubjAnswer record);

    SubjAnswer selectByPrimaryKey(Integer subjAnswerId);

    int updateByPrimaryKeySelective(SubjAnswer record);

    int updateByPrimaryKey(SubjAnswer record);
}