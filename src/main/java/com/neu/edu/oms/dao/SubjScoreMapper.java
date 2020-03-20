package com.neu.edu.oms.dao;

import com.neu.edu.oms.entity.SubjScore;

public interface SubjScoreMapper {
    int deleteByPrimaryKey(Integer subjScoreId);

    int insert(SubjScore record);

    int insertSelective(SubjScore record);

    SubjScore selectByPrimaryKey(Integer subjScoreId);

    int updateByPrimaryKeySelective(SubjScore record);

    int updateByPrimaryKey(SubjScore record);
}