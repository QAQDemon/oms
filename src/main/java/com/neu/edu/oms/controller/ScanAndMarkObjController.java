package com.neu.edu.oms.controller;

import com.neu.edu.oms.service.ScanAndMarkObjService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.annotation.Resource;
import java.util.List;

/*
 * @Description
 * @author demon
 * @version v1.0
 **/
@Api(tags={"试卷扫描"})
@RestController
public class ScanAndMarkObjController {

    @Resource
    ScanAndMarkObjService scanAndMarkObjService;

    /*
     * @Description 接受上传的excel文件并保存到本地
     * @Param [scanImg 扫描图片包括原图和分割图 直接保存, objChoice json格式 {"学号_科目号_试卷号":["1000","10"]},sign 0正常流程 1异常处理]
     * @return java.lang.String
     **/
    @ApiOperation(value = "上传excel", notes = " ")
    @RequestMapping(value={"/scanAndMarkObj/uploadScan/{sign}"},method = RequestMethod.POST)
    public String uploadScan(@ApiParam(required = true, name = "scanImgArray", value = "文件") @RequestParam(value = "scanImgArray") MultipartFile[] scanImgArray,
                             @ApiParam(required = true, name = "objChoiceJson", value = "{\"20160000_1_1\":[\"1000\",\"1010\"],\"20160001_1_1\":[\"1000\",\"1001\"]}")
                             @RequestParam(value = "objChoiceJson") String objChoiceJson, @ApiParam(required = true, name = "sign", value = "标志位") @PathVariable int sign){
        //保存文件
        List<String> errorList=scanAndMarkObjService.saveScanImg(scanImgArray);
        //保存到数据库
        List<String> errorList1 = scanAndMarkObjService.setScanAndObj(objChoiceJson,sign);
        if (0==errorList.size() &&0==errorList1.size()) {//保存无错误
            return "{msg:1}";
        }
        return "{msg:0}";
    }
}
