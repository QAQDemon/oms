package com.neu.edu.oms.entity;

import java.util.Date;

public class SubjMark {
    private Integer subjMarkId;

    private Integer paperScanId;

    private Short scoreGet;

    private Short score;

    private Short questionNum;

    private Integer pointId;

    private Integer goalId;

    private Date markTime;

    private Integer subjAnswerId;

    private Integer teacherId;

    private String photoAddress;

    private String reserve1;

    private String reserve2;

    public SubjMark(Integer subjMarkId, Integer paperScanId, Short scoreGet, Short score, Short questionNum, Integer pointId, Integer goalId, Date markTime, Integer subjAnswerId, Integer teacherId, String photoAddress, String reserve1, String reserve2) {
        this.subjMarkId = subjMarkId;
        this.paperScanId = paperScanId;
        this.scoreGet = scoreGet;
        this.score = score;
        this.questionNum = questionNum;
        this.pointId = pointId;
        this.goalId = goalId;
        this.markTime = markTime;
        this.subjAnswerId = subjAnswerId;
        this.teacherId = teacherId;
        this.photoAddress = photoAddress;
        this.reserve1 = reserve1;
        this.reserve2 = reserve2;
    }

    public SubjMark() {
        super();
    }

    public Integer getSubjMarkId() {
        return subjMarkId;
    }

    public void setSubjMarkId(Integer subjMarkId) {
        this.subjMarkId = subjMarkId;
    }

    public Integer getPaperScanId() {
        return paperScanId;
    }

    public void setPaperScanId(Integer paperScanId) {
        this.paperScanId = paperScanId;
    }

    public Short getScoreGet() {
        return scoreGet;
    }

    public void setScoreGet(Short scoreGet) {
        this.scoreGet = scoreGet;
    }

    public Short getScore() {
        return score;
    }

    public void setScore(Short score) {
        this.score = score;
    }

    public Short getQuestionNum() {
        return questionNum;
    }

    public void setQuestionNum(Short questionNum) {
        this.questionNum = questionNum;
    }

    public Integer getPointId() {
        return pointId;
    }

    public void setPointId(Integer pointId) {
        this.pointId = pointId;
    }

    public Integer getGoalId() {
        return goalId;
    }

    public void setGoalId(Integer goalId) {
        this.goalId = goalId;
    }

    public Date getMarkTime() {
        return markTime;
    }

    public void setMarkTime(Date markTime) {
        this.markTime = markTime;
    }

    public Integer getSubjAnswerId() {
        return subjAnswerId;
    }

    public void setSubjAnswerId(Integer subjAnswerId) {
        this.subjAnswerId = subjAnswerId;
    }

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    public String getPhotoAddress() {
        return photoAddress;
    }

    public void setPhotoAddress(String photoAddress) {
        this.photoAddress = photoAddress == null ? null : photoAddress.trim();
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