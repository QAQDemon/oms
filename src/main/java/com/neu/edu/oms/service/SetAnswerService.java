package com.neu.edu.oms.service;

import com.neu.edu.oms.entity.Goal;
import com.neu.edu.oms.entity.ObjAnswer;
import com.neu.edu.oms.entity.Point;
import com.neu.edu.oms.entity.SubjAnswer;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

/*
 * @Description
 * @author demon
 * @version v1.0
 **/
public interface SetAnswerService {
    List<ObjAnswer> getObjAnswerByAnswerId(int answerId);
    Boolean setObjAnswer(ObjAnswer[] objAnswers);
    Boolean setSubjAnswerAndSaveImgFiles(String subjAnswersJson,MultipartFile[] imgFiles);
    List<SubjAnswer> getSubjAnswerByAnswerId(int answerId);
    List<Point> getPointBySubjectId(int subjectId);
    List<Goal> getGoalBySubjectId(int subjectId);
    byte[] getPointImgByName(String imgFileNamePrefix);
}
