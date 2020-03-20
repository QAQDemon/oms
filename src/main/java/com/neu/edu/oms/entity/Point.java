package com.neu.edu.oms.entity;

public class Point {
    private Integer pointId;

    private Integer subjectId;

    private String subjectName;

    private String pointName;

    private String reserve1;

    private String reserve2;

    public Point(Integer pointId, Integer subjectId, String subjectName, String pointName, String reserve1, String reserve2) {
        this.pointId = pointId;
        this.subjectId = subjectId;
        this.subjectName = subjectName;
        this.pointName = pointName;
        this.reserve1 = reserve1;
        this.reserve2 = reserve2;
    }

    public Point() {
        super();
    }

    public Integer getPointId() {
        return pointId;
    }

    public void setPointId(Integer pointId) {
        this.pointId = pointId;
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

    public String getPointName() {
        return pointName;
    }

    public void setPointName(String pointName) {
        this.pointName = pointName == null ? null : pointName.trim();
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