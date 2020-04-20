package com.neu.edu.oms.dao;

import com.neu.edu.oms.entity.Subject;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface SubjectMapper {
    //获得所有记录
    List<Subject> selectAll();

    int deleteByPrimaryKey(Integer subjectId);

    int insert(Subject record);

    int insertSelective(Subject record);

    Subject selectByPrimaryKey(Integer subjectId);

    int updateByPrimaryKeySelective(Subject record);

    int updateByPrimaryKey(Subject record);
}