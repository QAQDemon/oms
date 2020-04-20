package com.neu.edu.oms.controller;

import com.neu.edu.oms.entity.Goal;
import com.neu.edu.oms.entity.ObjAnswer;
import com.neu.edu.oms.entity.Point;
import com.neu.edu.oms.entity.SubjAnswer;
import com.neu.edu.oms.service.SetAnswerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.annotation.Resource;
import java.util.List;

/*
 * @Description
 * @author demon
 * @version v1.0
 **/
@Api(tags={"答案设置"})
@RestController
public class SetAnswerController {

    @Resource
    SetAnswerService setAnswerService;

    /*
     * @Description 获得选定试卷的客观题答案，可能为[]
     * @Param [answerId]
     * @return java.util.List<com.neu.edu.oms.entity.ObjAnswer>
     **/
    @ApiOperation(value = "获得已设置客观题答案", notes = " " )
    @RequestMapping(value={"/setAnswer/getObjAnswerList/{answerId}"},method = RequestMethod.POST)
    public List<ObjAnswer> getObjAnswerList(@ApiParam(required = true,name="answerId",value="1") @PathVariable int answerId){
        return setAnswerService.getObjAnswerByAnswerId(answerId);
    }

    /*
     * @Description 设置客观题答案，有修改或新增的才传递
     * @Param [objAnswers 有objAnswerId就更新，无则插入，更新有效属性为point_id,goal_id,score,answer_num]
     * @return java.lang.String
     **/
    @ApiOperation(value = "设置客观题答案", notes = " ")
    @RequestMapping(value={"/setAnswer/setObjAnswerList"},method = RequestMethod.POST)
    public String setObjAnswerList(@ApiParam(required = true,name="objAnswers",value="[{\"objAnswerId\":1,\"answerId\":1,\"pointId\":1,\"goalId\":1,\"questionNum\":1,\"score\":66,\"answerNum\":0},{\"answerId\":1,\"pointId\":1,\"goalId\":1,\"questionNum\":3,\"score\":5,\"answerNum\":2}]")
                                       @RequestBody ObjAnswer[] objAnswers){
        setAnswerService.setObjAnswer(objAnswers);
        return "1";
    }

    /*
     * @Description 获得选定试卷的主观题答案，可能为[]
     * @Param [answerId]
     * @return java.util.List<com.neu.edu.oms.entity.SubjAnswer>
     **/
    @ApiOperation(value = "获得已设置主观题答案", notes = " ")
    @RequestMapping(value={"/setAnswer/getSubjAnswerList/{answerId}"},method = RequestMethod.POST)
    public List<SubjAnswer> getSubjAnswerList(@ApiParam(required = true,name="answerId",value="1") @PathVariable int answerId){
        return setAnswerService.getSubjAnswerByAnswerId(answerId);
    }

    /*
     * @Description 设置主观题，有修改或新增的才传递，图片地址在service设置
     * @Param [imgFiles 图片名为题号.jpg, subjAnswers 有subjAnswerId就更新，无则插入，更新有效属性为point_id,goal_id,score,point_description,point_photo；如果无内容更新但有图片更新则为“answerId”]
     * @return java.lang.String
     **/
    @ApiOperation(value = "设置主观题答案", notes = " ")
    @RequestMapping(value={"/setAnswer/setSubjAnswerList"},method = RequestMethod.POST)
    public String setSubjAnswerList(@ApiParam(required = true,name="imgFiles",value="得分点图片") @RequestParam(value = "imgFiles") MultipartFile[] imgFiles,
                                              @ApiParam(required = true,name="subjAnswers",value="[{\"subjAnswerId\":1,\"answerId\":1,\"pointId\":1,\"goalId\":1,\"questionNum\":1,\"score\":11,\"pointDescription\":\"as\",\"pointPhoto\":\"as\"},{\"answerId\":1,\"pointId\":1,\"goalId\":1,\"questionNum\":6,\"score\":11,\"pointDescription\":\"as\"}]")
                                              @RequestParam(value = "subjAnswers") String subjAnswers){
        setAnswerService.setSubjAnswerAndSaveImgFiles(subjAnswers,imgFiles);
        return "";
    }

    /*
     * @Description 获得选定科目的所有知识点
     * @Param [subjectId]
     * @return java.util.List<com.neu.edu.oms.entity.Point>
     **/
    @ApiOperation(value = "获得所有知识点", notes = " ")
    @RequestMapping(value={"/setAnswer/getPoint/{subjectId}"},method = RequestMethod.POST)
    public List<Point> getPoint(@ApiParam(required = true,name="subjectId",value="1") @PathVariable int subjectId){
        return setAnswerService.getPointBySubjectId(subjectId);
    }

    /*
     * @Description 获得选定科目的所有考题目标
     * @Param [subjectId]
     * @return java.util.List<com.neu.edu.oms.entity.Goal>
     **/
    @ApiOperation(value = "获得所有考题目标", notes = " ")
    @RequestMapping(value={"/setAnswer/getGoal/{subjectId}"},method = RequestMethod.POST)
    public List<Goal> getGoal(@ApiParam(required = true,name="subjectId",value="1") @PathVariable int subjectId){
        return setAnswerService.getGoalBySubjectId(subjectId);
    }

    /*
     * @Description 获得选定得分点图片
     * @Param [imgFileNamePrefix "answerId_questionNum" ]
     * @return byte[] null为失败
     **/
    @ApiOperation(value = "获得选定得分点图片", notes = " ")
    @RequestMapping(value={"/setAnswer/getPointImg/{imgFileNamePrefix}"},method = RequestMethod.POST,produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] getPointImg(@PathVariable String imgFileNamePrefix){
        return setAnswerService.getPointImgByName(imgFileNamePrefix);
    }
}
