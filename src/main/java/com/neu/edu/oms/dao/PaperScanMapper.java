package com.neu.edu.oms.dao;

import com.neu.edu.oms.entity.PaperScan;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PaperScanMapper {
    int deleteByPrimaryKey(Integer paperScanId);

    int insert(PaperScan record);

    int insertSelective(PaperScan record);

    PaperScan selectByPrimaryKey(Integer paperScanId);

    int updateByPrimaryKeySelective(PaperScan record);

    int updateByPrimaryKey(PaperScan record);
}