package com.neu.edu.oms.service.impl;

import com.neu.edu.oms.dao.GoalMapper;
import com.neu.edu.oms.dao.ObjAnswerMapper;
import com.neu.edu.oms.dao.PointMapper;
import com.neu.edu.oms.dao.SubjAnswerMapper;
import com.neu.edu.oms.entity.Goal;
import com.neu.edu.oms.entity.ObjAnswer;
import com.neu.edu.oms.entity.Point;
import com.neu.edu.oms.entity.SubjAnswer;
import com.neu.edu.oms.service.SetAnswerService;
import com.neu.edu.oms.utils.FileUtil;
import com.neu.edu.oms.utils.JsonUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import javax.annotation.Resource;
import java.util.*;

/*
 * @Description
 * @author demon
 * @version v1.0
 **/
@Service
public class SetAnswerServiceImpl implements SetAnswerService {

    @Resource
    ObjAnswerMapper objAnswerMapper;
    @Resource
    SubjAnswerMapper subjAnswerMapper;
    @Resource
    PointMapper pointMapper;
    @Resource
    GoalMapper goalMapper;

    private final static String POINT_IMG_FILE_PATH="C:\\Users\\demon\\Desktop\\ResourceBackup\\pointImg\\";
    private final static String IMG_SUFFIX=".jpg";

    /*
     * @Description 根据answerId获得客观题答案
     * @Param [answerId]
     * @return java.util.List<com.neu.edu.oms.entity.ObjAnswer>
     **/
    @Override
    public List<ObjAnswer> getObjAnswerByAnswerId(int answerId){
        return objAnswerMapper.selectByAnswerId(answerId);
    }

    /*
     * @Description 根据answerId获得主观题答案
     * @Param [answerId]
     * @return java.util.List<com.neu.edu.oms.entity.SubjAnswer>
     **/
    @Override
    public List<SubjAnswer> getSubjAnswerByAnswerId(int answerId){
        return subjAnswerMapper.selectByAnswerId(answerId);
    }

    /*
     * @Description 保存客观题答案到数据库
     * @Param [objAnswers]
     * @return java.lang.Boolean true更新数>1 false更新数=0
     **/
    @Override
    public Boolean setObjAnswer(ObjAnswer[] objAnswers){
        //数据库工作流程：先增加所有记录，然后删除已存在主键并更新，返回值为更新记录数，难以用来判断成功与否
        int reslut=objAnswerMapper.updateAndInsert(new ArrayList<>(Arrays.asList(objAnswers)));//先转换成list
        return reslut!=0;
    }

    /*
     * @Description 保存主观题答案到数据库，需更新图片地址,并保存得分点图片
     * @Param [subjAnswersJson 如果无内容更新但有图片更新则为“answerId”，imgFiles 图片名为题号.jpg]
     * @return java.lang.Boolean true更新数>1 false更新数=0
     **/
    @Override
    public Boolean setSubjAnswerAndSaveImgFiles(String subjAnswersJson, MultipartFile[] imgFiles){
        List<SubjAnswer> subjAnswers=null;
        int answerId;
        //判断json内容 "1"or"[{},{}]"
        if('['==subjAnswersJson.charAt(0)) {
            subjAnswers = JsonUtils.parseToSubjAnswerList(subjAnswersJson);
            answerId=subjAnswers.get(0).getAnswerId();
        } else answerId = Integer.parseInt(subjAnswersJson);
        //更新图片地址并保存
        for (MultipartFile img : imgFiles) {
            String path=POINT_IMG_FILE_PATH + answerId + "_" + img.getName();
            //更新地址
            if(null!=subjAnswers){
                for (SubjAnswer subjAnswer : subjAnswers) {
                    if(subjAnswer.getQuestionNum()==Short.parseShort(img.getName().split("\\.")[0])) {//获得1.jpg前面的题号
                        subjAnswer.setPointPhoto(answerId + "_" + img.getName());
                        break;
                    }
                }
            }
            FileUtil.saveFile(img, path);
        }
        int result;
        if(subjAnswers!=null)//有更新内容
            result=subjAnswerMapper.updateAndInsert(subjAnswers);//数据库工作流程：先增加所有记录，然后删除已存在主键并更新，返回值为更新记录数，难以用来判断成功与否
        else return true;
        return result!=0;
    }

    /*
     * @Description 获得选定科目的所有知识点
     * @Param [subjectId]
     * @return java.util.List<com.neu.edu.oms.entity.Point>
     **/
    @Override
    public List<Point> getPointBySubjectId(int subjectId){
        return pointMapper.selectBySubjectId(subjectId);
    }

    /*
     * @Description 获得选定科目的所有考题目标
     * @Param [subjectId]
     * @return java.util.List<com.neu.edu.oms.entity.Goal>
     **/
    @Override
    public List<Goal> getGoalBySubjectId(int subjectId){
        return goalMapper.selectBySubjectId(subjectId);
    }
    
    /*
     * @Description 读取得分点图片，文件名错误可能为null
     * @Param [imgFileNamePrefix需要加上.jpg]
     * @return byte[]
     **/
    @Override
    public byte[] getPointImgByName(String imgFileNamePrefix){
        return FileUtil.getFileStream(POINT_IMG_FILE_PATH+imgFileNamePrefix+IMG_SUFFIX);
    }
}
