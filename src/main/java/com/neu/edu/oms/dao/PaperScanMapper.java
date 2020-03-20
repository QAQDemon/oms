package com.neu.edu.oms.dao;

import com.neu.edu.oms.entity.PaperScan;

public interface PaperScanMapper {
    int deleteByPrimaryKey(Integer paperScanId);

    int insert(PaperScan record);

    int insertSelective(PaperScan record);

    PaperScan selectByPrimaryKey(Integer paperScanId);

    int updateByPrimaryKeySelective(PaperScan record);

    int updateByPrimaryKey(PaperScan record);
}