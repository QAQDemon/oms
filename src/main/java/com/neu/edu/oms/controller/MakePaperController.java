package com.neu.edu.oms.controller;

import com.neu.edu.oms.entity.Answer;
import com.neu.edu.oms.entity.Subject;
import com.neu.edu.oms.service.MakePaperService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

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

    /*
     * @Description 显示选中科目的试卷
     * @Param [subjectId]
     * @return java.util.List<com.neu.edu.oms.entity.Answer>
     **/
    @ApiOperation(value = "显示试卷", notes = "")
    @RequestMapping(value={"/makePaper/getAnswer"},method = RequestMethod.POST)
    public List<Answer> getAnswer(@ApiParam(required = true,name="subjectId",value="1")@RequestParam(value = "subjectId") String subjectId){
        return makePaperService.getAnswerListBySubjectId(Integer.parseInt(subjectId));
    }

    /*
     * @Description 修改试卷信息，可修改属性：名字，开考时间，考试时间
     * @Param [answer]
     * @return java.lang.String
     **/
    @ApiOperation(value = "修改试卷信息", notes = "")
    @RequestMapping(value={"/makePaper/updateAnswer"},method = RequestMethod.POST)
    public String updateAnswer(@ApiParam(required = true,name="answer",value="{ \"answerId\": 1, \"answersheetId\": 1, \"administratorId\": 1, \"subjectId\": 1, \"answerName\": \"2020告书\", \"establishTime\": 1585999953000, \"startTime\": 1585999957000, \"examTime\": 120, \"objNum\": 30, \"subjNum\": 5, \"isAssign\": 0, \"isDeleted\": 0, \"reserve2\": null }")
                                   @RequestBody Answer answer){
        makePaperService.updateAnswer(answer);
        return "1";
    }

    /*
     * @Description 删除试卷并修改模板表
     * @Param [answerId]
     * @return java.lang.String
     **/
    @ApiOperation(value = "删除试卷", notes = "")
    @RequestMapping(value={"/makePaper/deleteAnswer"},method = RequestMethod.POST)
    public String deleteAnswer(@ApiParam(required = true,name="answerId",value="1") @RequestParam(value = "answerId") String answerId){
        makePaperService.deleteAnswer(Integer.parseInt(answerId));
        return "1";
    }

    /*
     * @Description 插入试卷并修改模板表
     * @Param [answer]
     * @return java.lang.String
     **/
    @ApiOperation(value = "插入试卷", notes = "")
    @RequestMapping(value={"/makePaper/insertAnswer"},method = RequestMethod.POST)
    public String insertAnswer(@ApiParam(required = true,name="answer",value="{ \"answersheetId\": 1, \"administratorId\": 1, \"subjectId\": 1, \"answerName\": \"2020告书\",  \"startTime\": 1585999957000, \"examTime\": 120, \"objNum\": 30, \"subjNum\": 5}")
                                   @RequestBody Answer answer){
        makePaperService.insertAnswer(answer);
        return "1";
    }
}
