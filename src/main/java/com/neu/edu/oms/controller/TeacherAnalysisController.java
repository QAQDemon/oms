package com.neu.edu.oms.controller;

import com.neu.edu.oms.service.DataInsertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TeacherAnalysisController {

    @Autowired
    DataInsertService dataInsertService;

    @RequestMapping("/insert")
    public String insert(){
        dataInsertService.StudentInsert(50);
        return "Teacher";
    }
}
