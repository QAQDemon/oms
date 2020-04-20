package com.neu.edu.oms.dao;

import com.neu.edu.oms.entity.Class;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface ClassMapper {
    int deleteByPrimaryKey(Integer classId);

    int insert(Class record);

    int insertSelective(Class record);

    Class selectByPrimaryKey(Integer classId);

    int updateByPrimaryKeySelective(Class record);

    int updateByPrimaryKey(Class record);
    //通过教师id选取其教授的班级
    List<Class> getClassListByTeacerId(Integer teacherId);
}