package com.neu.edu.oms.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Api(tags={"模板制作"})
@RestController
public class MakeTemplateController {

    @ApiOperation(value = "上传excel", notes = "")
    @RequestMapping(value={"/makeTemplate/uploadExcel"},method = RequestMethod.POST)
    public String uploadExcel(@ApiParam(required = true,name="excelFile",value="文件") @RequestParam(value = "excelFile") MultipartFile excelFile,
                              @ApiParam(required = true,name="json",value="")@RequestParam(value = "json") String json){
        //保存到本地
        if (excelFile.isEmpty()) {
            return "false";
        }
        String fileName = excelFile.getOriginalFilename();
        File dest = new File("D://test.xlsx");
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            excelFile.transferTo(dest); // 保存文件
            return "true";
        } catch (Exception e) {
            e.printStackTrace();
            return "false";
        }

    }
}
