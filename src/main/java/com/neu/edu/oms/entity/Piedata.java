package com.neu.edu.oms.entity;

public class Piedata {
    private String name;
    private Integer value;

    public Piedata(Integer value, String name){
        this.value = value;
        this.name = name;
    }

    public Piedata() {
        super();
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
