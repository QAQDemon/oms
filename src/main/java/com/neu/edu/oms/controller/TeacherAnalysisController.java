package com.neu.edu.oms.controller;

import com.neu.edu.oms.service.DataInsertService;
import com.neu.edu.oms.service.TeacherAnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TeacherAnalysisController {

    @Autowired
    DataInsertService dataInsertService;

    @Autowired
    TeacherAnalysisService teacherAnalysisService;

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

    @RequestMapping("/test")
    public String test(){
        teacherAnalysisService.getClassAndAnswerByTeacherId(1);
        return "Teacher";
    }

}
