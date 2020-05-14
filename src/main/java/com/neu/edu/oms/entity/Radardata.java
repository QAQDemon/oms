package com.neu.edu.oms.entity;

public class Radardata {
    private String name;
    private Integer max;

    public Radardata(String name){
        this.name = name;
        this.max = 100;
    }
    public Radardata(){
        super();
        this.max = 100;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getMax() {
        return max;
    }

    public void setMax(Integer max) {
        this.max = max;
    }
}
