package com.neu.edu.oms.entity;

public class Administrator {
    private Integer administratorId;

    private String name;

    private String phonenum;

    private String email;

    private String password;

    private Byte type;

    private String college;

    private String reserve1;

    private String reserve2;

    public Administrator(Integer administratorId, String name, String phonenum, String email, String password, Byte type, String college, String reserve1, String reserve2) {
        this.administratorId = administratorId;
        this.name = name;
        this.phonenum = phonenum;
        this.email = email;
        this.password = password;
        this.type = type;
        this.college = college;
        this.reserve1 = reserve1;
        this.reserve2 = reserve2;
    }

    public Administrator() {
        super();
    }

    public Integer getAdministratorId() {
        return administratorId;
    }

    public void setAdministratorId(Integer administratorId) {
        this.administratorId = administratorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
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