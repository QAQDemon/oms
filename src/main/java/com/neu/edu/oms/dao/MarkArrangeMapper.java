package com.neu.edu.oms.dao;

import com.neu.edu.oms.entity.MarkArrange;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MarkArrangeMapper {
    int deleteByPrimaryKey(Integer markArrangeId);

    int insert(MarkArrange record);

    int insertSelective(MarkArrange record);

    MarkArrange selectByPrimaryKey(Integer markArrangeId);

    int updateByPrimaryKeySelective(MarkArrange record);

    int updateByPrimaryKey(MarkArrange record);
}