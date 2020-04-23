package com.neu.edu.oms.dao;

import com.neu.edu.oms.entity.SubjMark;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SubjMarkMapper {
    int deleteByPrimaryKey(Integer subjMarkId);

    int insert(SubjMark record);

    int insertSelective(SubjMark record);

    SubjMark selectByPrimaryKey(Integer subjMarkId);

    int updateByPrimaryKeySelective(SubjMark record);

    int updateByPrimaryKey(SubjMark record);
    //根据PaperScanId选取主观题列表
    List<SubjMark> getSubjMarkListBypaperScanId(Integer paperScanId);
}