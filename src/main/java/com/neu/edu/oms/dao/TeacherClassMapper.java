package com.neu.edu.oms.dao;

import com.neu.edu.oms.entity.TeacherClass;

public interface TeacherClassMapper {
    int deleteByPrimaryKey(Integer tcId);

    int insert(TeacherClass record);

    int insertSelective(TeacherClass record);

    TeacherClass selectByPrimaryKey(Integer tcId);

    int updateByPrimaryKeySelective(TeacherClass record);

    int updateByPrimaryKey(TeacherClass record);
}