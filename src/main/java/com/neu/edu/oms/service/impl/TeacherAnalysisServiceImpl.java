package com.neu.edu.oms.service.impl;

import com.neu.edu.oms.dao.*;
import com.neu.edu.oms.entity.*;
import com.neu.edu.oms.entity.Class;
import com.neu.edu.oms.service.TeacherAnalysisService;
import io.swagger.models.auth.In;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service
public class TeacherAnalysisServiceImpl implements TeacherAnalysisService {

    @Resource
    private ClassMapper classMapper;

    @Resource
    private PaperScanMapper paperScanMapper;

    @Resource
    private StudentMapper studentMapper;

    @Resource
    private AnswerMapper answerMapper;

    @Resource
    private SubjMarkMapper subjMarkMapper;

    @Resource
    private SubjScoreMapper subjScoreMapper;

    @Resource
    private ObjMarkMapper objMarkMapper;


    public List<Class> getClassAndAnswerByTeacherId(Integer teacherId){
        List<Class> classList = classMapper.getClassListByTeacerId(teacherId);
        Map<String, Object> studentIdMap = new HashMap<String, Object>();
        for(Class class1:classList){
            List<Integer> studentIdList = studentMapper.getStudentIdListByclassId(class1.getClassId());
            studentIdMap.put("studentIdList", studentIdList);
            studentIdMap.put("subjectId", class1.getSubjectId());
            List<Integer> answerIdList = paperScanMapper.getanswerIdListBystudentIdAndsubjectId(studentIdMap);
            studentIdMap.clear();
            if(answerIdList.size() == 0){
                class1.setAnswers(null);
            }else {
                class1.setAnswers(answerMapper.getAnswerListByanswerIdList(answerIdList));
            }
        }
        return classList;
    }

    public List<PaperScanFull> getAllpapers(Integer classId, Integer answerId){
        List<Student> studentList =  studentMapper.getStudentListByclassId(classId);
        List<PaperScanFull> paperScanFullList = new ArrayList<>();
        Answer answer = answerMapper.selectByPrimaryKey(answerId);
        for(Student student:studentList){
//            System.out.println(student.getStudentId());
            //paperScan选出来有可能是空
            PaperScan paperScan = paperScanMapper.getPaperScanBystudentIdAndanswerId(student.getStudentId(), answerId);
            if(paperScan == null){
                PaperScanFull paperScanFull = new PaperScanFull();
                paperScanFull.setStudentId(student.getStudentId());
                paperScanFull.setAnswerId(answerId);
                paperScanFull.setStudentName(student.getStudentName());
                paperScanFull.setAnswerName(answer.getAnswerName());
                paperScanFull.setPaperScanId(0);
                paperScanFull.setObjGet(0);
                paperScanFull.setObjAll(0);
                paperScanFull.setSubjGet(0);
                paperScanFull.setSubjAll(0);
                paperScanFull.setScoreGet(0);
                paperScanFull.setScore(0);
                paperScanFull.setObjnum(answer.getObjNum());
                paperScanFull.setSubjnum(answer.getSubjNum());
                paperScanFullList.add(paperScanFull);
//                System.out.println(paperScanFull.getStudentName());
            }else {
                PaperScanFull paperScanFull = new PaperScanFull(paperScan);
                paperScanFull.setStudentName(student.getStudentName());
                paperScanFull.setAnswerName(answer.getAnswerName());
                paperScanFull.setObjMarks(objMarkMapper.getObjMarkListBypaperScanId(paperScanFull.getPaperScanId()));
                paperScanFull.setSubjScores(getSubScoreList(paperScanFull.getPaperScanId()));
                paperScanFull = calculatescore(paperScanFull);
                paperScanFull.setObjnum(answer.getObjNum());
                paperScanFull.setSubjnum(answer.getSubjNum());
                paperScanFullList.add(paperScanFull);
            }
//            System.out.println(student.getStudentName());
        }
        paperScanFullList.sort(new Comparator<PaperScanFull>() {
            @Override
            public int compare(PaperScanFull o1, PaperScanFull o2) {//此处填的函数是需要交换时的场景
                return o2.getScoreGet()-o1.getScoreGet();
            }
        });
//        for(PaperScanFull paperScanFull:paperScanFullList){
//            System.out.println(paperScanFull.getScoreGet());
//        }
        return paperScanFullList;
    }

