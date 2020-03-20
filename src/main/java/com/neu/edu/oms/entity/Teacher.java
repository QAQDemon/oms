package com.neu.edu.oms.entity;

public class Teacher {
    private Integer teacherId;

    private String teacherName;

    private String phonenum;

    private String email;

    private String password;

    private String position;

    private Byte type;

    private String college;

    private String reserve1;

    private String reserve2;

    public Teacher(Integer teacherId, String teacherName, String phonenum, String email, String password, String position, Byte type, String college, String reserve1, String reserve2) {
        this.teacherId = teacherId;
        this.teacherName = teacherName;
        this.phonenum = phonenum;
        this.email = email;
        this.password = password;
        this.position = position;
        this.type = type;
        this.college = college;
        this.reserve1 = reserve1;
        this.reserve2 = reserve2;
    }

    public Teacher() {
        super();
    }

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName == null ? null : teacherName.trim();
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

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position == null ? null : position.trim();
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
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