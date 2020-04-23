package com.neu.edu.oms.service.impl;

import com.neu.edu.oms.dao.AnswerMapper;
import com.neu.edu.oms.dao.ClassMapper;
import com.neu.edu.oms.dao.PaperScanMapper;
import com.neu.edu.oms.dao.StudentMapper;
import com.neu.edu.oms.entity.Answer;
import com.neu.edu.oms.entity.Class;
import com.neu.edu.oms.service.TeacherAnalysisService;
import io.swagger.models.auth.In;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TeacherAnalysisServiceImpl implements TeacherAnalysisService {

    @Resource
    ClassMapper classMapper;

    @Resource
    PaperScanMapper paperScanMapper;

    @Resource
    StudentMapper studentMapper;

    @Resource
    AnswerMapper answerMapper;


    public List<Class> getClassAndAnswerByTeacherId(Integer teacherId){
        List<Class> classList = classMapper.getClassListByTeacerId(1);
        Map<String, Object> studentIdMap = new HashMap<String, Object>();
        for(Class class1:classList){
            List<Integer> studentIdList = studentMapper.getStudentIdListByclassId(class1.getClassId());
            studentIdMap.put("studentIdList", studentIdList);
            studentIdMap.put("subjectId", class1.getSubjectId());
            //此处调用的方法最好加上一个判断条件是已经批改完的
            List<Integer> answerIdList = paperScanMapper.getanswerIdListBystudentIdAndsubjectId(studentIdMap);
            studentIdMap.clear();
            class1.setAnswers(answerMapper.getAnswerListByanswerIdList(answerIdList));
        }
        return classList;
    }


}