    public List<PaperScanFull> getpapersByanswerId(Integer answerId){
        Answer answer = answerMapper.selectByPrimaryKey(answerId);
        List<PaperScanFull> paperScanFullList = new ArrayList<PaperScanFull>();
        List<PaperScan> paperScanList = paperScanMapper.getPaperScanListByanswerId(answerId);
        for(PaperScan paperScan:paperScanList){
            PaperScanFull paperScanFull = new PaperScanFull(paperScan);
            paperScanFull.setObjMarks(objMarkMapper.getObjMarkListBypaperScanId(paperScan.getPaperScanId()));
            paperScanFull.setSubjScores(getSubScoreList(paperScan.getPaperScanId()));
            paperScanFull = calculatescore(paperScanFull);
            paperScanFull.setObjnum(answer.getObjNum());
            paperScanFull.setSubjnum(answer.getSubjNum());
            paperScanFullList.add(paperScanFull);
        }
//        System.out.println(paperScanFullList.size());
        return paperScanFullList;
    }

//数据分析部分
//分数分析时，缺考不计入





    private List<SubjScore> getSubScoreList(Integer paperScanId){
        List<SubjScore> subjScoreList =  subjScoreMapper.getSubjScoreListBypaperScanId(paperScanId);
        if(subjScoreList.size() != 0){
//            System.out.println("空的"+subjScoreList.size());
            return subjScoreList;
        }else {
            //此处分数计算到底该怎么算后面再说
            List<SubjMark> subjMarkList = subjMarkMapper.getSubjMarkListBypaperScanId(paperScanId);
            int time=0, question_num = subjMarkList.get(0).getQuestionNum();
            for(SubjMark subjMark:subjMarkList){
//                System.out.println("yaya");
                if(subjMark.getQuestionNum() == question_num){
                    time++;
                }else {
                    break;
                }
//                System.out.println("haha"+time);
            }
            int sum = 0,scoreget;
            for(int i=0; i<subjMarkList.size(); i++){
                if((i+1)%time == 0){
                    sum += subjMarkList.get(i).getScoreGet();
                    //此处分数没有使用float造成的偏差问题
                    if(sum/time >=9){
                        scoreget = 10;
                    }else if(sum/time <= 1){
                        scoreget = 0;
                    }else {
                        scoreget = sum/time;
                    }
                    SubjScore subjScore = new SubjScore(null,subjMarkList.get(i).getPaperScanId(), (short) (scoreget), subjMarkList.get(i).getScore(), subjMarkList.get(i).getQuestionNum(), subjMarkList.get(i).getPointId(), subjMarkList.get(i).getGoalId(), new Date(), (byte)time, subjMarkList.get(i).getPhotoAddress(),null,null);
                    subjScoreMapper.insert(subjScore);
                    sum = 0;
                }else {
                    sum += subjMarkList.get(i).getScoreGet();
                }
            }
            subjScoreList = subjScoreMapper.getSubjScoreListBypaperScanId(paperScanId);
            return subjScoreList;
        }
    }

    private PaperScanFull calculatescore(PaperScanFull paperScanFull){
        int objscoreall=0, objscoreget=0;
        int subjscoreall=0, subjscoreget=0;
        for(ObjMark objMark:paperScanFull.getObjMarks()){
            objscoreget += objMark.getScoreGet();
            objscoreall += objMark.getScore();
        }
        for(SubjScore subjScore:paperScanFull.getSubjScores()){
            subjscoreget += subjScore.getScoreGet();
            subjscoreall += subjScore.getScore();
        }
        paperScanFull.setObjGet(objscoreget);
        paperScanFull.setObjAll(objscoreall);
        paperScanFull.setSubjGet(subjscoreget);
        paperScanFull.setSubjAll(subjscoreall);
        paperScanFull.setScoreGet(objscoreget+subjscoreget);
        paperScanFull.setScore(objscoreall+subjscoreall);
        return paperScanFull;
    }
}
