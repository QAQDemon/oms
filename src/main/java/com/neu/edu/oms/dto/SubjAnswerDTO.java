package com.neu.edu.oms.dto;

import com.neu.edu.oms.entity.SubjAnswer;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

//无用，可删
public class SubjAnswerDTO implements Serializable {

    private SubjAnswer[] subjAnswers;
    private MultipartFile[] imgFiles;//得分点图片，可能有空值

    public SubjAnswer[] getSubjAnswers() {
        return subjAnswers;
    }

    public void setSubjAnswers(SubjAnswer[] subjAnswers) {
        this.subjAnswers = subjAnswers;
    }

    public MultipartFile[] getImgFiles() {
        return imgFiles;
    }

    public void setImgFiles(MultipartFile[] imgFiles) {
        this.imgFiles = imgFiles;
    }
}
