package com.neu.edu.oms.service;

import org.dom4j.DocumentException;
import org.springframework.web.multipart.MultipartFile;

public interface MakeTemplateService {
    Boolean saveExcelFile(MultipartFile excelFile,String path);
    String getXMLEncode(String path) throws DocumentException;
    Boolean saveXML(String path,String content);
}
