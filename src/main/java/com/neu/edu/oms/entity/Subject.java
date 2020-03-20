package com.neu.edu.oms.entity;

public class Subject {
    private Integer subjectId;

    private String subjectName;

    private String college;

    private String reserve1;

    private String reserve2;

    public Subject(Integer subjectId, String subjectName, String college, String reserve1, String reserve2) {
        this.subjectId = subjectId;
        this.subjectName = subjectName;
        this.college = college;
        this.reserve1 = reserve1;
        this.reserve2 = reserve2;
    }

    public Subject() {
        super();
    }

    public Integer getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName == null ? null : subjectName.trim();
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
}