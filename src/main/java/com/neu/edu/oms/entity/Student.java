package com.neu.edu.oms.entity;

public class Student {
    private Integer studentId;

    private Integer classId;

    private String studentName;

    private String phonenum;

    private String email;

    private String password;

    private String reserve1;

    private String reserve2;

    public Student(Integer studentId, Integer classId, String studentName, String phonenum, String email, String password, String reserve1, String reserve2) {
        this.studentId = studentId;
        this.classId = classId;
        this.studentName = studentName;
        this.phonenum = phonenum;
        this.email = email;
        this.password = password;
        this.reserve1 = reserve1;
        this.reserve2 = reserve2;
    }

    public Student() {
        super();
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName == null ? null : studentName.trim();
    }

    public String getPhonenum() {
        return phonenum;
    }

    public void setPhonenum(String phonenum) {
        this.phonenum = phonenum == null ? null : phonenum.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
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