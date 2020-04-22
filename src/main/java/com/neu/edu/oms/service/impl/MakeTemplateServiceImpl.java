package com.neu.edu.oms.service.impl;

import com.neu.edu.oms.dao.AnswerSheetMapper;
import com.neu.edu.oms.entity.AnswerSheet;
import com.neu.edu.oms.service.MakeTemplateService;
import com.neu.edu.oms.utils.Base64Util;
import com.neu.edu.oms.utils.FileUtil;
import com.neu.edu.oms.utils.JsonUtils;
import com.neu.edu.oms.utils.PaperXMLReader;
import org.dom4j.DocumentException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import javax.annotation.Resource;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/*
 * @Description
 * @author demon
 * @version v1.0
 **/
@Service
public class MakeTemplateServiceImpl implements MakeTemplateService {

    @Resource
    AnswerSheetMapper answerSheetMapper;

    private final static String PAPER_TEMPLATE_PATH="src/main/resources/paperTemplate.xml";
    private final static String EXCEL_FILE_PATH="C:\\Users\\demon\\Desktop\\ResourceBackup\\excel\\";
    private final static String EXCEL_SUFFIX=".xlsx";

    /*
     * @Description 根据路径获得xml文件的内容并加密
     * @Param [path src/main/resources/paperTemplate.xml]
     * @return java.lang.String
     **/
    @Override
    public String getXMLEncode()throws DocumentException {
        return Base64Util.encodeToString(new PaperXMLReader(PAPER_TEMPLATE_PATH).readAllAsString());
    }

    /*
     * @Description 先解密再保存xml文件
     * @Param [path, content加密的xml内容]
     * @return java.lang.Boolean
     **/
    @Override
    public Boolean saveXML(String content){
        content = Base64Util.decodeToString(content);//解密
        FileWriter writer;
        try {
            writer = new FileWriter(PAPER_TEMPLATE_PATH);
            writer.write("");//清空原文件内容
            writer.write(content);
            writer.flush();
            writer.close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    /*
     * @Description 插入新的答题卡模板,并保存
     * @Param [answerSheet, excelFiles]
     * @return java.lang.Boolean
     **/
    @Override
    public Boolean setNewAnswerSheetAndSaveExcelFiles(String answerSheetJson,MultipartFile[] excelFiles) {
        AnswerSheet answerSheet=JsonUtils.parseToAnswerSheet(answerSheetJson);
        answerSheet.setEstablishTime(new Date());
        answerSheetMapper.insertSelective(answerSheet);
        int answerSheetId=answerSheet.getAnswerSheetId();
        if(0==answerSheetId)//插入失败
            return false;
        //插入成功，保存文件到本地
        Boolean b1= FileUtil.saveFile(excelFiles[0],EXCEL_FILE_PATH+answerSheetId+"_A"+EXCEL_SUFFIX);//地址 D://1_A.xlsx
        Boolean b2=FileUtil.saveFile(excelFiles[1],EXCEL_FILE_PATH+answerSheetId+"_B"+EXCEL_SUFFIX);
        return b1 && b2;//全成功才返回true
    }

    /*
     * @Description 删除选中的答题卡模板，根据采用数判断是否可删;还要删除对应AB卷Excel文件
     * @Param [answerSheetId,directory excel文件目录]
     * @return java.lang.Boolean
     **/
    @Override
    public Boolean deleteAnswerSheet(int answerSheetId){
        AnswerSheet answerSheet=answerSheetMapper.selectByPrimaryKey(answerSheetId);
        if(0!=answerSheet.getAdoptNum())//使用过则不能删除
            return false;
        if(1==answerSheet.getIsDeleted())//已删除
            return true;
        //删除数据库
        answerSheet.setIsDeleted((short)1);
        answerSheetMapper.updateByPrimaryKeySelective(answerSheet);
        //删除文件
        new File(EXCEL_FILE_PATH+answerSheetId+"_A"+EXCEL_SUFFIX).delete();
        new File(EXCEL_FILE_PATH+answerSheetId+"_B"+EXCEL_SUFFIX).delete();
        return true;
    }

    /*
     * @Description 获得所有可用答题卡模板
     * @Param []
     * @return java.util.List<com.neu.edu.oms.entity.AnswerSheet>
     **/
    @Override
    public List<AnswerSheet> getAnswerSheetList(){
        return answerSheetMapper.selectExceptDeleted();
    }

    /*
     * @Description 下载excel文件，可能为null
     * @Param [excelFileNamePrefix]
     * @return byte[]
     **/
    @Override
    public byte[] downloadExcelByName(String excelFileNamePrefix){
        return FileUtil.getFileStream(EXCEL_FILE_PATH+excelFileNamePrefix+EXCEL_SUFFIX);
    }
}
