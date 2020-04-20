package com.neu.edu.oms.dao;

import com.neu.edu.oms.entity.Point;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface PointMapper {

    //获得选定科目的所有知识点
    List<Point> selectBySubjectId(Integer subjectId);


    int deleteByPrimaryKey(Integer pointId);

    int insert(Point record);

    int insertSelective(Point record);

    Point selectByPrimaryKey(Integer pointId);

    int updateByPrimaryKeySelective(Point record);

    int updateByPrimaryKey(Point record);
}