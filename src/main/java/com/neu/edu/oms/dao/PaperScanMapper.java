package com.neu.edu.oms.dao;

import com.neu.edu.oms.entity.Answer;
import com.neu.edu.oms.entity.PaperScan;
import com.neu.edu.oms.entity.PaperScanFull;
import io.swagger.models.auth.In;
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
//通过学生id列表和科目选取其考试的答案id列表，  此处最好加一个条件是已经批改完的
    List<Integer> getanswerIdListBystudentIdAndsubjectId(Map<String, Object> studentIdMap);
    //选取出所有的扫描试卷
    List<PaperScan> getAllPaperScan();
    //通过学生id和试卷id选出相应的扫描试卷,此处填写paperscanfull尝试一下
    PaperScan getPaperScanBystudentIdAndanswerId(Integer studentId, Integer answerId);
}