package com.neu.edu.oms.dao;

import com.neu.edu.oms.entity.ObjMark;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ObjMarkMapper {
    //根据PaperScanId删除
    int deleteByPaperScanId(Integer paperScanId);

    int deleteByPrimaryKey(Integer objMarkId);

    int insert(ObjMark record);

    int insertSelective(ObjMark record);

    ObjMark selectByPrimaryKey(Integer objMarkId);

    int updateByPrimaryKeySelective(ObjMark record);

    int updateByPrimaryKey(ObjMark record);
    //根据扫描试卷id选取相应客观题列表
    List<ObjMark> getObjMarkListBypaperScanId(Integer paperScanId);
}