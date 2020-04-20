package com.neu.edu.oms.service;

import com.neu.edu.oms.entity.AnswerSheet;
import org.dom4j.DocumentException;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

/*
 * @Description
 * @author demon
 * @version v1.0
 **/
public interface MakeTemplateService {
    String getXMLEncode() throws DocumentException;
    Boolean saveXML(String content);
    Boolean setNewAnswerSheetAndSaveExcelFiles(String answerSheetJson,MultipartFile[] excelFiles);
    Boolean deleteAnswerSheet(int answerSheetId);
    List<AnswerSheet> getAnswerSheetList();
    byte[] downloadExcelByName(String excelFileNamePrefix);
}
