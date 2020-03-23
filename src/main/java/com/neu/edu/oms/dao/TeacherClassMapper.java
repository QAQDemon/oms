package com.neu.edu.oms.dao;

import com.neu.edu.oms.entity.TeacherClass;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TeacherClassMapper {
    int deleteByPrimaryKey(Integer tcId);

    int insert(TeacherClass record);

    int insertSelective(TeacherClass record);

    TeacherClass selectByPrimaryKey(Integer tcId);

    int updateByPrimaryKeySelective(TeacherClass record);

    int updateByPrimaryKey(TeacherClass record);
}