package com.neu.edu.oms.dao;

import com.neu.edu.oms.entity.Answer;
import com.neu.edu.oms.entity.PaperScan;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface PaperScanMapper {
    //获得所有当前答案的扫描信息
    List<Answer> selectByAnswerId(Integer answerId);
    //根据answerId,subjectId,studentId获得主键
    int[] selectByThreeId(Integer answerId,Integer subjectId,Integer studentId);

    int deleteByPrimaryKey(Integer paperScanId);

    int insert(PaperScan record);

    int insertSelective(PaperScan record);

    PaperScan selectByPrimaryKey(Integer paperScanId);

    int updateByPrimaryKeySelective(PaperScan record);

    int updateByPrimaryKey(PaperScan record);
//通过学生id列表和科目选取其考试的答案id列表
    List<Integer> getanswerIdListBystudentIdAndsubjectId(Map<String, Object> studentIdMap);
}