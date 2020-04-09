package com.neu.edu.oms.service;

import java.util.List;

public interface TeacherAnalysisService {
    //根据教师id选取其教授的班级以及相关考试
    List<Class> getClassAndAnswerByTeacherId(Integer teacherId);
}
