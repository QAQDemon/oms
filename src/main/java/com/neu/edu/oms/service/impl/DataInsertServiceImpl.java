package com.neu.edu.oms.service.impl;

import com.neu.edu.oms.dao.StudentMapper;
import com.neu.edu.oms.entity.Student;
import com.neu.edu.oms.service.DataInsertService;
import com.neu.edu.oms.utils.RandomValueUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


@Service
public class DataInsertServiceImpl implements DataInsertService {

    RandomValueUtil random = new RandomValueUtil();

    @Resource
    StudentMapper studentMapper;

    @Override
    public int StudentInsert(int num) {
        List<Student> students = CreateStudentList(num);
        for(int i=0; i<num; i++){
            System.out.println(students.get(i).getStudentId()+"----"+students.get(i).getStudentName());
            studentMapper.insert(students.get(i));
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
        List<Integer> idlist = CreateIdList(num);
        List<String> namelist = CreateNameList(num);
        List<String> phonelist = CreatePhoneList(num);
        List<String> emaillist = CreateEmailList(num);
        for(int i=0; i<num; i++){
            Student student = new Student();
            student.setStudentId(idlist.get(i));
            student.setClassId((int)(Math.random()*3+1));
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
    public List<Integer> CreateIdList(int num){
        List<Integer> idlist = new ArrayList<Integer>(num);
//        Random random = new Random(1);
//        for(int i=0; i<num; i++){
//            int id = random.nextInt(10000)+20160000;
//            System.out.println(id);
//            idlist.add(id);
//        }
        for(int i=0; i<num; i++){
            int id = (int)(Math.random()*10000+20170000);
            System.out.println(id);
            idlist.add(id);
        }
        return idlist;
    }


    public List<String> CreateNameList(int num){
        List<String> namelist = new ArrayList<String>(num);
        for(int i=0; i<num; i++){
            namelist.add(random.getChineseName());
        }
        return namelist;
    }


    public List<String> CreatePhoneList(int num){
        List<String> phonelist = new ArrayList<String>(num);
        for(int i=0; i<num; i++){
            phonelist.add(random.getTelephone());
        }
        return phonelist;
    }

    public List<String> CreateEmailList(int num){
        List<String> emaillist = new ArrayList<String>(num);
        for(int i=0; i<num; i++){
            emaillist.add(random.getEmail(13,20));
        }
        return emaillist;
    }

//    private List<Integer> CreateClassId(int num){
//
//        return null;
//    }

    public static void main(String[] args){
        new DataInsertServiceImpl().StudentInsert(100);
    }
}
