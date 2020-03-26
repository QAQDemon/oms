package com.neu.edu.oms.controller;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.neu.edu.oms.service.MakeTemplateService;
import com.neu.edu.oms.utils.JsonUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.dom4j.DocumentException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.annotation.Resource;
import java.lang.reflect.Type;
import java.util.Map;

@Api(tags={"模板制作"})
@RestController
public class MakeTemplateController {
    @Resource
    MakeTemplateService makeTemplateService;

    private final static String PAPER_TEMPLATE_PATH="src/main/resources/paperTemplate.xml";
    private final static String EXCEL_FILE_PATH="D://";
    private static int updateFlag=0;//0表示无人在更新 1表示有人正在更新

    /*
     * @Description 接受上传的excel文件并保存到本地//TODO json待定 重复文件的问题，删除没写
     * @Param [excelFiles AB卷两个文件, answerSheet json格式]
     * @return java.lang.String
     **/
    @ApiOperation(value = "上传excel", notes = "")
    @RequestMapping(value={"/makeTemplate/uploadExcel"},method = RequestMethod.POST)
    public String uploadExcel(@ApiParam(required = true,name="excelFiles",value="文件") @RequestParam(value = "excelFiles") MultipartFile[] excelFiles,
                              @ApiParam(required = true,name="answerSheet",value="{\"administratorId\":1,\"answerSheetName\":\"sj\",\"subjNum\":4,\"objNum\":2}")@RequestParam(value = "answerSheet") String answerSheet){
        //增加到数据库
        int answerSheetId=makeTemplateService.setNewAnswerSheet(JsonUtils.parseToAnswerSheet(answerSheet));
        if(answerSheetId==0)//失败
            return "{msg:0}";
        //保存到本地
        makeTemplateService.saveExcelFile(excelFiles[0],EXCEL_FILE_PATH+answerSheet+"_A.xlsx");//地址 D://1_A.xlsx
        makeTemplateService.saveExcelFile(excelFiles[1],EXCEL_FILE_PATH+answerSheet+"_B.xlsx");
        return "{msg:1}";
    }

    /*
     * @Description 获得答题卡模板内容
     * @Param []
     * @return java.lang.String 空字符串为异常
     **/
    @ApiOperation(value = "获得答题卡模板", notes = "")
    @RequestMapping(value={"/makeTemplate/getPaperTemplate"},method = RequestMethod.POST)
    public String getPaperTemplate(){
        String res;
        try{
            res=makeTemplateService.getXMLEncode(PAPER_TEMPLATE_PATH);
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
    @ApiOperation(value = "能否更新答题卡模板", notes = "")
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
    @ApiOperation(value = "更新答题卡模板", notes = "")
    @RequestMapping(value={"/makeTemplate/updatePaperTemplate"},method = RequestMethod.POST)
    public String updatePaperTemplate(@ApiParam(required = true,name="content",value="字符串")@RequestParam(value = "content") String content){
        makeTemplateService.saveXML(PAPER_TEMPLATE_PATH,content);
        updateFlag=0;//更新结束 置0
        return "{msg:1}";
    }
}
