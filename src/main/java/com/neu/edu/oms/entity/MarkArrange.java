package com.neu.edu.oms.entity;

public class MarkArrange {
    private Integer markArrangeId;

    private Integer teacherId;

    private Integer paperScanId;

    private Short questionNum;

    private String photoAddress;

    private String photoPrefix;

    private Integer answerId;

    private Short scoreGet;

    private Byte isMark;

    private String reserve1;

    private String reserve2;

    public MarkArrange(Integer markArrangeId, Integer teacherId, Integer paperScanId, Short questionNum, String photoAddress, String photoPrefix, Integer answerId, Short scoreGet, Byte isMark, String reserve1, String reserve2) {
        this.markArrangeId = markArrangeId;
        this.teacherId = teacherId;
        this.paperScanId = paperScanId;
        this.questionNum = questionNum;
        this.photoAddress = photoAddress;
        this.photoPrefix = photoPrefix;
        this.answerId = answerId;
        this.scoreGet = scoreGet;
        this.isMark = isMark;
        this.reserve1 = reserve1;
        this.reserve2 = reserve2;
    }

    public MarkArrange() {
        super();
    }

    public Integer getMarkArrangeId() {
        return markArrangeId;
    }

    public void setMarkArrangeId(Integer markArrangeId) {
        this.markArrangeId = markArrangeId;
    }

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    public Integer getPaperScanId() {
        return paperScanId;
    }

    public void setPaperScanId(Integer paperScanId) {
        this.paperScanId = paperScanId;
    }

    public Short getQuestionNum() {
        return questionNum;
    }

    public void setQuestionNum(Short questionNum) {
        this.questionNum = questionNum;
    }

    public String getPhotoAddress() {
        return photoAddress;
    }

    public void setPhotoAddress(String photoAddress) {
        this.photoAddress = photoAddress == null ? null : photoAddress.trim();
    }

    public String getPhotoPrefix() {
        return photoPrefix;
    }

    public void setPhotoPrefix(String photoPrefix) {
        this.photoPrefix = photoPrefix == null ? null : photoPrefix.trim();
    }

    public Integer getAnswerId() {
        return answerId;
    }

    public void setAnswerId(Integer answerId) {
        this.answerId = answerId;
    }

    public Short getScoreGet() {
        return scoreGet;
    }

    public void setScoreGet(Short scoreGet) {
        this.scoreGet = scoreGet;
    }

    public Byte getIsMark() {
        return isMark;
    }

    public void setIsMark(Byte isMark) {
        this.isMark = isMark;
    }

    public String getReserve1() {
        return reserve1;
    }

    public void setReserve1(String reserve1) {
        this.reserve1 = reserve1 == null ? null : reserve1.trim();
    }

    public String getReserve2() {
        return reserve2;
    }

    public void setReserve2(String reserve2) {
        this.reserve2 = reserve2 == null ? null : reserve2.trim();
    }
}