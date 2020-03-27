package com.neu.edu.oms.entity;

import java.util.Date;

public class AnswerSheet {
    private Integer answerSheetId;

    private Integer administratorId;

    private String answerSheetName;

    private Date establishTime;

    private Short objNum;

    private Short subjNum;

    private Integer adoptNum;

    private Short isDeleted;

    private String reserve2;

    public AnswerSheet(Integer answerSheetId, Integer administratorId, String answerSheetName, Date establishTime, Short objNum, Short subjNum, Integer adoptNum, Short isDeleted, String reserve2) {
        this.answerSheetId = answerSheetId;
        this.administratorId = administratorId;
        this.answerSheetName = answerSheetName;
        this.establishTime = establishTime;
        this.objNum = objNum;
        this.subjNum = subjNum;
        this.adoptNum = adoptNum;
        this.isDeleted = isDeleted;
        this.reserve2 = reserve2;
    }

    public AnswerSheet() {
        super();
    }

    public Integer getAnswerSheetId() {
        return answerSheetId;
    }

    public void setAnswerSheetId(Integer answerSheetId) {
        this.answerSheetId = answerSheetId;
    }

    public Integer getAdministratorId() {
        return administratorId;
    }

    public void setAdministratorId(Integer administratorId) {
        this.administratorId = administratorId;
    }

    public String getAnswerSheetName() {
        return answerSheetName;
    }

    public void setAnswerSheetName(String answerSheetName) {
        this.answerSheetName = answerSheetName == null ? null : answerSheetName.trim();
    }

    public Date getEstablishTime() {
        return establishTime;
    }

    public void setEstablishTime(Date establishTime) {
        this.establishTime = establishTime;
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

    public Integer getAdoptNum() {
        return adoptNum;
    }

    public void setAdoptNum(Integer adoptNum) {
        this.adoptNum = adoptNum;
    }

    public Short getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Short isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getReserve2() {
        return reserve2;
    }

    public void setReserve2(String reserve2) {
        this.reserve2 = reserve2 == null ? null : reserve2.trim();
    }
}