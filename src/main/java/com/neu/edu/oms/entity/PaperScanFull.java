package com.neu.edu.oms.entity;

import java.util.Date;
import java.util.List;

public class PaperScanFull {
    private Integer paperScanId;

    private Integer studentId;

    private Integer answerId;

    private String paperName;

    private Integer studentExamNum;

    private String barCode;

    private Integer subjectId;

    private Date submitTime;

    private String addressPrefix;

    private Byte isAssign;

    private Byte isMark;

    private Integer objGet;

    private Integer objAll;

    private Integer subjGet;

    private Integer subjAll;

    private Integer scoreGet;

    private Integer score;

    private String reserve1;

    private String reserve2;

    private String studentName;

    private String answerName;

    private List<ObjMark> objMarks;

    private List<SubjScore> subjScores;

    private Short objnum;

    private Short subjnum;

    public PaperScanFull(Integer paperScanId, Integer studentId, Integer answerId, String paperName, Integer studentExamNum, String barCode, Integer subjectId, Date submitTime, String addressPrefix, Byte isAssign, Byte isMark, Integer objGet, Integer objAll, Integer subjGet, Integer subjAll, Integer scoreGet, Integer score, String reserve1, String reserve2) {
        this.paperScanId = paperScanId;
        this.studentId = studentId;
        this.answerId = answerId;
        this.paperName = paperName;
        this.studentExamNum = studentExamNum;
        this.barCode = barCode;
        this.subjectId = subjectId;
        this.submitTime = submitTime;
        this.addressPrefix = addressPrefix;
        this.isAssign = isAssign;
        this.isMark = isMark;
        this.objGet = objGet;
        this.objAll = objAll;
        this.subjGet = subjGet;
        this.subjAll = subjAll;
        this.scoreGet = scoreGet;
        this.score = score;
        this.reserve1 = reserve1;
        this.reserve2 = reserve2;
    }

    public PaperScanFull(PaperScan paperScan){
        this.paperScanId = paperScan.getPaperScanId();
        this.studentId = paperScan.getStudentId();
        this.answerId = paperScan.getAnswerId();
        this.paperName = paperScan.getPaperName();
        this.studentExamNum = paperScan.getStudentExamNum();
        this.barCode = paperScan.getBarCode();
        this.subjectId = paperScan.getSubjectId();
        this.submitTime = paperScan.getSubmitTime();
        this.addressPrefix = paperScan.getAddressPrefix();
        this.isAssign = paperScan.getIsAssign();
        this.isMark = paperScan.getIsMark();
        this.objGet = paperScan.getObjGet();
        this.objAll = paperScan.getObjAll();
        this.subjGet = paperScan.getSubjGet();
        this.subjAll = paperScan.getSubjAll();
        this.scoreGet = paperScan.getScoreGet();
        this.score = paperScan.getScore();
        this.reserve1 = paperScan.getReserve1();
        this.reserve2 = paperScan.getReserve2();
    }

    public PaperScanFull() {
        super();
    }

    public Integer getPaperScanId() {
        return paperScanId;
    }

    public void setPaperScanId(Integer paperScanId) {
        this.paperScanId = paperScanId;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Integer getAnswerId() {
        return answerId;
    }

    public void setAnswerId(Integer answerId) {
        this.answerId = answerId;
    }

    public String getPaperName() {
        return paperName;
    }

    public void setPaperName(String paperName) {
        this.paperName = paperName == null ? null : paperName.trim();
    }

    public Integer getStudentExamNum() {
        return studentExamNum;
    }

    public void setStudentExamNum(Integer studentExamNum) {
        this.studentExamNum = studentExamNum;
    }

    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode == null ? null : barCode.trim();
    }

    public Integer getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }

    public Date getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(Date submitTime) {
        this.submitTime = submitTime;
    }

    public String getAddressPrefix() {
        return addressPrefix;
    }

    public void setAddressPrefix(String addressPrefix) {
        this.addressPrefix = addressPrefix == null ? null : addressPrefix.trim();
    }

    public Byte getIsAssign() {
        return isAssign;
    }

    public void setIsAssign(Byte isAssign) {
        this.isAssign = isAssign;
    }

    public Byte getIsMark() {
        return isMark;
    }

    public void setIsMark(Byte isMark) {
        this.isMark = isMark;
    }

    public Integer getObjGet() {
        return objGet;
    }

    public void setObjGet(Integer objGet) {
        this.objGet = objGet;
    }

    public Integer getObjAll() {
        return objAll;
    }

    public void setObjAll(Integer objAll) {
        this.objAll = objAll;
    }

    public Integer getSubjGet() {
        return subjGet;
    }

    public void setSubjGet(Integer subjGet) {
        this.subjGet = subjGet;
    }

    public Integer getSubjAll() {
        return subjAll;
    }

    public void setSubjAll(Integer subjAll) {
        this.subjAll = subjAll;
    }

    public Integer getScoreGet() {
        return scoreGet;
    }

    public void setScoreGet(Integer scoreGet) {
        this.scoreGet = scoreGet;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
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

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getAnswerName() {
        return answerName;
    }

    public void setAnswerName(String answerName) {
        this.answerName = answerName;
    }

    public List<ObjMark> getObjMarks() {
        return objMarks;
    }

    public void setObjMarks(List<ObjMark> objMarks) {
        this.objMarks = objMarks;
    }

    public List<SubjScore> getSubjScores() {
        return subjScores;
    }

    public void setSubjScores(List<SubjScore> subjScores) {
        this.subjScores = subjScores;
    }

    public Short getObjnum() {
        return objnum;
    }

    public void setObjnum(Short objnum) {
        this.objnum = objnum;
    }

    public Short getSubjnum() {
        return subjnum;
    }

    public void setSubjnum(Short subjnum) {
        this.subjnum = subjnum;
    }
}