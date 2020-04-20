package com.neu.edu.oms.controller;

import com.neu.edu.oms.entity.AnswerSheet;
import com.neu.edu.oms.service.MakeTemplateService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.dom4j.DocumentException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.annotation.Resource;
import java.util.List;

/*
 * @Description
 * @author demon
 * @version v1.0
 **/
@Api(tags={"模板制作"})
@RestController
public class MakeTemplateController {

    @Resource
    MakeTemplateService makeTemplateService;

    private static int updateFlag=0;//0表示无人在更新 1表示有人正在更新

    /*
     * @Description 接受上传的excel文件并保存到本地
     * @Param [excelFiles AB卷两个文件, answerSheet json格式]
     * @return java.lang.String
     **/
    @ApiOperation(value = "上传excel", notes = " ")
    @RequestMapping(value={"/makeTemplate/uploadExcel"},method = RequestMethod.POST)
    public String uploadExcel(@ApiParam(required = true,name="excelFiles",value="文件") @RequestParam(value = "excelFiles") MultipartFile[] excelFiles,
                              @ApiParam(required = true,name="answerSheet",value="{\"administratorId\":1,\"answerSheetName\":\"sj\",\"subjNum\":4,\"objNum\":2}")@RequestParam(value = "answerSheet") String answerSheet){
        makeTemplateService.setNewAnswerSheetAndSaveExcelFiles(answerSheet,excelFiles);
        return "{msg:1}";
    }

    /*
     * @Description 删除选中模板，判断是否可删（未添加过答案的）
     * @Param [answerSheetId]
     * @return java.lang.String
     **/
    @ApiOperation(value = "删除答题卡模板", notes = " ")
    @RequestMapping(value={"/makeTemplate/deleteAnswerSheet/{answerSheetId}"},method = RequestMethod.POST)
    public String deleteAnswerSheet(@ApiParam(required = true,name="answerSheetId",value=" ") @PathVariable int answerSheetId){
        makeTemplateService.deleteAnswerSheet(answerSheetId);
        return "{msg:1}";
    }

    /*
     * @Description 显示所有未删除的模板
     * @Param []
     * @return java.util.List<com.neu.edu.oms.entity.AnswerSheet>
     **/
    @ApiOperation(value = "显示所有答题卡模板", notes = " ")
    @RequestMapping(value={"/makeTemplate/getAnswerSheet"},method = RequestMethod.POST)
    public List<AnswerSheet> getAnswerSheet(){
        return makeTemplateService.getAnswerSheetList();
    }

    /*
     * @Description 获得答题卡模板内容
     * @Param []
     * @return java.lang.String 空字符串为异常
     **/
    @ApiOperation(value = "获得答题卡模板", notes = " ")
    @RequestMapping(value={"/makeTemplate/getPaperTemplate"},method = RequestMethod.POST)
    public String getPaperTemplate(){
        String res;
        try{
            res=makeTemplateService.getXMLEncode();
        }catch (DocumentException e){//路径错误
            return "";
        }
        return res;
    }

    /*
     * @Description 判断能有更新xml文件，同时只能一人更新
     * @Param []
     * @return java.lang.String 1可以 0不能
     **/
    @ApiOperation(value = "能否更新答题卡模板", notes = " ")
    @RequestMapping(value={"/makeTemplate/canUpdatePaperTemplate"},method = RequestMethod.POST)
    public String canUpdatePaperTemplate(){
        if (updateFlag == 0) {
            updateFlag=1;//可以更新，置1
            return "{msg:1}";
        }
        return "{msg:0}";
    }

    /*
     * @Description 更新答题卡模板
     * @Param [content]
     * @return java.lang.String
     **/
    @ApiOperation(value = "更新答题卡模板", notes = " ")
    @RequestMapping(value={"/makeTemplate/updatePaperTemplate"},method = RequestMethod.POST)
    public String updatePaperTemplate(@ApiParam(required = true,name="content",value="字符串")@RequestParam(value = "content") String content){
        makeTemplateService.saveXML(content);
        updateFlag=0;//更新结束 置0
        return "{msg:1}";
    }

    /*
     * @Description 下载选定excel文件
     * @Param [excelFileNamePrefix]
     * @return byte[]
     **/
    @ApiOperation(value = "下载选定excel文件", notes = " ")
    @RequestMapping(value={"/makeTemplate/downloadExcel/{excelFileNamePrefix}"},method = RequestMethod.POST,produces = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
    public byte[] getPointImg(@PathVariable String excelFileNamePrefix){
        return makeTemplateService.downloadExcelByName(excelFileNamePrefix);
    }
}
