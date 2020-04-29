package com.neu.edu.oms.service;

import com.neu.edu.oms.entity.Class;
import com.neu.edu.oms.entity.PaperScanFull;

import java.util.List;

public interface TeacherAnalysisService {
    //根据教师id选取其教授的班级以及相关考试
    List<Class> getClassAndAnswerByTeacherId(Integer teacherId);
    //选取相应班级所有学生某一次考试的成绩
    List<PaperScanFull> getAllpapers(Integer classId, Integer answerId);
}
