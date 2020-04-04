package com.neu.edu.oms.dao;

import com.neu.edu.oms.entity.Answer;
import com.neu.edu.oms.entity.PaperScan;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PaperScanMapper {
    //获得所有当前答案的扫描信息
    List<Answer> selectByAnswerId(Integer answerId);

    int deleteByPrimaryKey(Integer paperScanId);

    int insert(PaperScan record);

    int insertSelective(PaperScan record);

    PaperScan selectByPrimaryKey(Integer paperScanId);

    int updateByPrimaryKeySelective(PaperScan record);

    int updateByPrimaryKey(PaperScan record);
}