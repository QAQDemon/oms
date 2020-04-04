package com.neu.edu.oms.service.impl;

import com.neu.edu.oms.dao.AnswerSheetMapper;
import com.neu.edu.oms.entity.AnswerSheet;
import com.neu.edu.oms.service.MakeTemplateService;
import com.neu.edu.oms.utils.Base64Util;
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

@Service
public class MakeTemplateServiceImpl implements MakeTemplateService {

    @Resource
    AnswerSheetMapper answerSheetMapper;

    /*
     * @Description 保存文件到本地
     * @Param [excelFile，path保存路径包含文件名D://test.xlsx]
     * @return java.lang.Boolean true成功 false失败
     **/
    public Boolean saveExcelFile(MultipartFile excelFile,String path){
        if (excelFile.isEmpty()) {
            return false;
        }
        File dest = new File(path);
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            excelFile.transferTo(dest); // 保存文件
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /*
     * @Description 根据路径获得xml文件的内容并加密
     * @Param [path src/main/resources/paperTemplate.xml]
     * @return java.lang.String
     **/
    public String getXMLEncode(String path)throws DocumentException {
        return Base64Util.encodeToString(new PaperXMLReader(path).readAllAsString());
    }

    /*
     * @Description 先解密再保存xml文件
     * @Param [path, content加密的xml内容]
     * @return java.lang.Boolean
     **/
    public Boolean saveXML(String path,String content){
        content = Base64Util.decodeToString(content);//解密
        FileWriter writer;
        try {
            writer = new FileWriter(path);
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
     * @Description 插入新的答题卡模板并返回主键id
     * @Param [answerSheet]
     * @return int 非0：主键id，0：失败
     **/
    public int setNewAnswerSheet(AnswerSheet answerSheet) {
        answerSheet.setEstablishTime(new Date());
        return (1==answerSheetMapper.insertSelective(answerSheet))?answerSheet.getAnswerSheetId():0;//1：成功，返回主键  0：失败，返回0
    }

    /*
     * @Description 删除选中的答题卡模板，根据采用数判断是否可删;还要删除对应AB卷Excel文件
     * @Param [answerSheetId,directory excel文件目录]
     * @return java.lang.Boolean
     **/
    public Boolean deleteAnswerSheet(int answerSheetId,String directory){
        AnswerSheet answerSheet=answerSheetMapper.selectByPrimaryKey(answerSheetId);
        if(0!=answerSheet.getAdoptNum())//使用过则不能删除
            return false;
        if(1==answerSheet.getIsDeleted())//已删除
            return true;
        //删除数据库
        answerSheet.setIsDeleted((short)1);
        answerSheetMapper.updateByPrimaryKeySelective(answerSheet);
        //删除文件
        new File(directory+answerSheetId+"_A.xlsx").delete();
        new File(directory+answerSheetId+"_B.xlsx").delete();
        return true;
    }

    /*
     * @Description 获得所有可用答题卡模板
     * @Param []
     * @return java.util.List<com.neu.edu.oms.entity.AnswerSheet>
     **/
    public List<AnswerSheet> getAnswerSheetList(){
        return answerSheetMapper.selectExceptDeleted();
    }
}
