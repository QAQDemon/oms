package com.neu.edu.oms.controller;

import com.neu.edu.oms.entity.Subject;
import com.neu.edu.oms.service.MakePaperService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@Api(tags={"试卷制作"})
@RestController
public class MakePaperController {

    @Resource
    MakePaperService makePaperService;

    /*
     * @Description 显示所有科目
     * @Param []
     * @return java.util.List<com.neu.edu.oms.entity.Subject>
     **/
    @ApiOperation(value = "显示科目", notes = "")
    @RequestMapping(value={"/makePaper/getSubject"},method = RequestMethod.POST)
    public List<Subject> getSubject(){
        return makePaperService.getSubjectList();
    }
}
