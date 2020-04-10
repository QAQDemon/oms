package com.neu.edu.oms.dao;

import com.neu.edu.oms.entity.Student;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StudentMapper {
    int deleteByPrimaryKey(Integer studentId);

    int insert(Student record);

    int insertSelective(Student record);

    Student selectByPrimaryKey(Integer studentId);

    int updateByPrimaryKeySelective(Student record);

    int updateByPrimaryKey(Student record);
    //选取出所有学生
    List<Student> getAllStudent();
    //根据班级id选取所有的学生id列表
    List<Integer> getStudentIdListByclassId(Integer classId);
}