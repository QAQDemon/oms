package com.neu.edu.oms.entity;

import java.util.Date;

public class ObjMark {
    private Integer objMarkId;

    private Integer paperSacnId;

    private Short scoreGet;

    private Short score;

    private Integer pointId;

    private Integer goalId;

    private Date markTime;

    private Byte isRight;

    private Short questionNum;

    private String reserve2;

    public ObjMark(Integer objMarkId, Integer paperSacnId, Short scoreGet, Short score, Integer pointId, Integer goalId, Date markTime, Byte isRight, Short questionNum, String reserve2) {
        this.objMarkId = objMarkId;
        this.paperSacnId = paperSacnId;
        this.scoreGet = scoreGet;
        this.score = score;
        this.pointId = pointId;
        this.goalId = goalId;
        this.markTime = markTime;
        this.isRight = isRight;
        this.questionNum = questionNum;
        this.reserve2 = reserve2;
    }

    public ObjMark() {
        super();
    }

    public Integer getObjMarkId() {
        return objMarkId;
    }

    public void setObjMarkId(Integer objMarkId) {
        this.objMarkId = objMarkId;
    }

    public Integer getPaperSacnId() {
        return paperSacnId;
    }

    public void setPaperSacnId(Integer paperSacnId) {
        this.paperSacnId = paperSacnId;
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

    public Byte getIsRight() {
        return isRight;
    }

    public void setIsRight(Byte isRight) {
        this.isRight = isRight;
    }

    public Short getQuestionNum() {
        return questionNum;
    }

    public void setQuestionNum(Short questionNum) {
        this.questionNum = questionNum;
    }

    public String getReserve2() {
        return reserve2;
    }

    public void setReserve2(String reserve2) {
        this.reserve2 = reserve2 == null ? null : reserve2.trim();
    }
}