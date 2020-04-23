package com.neu.edu.oms.service.impl;

import com.neu.edu.oms.dao.*;
import com.neu.edu.oms.entity.*;
import com.neu.edu.oms.service.DataInsertService;
import com.neu.edu.oms.utils.RandomValueUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.websocket.PongMessage;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;


@Service
public class DataInsertServiceImpl implements DataInsertService {

    RandomValueUtil random = new RandomValueUtil();

    @Resource
    StudentMapper studentMapper;

    @Resource
    AnswerMapper answerMapper;

    @Resource
    PaperScanMapper paperScanMapper;

    @Resource
    SubjectMapper subjectMapper;

    @Resource
    GoalMapper goalMapper;

    @Resource
    PointMapper pointMapper;

    @Resource
    ObjMarkMapper objMarkMapper;

    @Resource
    SubjMarkMapper subjMarkMapper;

    @Override
    public int StudentInsert(int num) {
        List<Student> students = CreateStudentList(num);
        for(Student student:students){
            studentMapper.insert(student);
        }
        return 1;
    }

    @Override
    public int PaperScanInsert(){
        List<PaperScan> paperScans = CreatePaperScanList();
        for(PaperScan paperScan:paperScans){
            paperScanMapper.insert(paperScan);
        }
        return 1;
    }

    @Override
    public int GoalAndPointInsert(){
        List<Subject> subjects = subjectMapper.selectAll();
        Goal goal;
        Point point;
        for(Subject subject:subjects){
            for(int i=0; i<6; i++){
                goal = new Goal(null, subject.getSubjectId(), subject.getSubjectName(), subject.getSubjectName()+"目标"+(i+1), null, null);
                point = new Point(null, subject.getSubjectId(), subject.getSubjectName(), subject.getSubjectName()+"得分点"+(i+1), null, null);
                goalMapper.insert(goal);
                pointMapper.insert(point);
            }
        }
        return 1;
    }

    @Override
    public int ObjMarkInsert(){
        List<PaperScan> paperScans = paperScanMapper.getAllPaperScan();
        for (PaperScan paperScan:paperScans){
            Answer answer = answerMapper.selectByPrimaryKey(paperScan.getAnswerId());
            int num = answer.getObjNum();
            int subid = answer.getSubjectId();
            for(int i=0; i<num; i++){
                ObjMark objMark = new ObjMark();
                objMark.setPaperSacnId(paperScan.getPaperScanId());
                objMark.setScore((short)5);
                Byte flag = (byte)(Math.random()+0.74);
                objMark.setIsRight(flag);
                if(flag == 1){
                    objMark.setScoreGet((short)5);
                }else {
                    objMark.setScoreGet((short)0);
                }
                objMark.setGoalId((int)(Math.random()*6+(subid-1)*6+1));
                objMark.setPointId((int)(Math.random()*6+(subid-1)*6+1));
                objMark.setQuestionNum((short)(i+1));
                objMarkMapper.insert(objMark);
            }
        }
        return 1;
    }


    /*
     * @Author zongyinxiao
     * @Date 2020/4/5 1:01
     * @Description 创建学生链表
     * @Param [num 创建数量]
     * @Return java.util.List<com.neu.edu.oms.entity.Student>
     * @Since version-1.0
     **/
    private List<Student> CreateStudentList(int num){
        List<Student> students= new ArrayList<Student>(num);
        List<Integer> idlist = CreateIdList(num,1);
        List<String> namelist = CreateNameList(num);
        List<String> phonelist = CreatePhoneList(num);
        List<String> emaillist = CreateEmailList(num);
        for(int i=0; i<num; i++){
            Student student = new Student();
            student.setStudentId(idlist.get(i));
            student.setClassId((int)(Math.random()+4));
            student.setStudentName(namelist.get(i));
            student.setPhonenum(phonelist.get(i));
            student.setEmail(emaillist.get(i));
            student.setPassword("123456");
            students.add(student);
        }
        return students;
    }

    /*
     * @Author zongyinxiao
     * @Date 2020/4/5 1:02
     * @Description 随机生成id,范围是20160000-20170000
     * @Param [num创建数量]
     * @Return java.util.List<java.lang.Integer>
     * @Since version-1.0
     **/
    private List<Integer> CreateIdList(int num, int flag){
        List<Integer> idlist = new ArrayList<Integer>();
        if (flag == 1){
            idlist = CreateIdByRandom(num);
            return idlist;
        }else if (flag == 2){
            idlist = CreateIdBynextInt(num);
            return idlist;
        }else {
            return null;
        }
    }

    private List<Integer> CreateIdByRandom(int num){
        HashSet<Integer> idset = new HashSet(num);
        List<Integer> idlist = new ArrayList<Integer>(num);
        while(idset.size() < num){
            idset.add((int)(Math.random()*10000+20160000));
        }
        idlist.addAll(idset);
        return idlist;
    }

    private List<Integer> CreateIdBynextInt(int num){
        List<Integer> idlist = new ArrayList(num);
        HashSet<Integer> idset = new HashSet(num);
        Random random = new Random(1);
        while(idset.size() < num){
            idset.add(random.nextInt(10000)+20160000);
        }
        idlist.addAll(idset);
        return idlist;
    }


    private List<String> CreateNameList(int num){
        List<String> namelist = new ArrayList<String>(num);
        for(int i=0; i<num; i++){
            namelist.add(random.getChineseName());
        }
        return namelist;
    }


    private List<String> CreatePhoneList(int num){
        List<String> phonelist = new ArrayList<String>(num);
        for(int i=0; i<num; i++){
            phonelist.add(random.getTelephone());
        }
        return phonelist;
    }

    private List<String> CreateEmailList(int num){
        List<String> emaillist = new ArrayList<String>(num);
        for(int i=0; i<num; i++){
            emaillist.add(random.getEmail(13,20));
        }
        return emaillist;
    }



    //创建扫描试卷
    private List<PaperScan> CreatePaperScanList(){
        List<Student> students = studentMapper.getAllStudent();
        List<Answer> answers = answerMapper.getAllAnswer();
        List<PaperScan> paperScans = new ArrayList<>();
        for(Student student:students){
            for(Answer answer:answers){
                PaperScan paperScan = new PaperScan(null, student.getStudentId(), answer.getAnswerId(), answer.getAnswerName(),
                        null, null, answer.getSubjectId(), null, null, (byte) 1,(byte) 1,null,null,null,null,null,null,null,null );
                paperScans.add(paperScan);
            }
        }
        return paperScans;
    }

    public static void main(String[] args){
        new DataInsertServiceImpl().StudentInsert(100);
    }
}
