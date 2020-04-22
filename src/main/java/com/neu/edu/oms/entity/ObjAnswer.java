package com.neu.edu.oms.entity;

public class ObjAnswer {
    private Integer objAnswerId;

    private Integer answerId;

    private Integer pointId;

    private Integer goalId;

    private Short questionNum;

    private Short score;

    private String answerNum;

    private String reserve1;

    private String reserve2;

    public ObjAnswer(Integer objAnswerId, Integer answerId, Integer pointId, Integer goalId, Short questionNum, Short score, String answerNum, String reserve1, String reserve2) {
        this.objAnswerId = objAnswerId;
        this.answerId = answerId;
        this.pointId = pointId;
        this.goalId = goalId;
        this.questionNum = questionNum;
        this.score = score;
        this.answerNum = answerNum;
        this.reserve1 = reserve1;
        this.reserve2 = reserve2;
    }

    public ObjAnswer() {
        super();
    }

    public Integer getObjAnswerId() {
        return objAnswerId;
    }

    public void setObjAnswerId(Integer objAnswerId) {
        this.objAnswerId = objAnswerId;
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

    public String getAnswerNum() {
        return answerNum;
    }

    public void setAnswerNum(String answerNum) {
        this.answerNum = answerNum == null ? null : answerNum.trim();
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