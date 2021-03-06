package com.neu.edu.oms.controller;

import com.neu.edu.oms.service.DataInsertService;
import com.neu.edu.oms.service.TeacherAnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TeacherTestController {
    @Autowired
    private DataInsertService dataInsertService;

    @Autowired
    private TeacherAnalysisService teacherAnalysisService;


    @RequestMapping("/getscoretest")
    public String getscoretest(){
        teacherAnalysisService.getAllpapers(2,1);
        System.out.println("WWWWWWWWWWWWWWWWWWWWWWWWW");
        return "Teacher";
    }

    @RequestMapping("/Teacher/getscore1test")
    @ResponseBody
    public String getscoretest1(Integer classId, Integer answerId){
//        teacherAnalysisService.getAllpapers(2,1);
        System.out.println("WWWWWWWWWWWWWWWWWWWWWWWWW");
        System.out.println(classId+"WWWWWWWWWWWWWWWWWWWWWWWWW"+answerId);
        return "xiaozongyin";
    }

    @RequestMapping("/getpapers")
    @ResponseBody
    public String getpapers(){
        teacherAnalysisService.getpapersByanswerId(1);
        System.out.println("WWWWWWWWWWWWWWWWWWWWWWWWW");
        return "success";
    }

    @RequestMapping("/addscore")
    @ResponseBody
    public String addscore(){
        dataInsertService.scoreadd(3,1);
        dataInsertService.scoreadd(2,0);
        System.out.println("WWWWWWWWWWWWWWWWWWWWWWWWW");
        return "success";
    }

    @RequestMapping("/cutscore")
    @ResponseBody
    public String cutscore(){
        dataInsertService.scorecut(1,0);
        System.out.println("WWWWWWWWWWWWWWWWWWWWWWWWW");
        return "success";
    }
}
