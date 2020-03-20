package com.neu.edu.oms.entity;

public class SubjAnswer {
    private Integer subjAnswerId;

    private Integer answerId;

    private Integer pointId;

    private Integer goalId;

    private Short questionNum;

    private Short score;

    private String pointDescription;

    private String pointPhoto;

    private String reserve1;

    private String reserve2;

    public SubjAnswer(Integer subjAnswerId, Integer answerId, Integer pointId, Integer goalId, Short questionNum, Short score, String pointDescription, String pointPhoto, String reserve1, String reserve2) {
        this.subjAnswerId = subjAnswerId;
        this.answerId = answerId;
        this.pointId = pointId;
        this.goalId = goalId;
        this.questionNum = questionNum;
        this.score = score;
        this.pointDescription = pointDescription;
        this.pointPhoto = pointPhoto;
        this.reserve1 = reserve1;
        this.reserve2 = reserve2;
    }

    public SubjAnswer() {
        super();
    }

    public Integer getSubjAnswerId() {
        return subjAnswerId;
    }

    public void setSubjAnswerId(Integer subjAnswerId) {
        this.subjAnswerId = subjAnswerId;
    }

    public Integer getAnswerId() {
        return answerId;
    }

    public void setAnswerId(Integer answerId) {
        this.answerId = answerId;
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

    public Short getQuestionNum() {
        return questionNum;
    }

    public void setQuestionNum(Short questionNum) {
        this.questionNum = questionNum;
    }

    public Short getScore() {
        return score;
    }

    public void setScore(Short score) {
        this.score = score;
    }

    public String getPointDescription() {
        return pointDescription;
    }

    public void setPointDescription(String pointDescription) {
        this.pointDescription = pointDescription == null ? null : pointDescription.trim();
    }

    public String getPointPhoto() {
        return pointPhoto;
    }

    public void setPointPhoto(String pointPhoto) {
        this.pointPhoto = pointPhoto == null ? null : pointPhoto.trim();
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