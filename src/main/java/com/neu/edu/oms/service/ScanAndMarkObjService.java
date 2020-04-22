package com.neu.edu.oms.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/*
 * @Description
 * @author demon
 * @version v1.0
 **/
public interface ScanAndMarkObjService {
    List<String> saveScanImg(MultipartFile[] scanImgArray);
    List<String> setScanAndObj(String objChoiceJson,int sign);
}
