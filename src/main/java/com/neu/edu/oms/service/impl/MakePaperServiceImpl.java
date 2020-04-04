package com.neu.edu.oms.service.impl;

import com.neu.edu.oms.dao.AnswerMapper;
import com.neu.edu.oms.dao.AnswerSheetMapper;
import com.neu.edu.oms.dao.PaperScanMapper;
import com.neu.edu.oms.dao.SubjectMapper;
import com.neu.edu.oms.entity.Answer;
import com.neu.edu.oms.entity.AnswerSheet;
import com.neu.edu.oms.entity.Subject;
import com.neu.edu.oms.service.MakePaperService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.Date;
import java.util.List;


@Service
public class MakePaperServiceImpl implements MakePaperService {

    @Resource
    SubjectMapper subjectMapper;
    @Resource
    AnswerMapper answerMapper;
    @Resource
    PaperScanMapper paperScanMapper;
    @Resource
    AnswerSheetMapper answerSheetMapper;

    /*
     * @Description 获得科目
     * @Param []
     * @return java.util.List<com.neu.edu.oms.entity.Subject>
     **/
    public List<Subject> getSubjectList() {
        return subjectMapper.selectAll();
    }

    /*
     * @Description 获得选中科目的试卷，未删除的
     * @Param [subjectId]
     * @return java.util.List<com.neu.edu.oms.entity.Answer>
     **/
    public List<Answer> getAnswerListBySubjectId(int subjectId){
        return answerMapper.selectBySubjectId(subjectId);
    }

    /*
     * @Description 更新答案表
     * @Param [answer]
     * @return java.lang.Boolean true成功
     **/
    public Boolean updateAnswer(Answer answer) {
        return 1==answerMapper.updateByPrimaryKeySelective(answer);
    }

    /*
     * @Description 删除试卷并修改模板表
     * @Param [answerId]
     * @return java.lang.Boolean
     **/
    public Boolean deleteAnswer(int answerId) {
        //判断是否扫描过
        List<Answer> list = paperScanMapper.selectByAnswerId(answerId);
        if (0 != list.size()) {
            return false;
        }
        //判断是否分配
        Answer answer=answerMapper.selectByPrimaryKey(answerId);
        if (1 == answer.getIsAssign()) {
            return false;
        }
        if(1==answer.getIsDeleted())//已删除
            return true;
        //删除数据库
        answer.setIsDeleted((short)1);
        answerMapper.updateByPrimaryKeySelective(answer);
        //修改answersheet表的使用次数
        AnswerSheet answerSheet=answerSheetMapper.selectByPrimaryKey(answer.getAnswersheetId());
        answerSheet.setAdoptNum(answerSheet.getAdoptNum()-1);
        answerSheetMapper.updateByPrimaryKeySelective(answerSheet);
        return true;
    }

    /*
     * @Description 插入试卷并修改模板表
     * @Param [answer]
     * @return java.lang.Boolean
     **/
    public Boolean insertAnswer(Answer answer){
        answer.setEstablishTime(new Date());
        if(0==answerMapper.insertSelective(answer)){//插入失败
            return false;
        }
        //修改模板表
        AnswerSheet answerSheet=answerSheetMapper.selectByPrimaryKey(answer.getAnswersheetId());
        answerSheet.setAdoptNum(answerSheet.getAdoptNum()+1);
        answerSheetMapper.updateByPrimaryKeySelective(answerSheet);
        return true;
    }
}
