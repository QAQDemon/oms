package com.neu.edu.oms.dao;

import com.neu.edu.oms.entity.Administrator;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AdministratorMapper {
    int deleteByPrimaryKey(Integer administratorId);

    int insert(Administrator record);

    int insertSelective(Administrator record);

    Administrator selectByPrimaryKey(Integer administratorId);

    int updateByPrimaryKeySelective(Administrator record);

    int updateByPrimaryKey(Administrator record);
}