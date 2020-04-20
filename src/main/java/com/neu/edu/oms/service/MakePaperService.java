package com.neu.edu.oms.service;

import com.neu.edu.oms.entity.Answer;
import com.neu.edu.oms.entity.Subject;
import java.util.List;

/*
 * @Description
 * @author demon
 * @version v1.0
 **/
public interface MakePaperService {
    List<Subject> getSubjectList();
    List<Answer> getAnswerListBySubjectId(int subjectId);
    Boolean updateAnswer(Answer answer);
    Boolean deleteAnswer(int answerId);
    Boolean insertAnswer(Answer answer);
}
