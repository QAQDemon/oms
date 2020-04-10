package com.neu.edu.oms.entity;

import java.util.List;

public class Class {
    private Integer classId;

    private Short classYear;

    private Byte classNum;

    private String className;

    private Short studentNum;

    private String college;

    private String reserve1;

    private String reserve2;

    private Integer teachYear;

    private Integer subjectId;

    private List<Answer> answers;

    public Class(Integer classId, Short classYear, Byte classNum, String className, Short studentNum, String college, String reserve1, String reserve2) {
        this.classId = classId;
        this.classYear = classYear;
        this.classNum = classNum;
        this.className = className;
        this.studentNum = studentNum;
        this.college = college;
        this.reserve1 = reserve1;
        this.reserve2 = reserve2;
    }

    public Class() {
        super();
    }

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public Short getClassYear() {
        return classYear;
    }

    public void setClassYear(Short classYear) {
        this.classYear = classYear;
    }

    public Byte getClassNum() {
        return classNum;
    }

    public void setClassNum(Byte classNum) {
        this.classNum = classNum;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className == null ? null : className.trim();
    }

    public Short getStudentNum() {
        return studentNum;
    }

    public void setStudentNum(Short studentNum) {
        this.studentNum = studentNum;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college == null ? null : college.trim();
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

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    public Integer getTeachYear() {
        return teachYear;
    }

    public void setTeachYear(Integer teachYear) {
        this.teachYear = teachYear;
    }

    public Integer getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Integer teachSubjectId) {
        this.subjectId = teachSubjectId;
    }
}