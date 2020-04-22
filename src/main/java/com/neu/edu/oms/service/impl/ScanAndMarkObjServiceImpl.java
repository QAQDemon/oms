package com.neu.edu.oms.service.impl;

import com.neu.edu.oms.dao.AnswerMapper;
import com.neu.edu.oms.dao.ObjAnswerMapper;
import com.neu.edu.oms.dao.ObjMarkMapper;
import com.neu.edu.oms.dao.PaperScanMapper;
import com.neu.edu.oms.entity.ObjAnswer;
import com.neu.edu.oms.entity.ObjMark;
import com.neu.edu.oms.entity.PaperScan;
import com.neu.edu.oms.service.ScanAndMarkObjService;
import com.neu.edu.oms.utils.FileUtil;
import com.neu.edu.oms.utils.JsonUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/*
 * @Description
 * @author demon
 * @version v1.0
 **/
@Service
public class ScanAndMarkObjServiceImpl implements ScanAndMarkObjService {

    @Resource
    PaperScanMapper paperScanMapper;
    @Resource
    ObjMarkMapper objMarkMapper;
    @Resource
    ObjAnswerMapper objAnswerMapper;

    private final static String SCAN_IMG_PATH="C:\\Users\\demon\\Desktop\\ResourceBackup\\scanImg\\";

    /*
     * @Description 将扫描图片（总的及分割图）保存到本地，涉及大量图片，使用list保存出错文件名
     * @Param [scanImgArray]
     * @return java.util.List<java.lang.String> 出错文件名
     **/
    @Override
    public List<String> saveScanImg(MultipartFile[] scanImgArray){
        List<String> error = new ArrayList<>();
        for (MultipartFile img : scanImgArray) {
            if(!FileUtil.saveFile(img, SCAN_IMG_PATH+img.getName()))//保存图片出错
                error.add(img.getName());
        }
        return error;
    }

    /*
     * @Description 保存扫描信息和客观题批阅信息到数据库 ,主观题批阅是否删除待定
     * @Param [objChoiceJson,sign 0正常流程 1异常处理]
     * @return  List<String> 可能出错的记录（名字出错、题号和答案不匹配）
     **/
    @Override
    public List<String> setScanAndObj(String objChoiceJson,int sign){
        List<String> error = new ArrayList<>();
        Map<String, String[]> map= JsonUtils.parseToObjMap(objChoiceJson);
        for (String keyString : map.keySet()) {//遍历map添加到数据库
            String[] splits=keyString.split("_");
            if(splits.length!=3) {
                error.add(keyString);
                continue;//很难出错
            }
            int studentId=Integer.parseInt(splits[0]);
            int subjectId=Integer.parseInt(splits[1]);
            int answerId=Integer.parseInt(splits[2]);
            if (1 == sign) {//异常处理，删除记录paperScan objMark，主观题是否删除待定
                int[] paperScanIds=paperScanMapper.selectByThreeId(answerId, subjectId, studentId);
                for (int id : paperScanIds) {
                    objMarkMapper.deleteByPaperScanId(id);
                    paperScanMapper.deleteByPrimaryKey(id);
                }
            }
            //保存paperScan
            PaperScan paperScan = new PaperScan();
            paperScan.setStudentId(studentId);
            paperScan.setSubjectId(subjectId);
            paperScan.setAnswerId(answerId);
            paperScan.setSubmitTime(new Date());
            paperScan.setAddressPrefix(keyString);
            paperScanMapper.insertSelective(paperScan);
            int paperScanId=paperScan.getPaperScanId();
            //批阅客观题
            List<ObjAnswer> objAnswers = objAnswerMapper.selectByAnswerId(answerId);//获得客观题答案
            int questionNum=1;
            for (String choice : map.get(keyString)) {
                ObjAnswer nowAnswer=null;
                for (ObjAnswer objAnswer : objAnswers) {
                    if(questionNum==objAnswer.getQuestionNum()) {//题号可能不是顺序的
                        nowAnswer = objAnswer;
                        break;
                    }
                }
                ++questionNum;
                if(null==nowAnswer) {//题号不存在
                    error.add(keyString);
                    continue;
                }
                ObjMark objMark = new ObjMark();
                objMark.setPaperSacnId(paperScanId);
                objMark.setPointId(nowAnswer.getPointId());
                objMark.setGoalId(nowAnswer.getGoalId());
                objMark.setMarkTime(new Date());
                objMark.setQuestionNum((short)(questionNum-1));
                objMark.setScore(nowAnswer.getScore());
                //判断正确与否
                String answerNum=nowAnswer.getAnswerNum();//形式"1000"
                if (answerNum.equals(choice)) {//答对
                    objMark.setScoreGet(objMark.getScore());
                    objMark.setIsRight((byte) 1);
                }else {
                    objMark.setScoreGet((short)0);
                    objMark.setIsRight((byte) 0);
                }
                objAnswers.remove(nowAnswer);//删去已批阅，降低搜索效率
                objMarkMapper.insertSelective(objMark);
            }
        }
        return error;
    }
}
