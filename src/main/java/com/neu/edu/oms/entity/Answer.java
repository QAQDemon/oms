package com.neu.edu.oms.entity;

import java.util.Date;

public class Answer {
    private Integer answerId;

    private Integer answersheetId;

    private Integer administratorId;

    private Integer subjectId;

    private String answerName;

    private Date establishTime;

    private Date startTime;

    private Short examTime;

    private Short objNum;

    private Short subjNum;

    private Byte isAssign;

    private String reserve1;

    private String reserve2;

    public Answer(Integer answerId, Integer answersheetId, Integer administratorId, Integer subjectId, String answerName, Date establishTime, Date startTime, Short examTime, Short objNum, Short subjNum, Byte isAssign, String reserve1, String reserve2) {
        this.answerId = answerId;
        this.answersheetId = answersheetId;
        this.administratorId = administratorId;
        this.subjectId = subjectId;
        this.answerName = answerName;
        this.establishTime = establishTime;
        this.startTime = startTime;
        this.examTime = examTime;
        this.objNum = objNum;
        this.subjNum = subjNum;
        this.isAssign = isAssign;
        this.reserve1 = reserve1;
        this.reserve2 = reserve2;
    }

    public Answer() {
        super();
    }

    public Integer getAnswerId() {
        return answerId;
    }

    public void setAnswerId(Integer answerId) {
        this.answerId = answerId;
    }

    public Integer getAnswersheetId() {
        return answersheetId;
    }

    public void setAnswersheetId(Integer answersheetId) {
        this.answersheetId = answersheetId;
    }

    public Integer getAdministratorId() {
        return administratorId;
    }

    public void setAdministratorId(Integer administratorId) {
        this.administratorId = administratorId;
    }

    public Integer getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }

    public String getAnswerName() {
        return answerName;
    }

    public void setAnswerName(String answerName) {
        this.answerName = answerName == null ? null : answerName.trim();
    }

    public Date getEstablishTime() {
        return establishTime;
    }

    public void setEstablishTime(Date establishTime) {
        this.establishTime = establishTime;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Short getExamTime() {
        return examTime;
    }

    public void setExamTime(Short examTime) {
        this.examTime = examTime;
    }

    public Short getObjNum() {
        return objNum;
    }

    public void setObjNum(Short objNum) {
        this.objNum = objNum;
    }

    public Short getSubjNum() {
        return subjNum;
    }

    public void setSubjNum(Short subjNum) {
        this.subjNum = subjNum;
    }

    public Byte getIsAssign() {
        return isAssign;
    }

    public void setIsAssign(Byte isAssign) {
        this.isAssign = isAssign;
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