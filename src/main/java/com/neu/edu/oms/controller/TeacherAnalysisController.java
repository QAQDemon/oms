package com.neu.edu.oms.controller;

import com.google.gson.Gson;
import com.neu.edu.oms.entity.Class;
import com.neu.edu.oms.entity.PaperScanFull;
import com.neu.edu.oms.service.DataInsertService;
import com.neu.edu.oms.service.TeacherAnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/Teacher")
public class TeacherAnalysisController {

    @Autowired
    private DataInsertService dataInsertService;

    @Autowired
    private TeacherAnalysisService teacherAnalysisService;

    @RequestMapping("/insertstudent")
    public String insertstudent(){
        dataInsertService.StudentInsert(26);
        return "Teacher";
    }

    @RequestMapping("/insertpaperscan")
    public String insertinsertpaperscan(){
        dataInsertService.PaperScanInsert();
        return "Teacher";
    }

    @RequestMapping("/insertgoalandpoint")
    public String insertgoalandpoint(){
        dataInsertService.GoalAndPointInsert();
        return "Teacher";
    }

    @RequestMapping("/insertobj")
    public String insertobj(){
        dataInsertService.ObjMarkInsert();
        return "Teacher";
    }

    @RequestMapping("/insertsubj")
    public String insertsubj(){
        dataInsertService.SubjMarkInsert();
        dataInsertService.SubjMarkInsert();
        return "Teacher";
    }

    @RequestMapping("/test")
    public String test(Model model){
        List<Class> classList =  teacherAnalysisService.getClassAndAnswerByTeacherId(1);
        model.addAttribute("classes", classList);
        return "Teacher";
    }

    @RequestMapping("/getscore")
    @ResponseBody
    public String getscoretest1(Integer classId, Integer answerId){
        List<PaperScanFull> paperScanFullList = teacherAnalysisService.getAllpapers(classId, answerId);
        Gson gson = new Gson();
        String paperScanListjson = gson.toJson(paperScanFullList);
        System.out.println(paperScanListjson);
        return paperScanListjson;
    }

    @ResponseBody
    @RequestMapping("/ceshi")
    public String requestbody(){
        int a=0,b=0;
        for(int i=0; i<200; i++){
            Byte flag = (byte)(Math.random()+0.65);
            if(flag == 1){
                a++;
            }else{
                b++;
            }
            System.out.println(flag);
        }
        System.out.println(a+"------------"+b);
        return "Teacher";
    }
}
