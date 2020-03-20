package com.neu.edu.oms.entity;

public class TeacherClass {
    private Integer tcId;

    private Integer classId;

    private Integer teacherId;

    private Integer subjectId;

    private String subjectName;

    private Integer teachYear;

    private Byte semester;

    private String comment;

    private String reserve1;

    private String reserve2;

    public TeacherClass(Integer tcId, Integer classId, Integer teacherId, Integer subjectId, String subjectName, Integer teachYear, Byte semester, String comment, String reserve1, String reserve2) {
        this.tcId = tcId;
        this.classId = classId;
        this.teacherId = teacherId;
        this.subjectId = subjectId;
        this.subjectName = subjectName;
        this.teachYear = teachYear;
        this.semester = semester;
        this.comment = comment;
        this.reserve1 = reserve1;
        this.reserve2 = reserve2;
    }

    public TeacherClass() {
        super();
    }

    public Integer getTcId() {
        return tcId;
    }

    public void setTcId(Integer tcId) {
        this.tcId = tcId;
    }

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
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

    public Integer getTeachYear() {
        return teachYear;
    }

    public void setTeachYear(Integer teachYear) {
        this.teachYear = teachYear;
    }

    public Byte getSemester() {
        return semester;
    }

    public void setSemester(Byte semester) {
        this.semester = semester;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment == null ? null : comment.trim();
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