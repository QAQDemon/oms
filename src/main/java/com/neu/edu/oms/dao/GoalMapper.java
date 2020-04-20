package com.neu.edu.oms.dao;

import com.neu.edu.oms.entity.Goal;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface GoalMapper {

    //获得选定科目的所有考题目标
    List<Goal> selectBySubjectId(Integer subjectId);

    int deleteByPrimaryKey(Integer goalId);

    int insert(Goal record);

    int insertSelective(Goal record);

    Goal selectByPrimaryKey(Integer goalId);

    int updateByPrimaryKeySelective(Goal record);

    int updateByPrimaryKey(Goal record);
}