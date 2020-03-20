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

    private String reserve1;

    private String reserve2;

    public AnswerSheet(Integer answerSheetId, Integer administratorId, String answerSheetName, Date establishTime, Short objNum, Short subjNum, Integer adoptNum, String reserve1, String reserve2) {
        this.answerSheetId = answerSheetId;
        this.administratorId = administratorId;
        this.answerSheetName = answerSheetName;
        this.establishTime = establishTime;
        this.objNum = objNum;
        this.subjNum = subjNum;
        this.adoptNum = adoptNum;
        this.reserve1 = reserve1;
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