package com.neu.edu.oms.dao;

import com.neu.edu.oms.entity.Goal;

public interface GoalMapper {
    int deleteByPrimaryKey(Integer goalId);

    int insert(Goal record);

    int insertSelective(Goal record);

    Goal selectByPrimaryKey(Integer goalId);

    int updateByPrimaryKeySelective(Goal record);

    int updateByPrimaryKey(Goal record);
}