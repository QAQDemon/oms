package com.neu.edu.oms.entity;

public class Score {
    private Integer id;
    private Integer scoreget;
    private Integer scoreall;

    public Score(Integer id){
        this.id = id;
        this.scoreall =0;
        this.scoreget = 0;
    }

    public Score(){
        super();
        this.scoreall =0;
        this.scoreget = 0;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getScoreget() {
        return scoreget;
    }

    public void setScoreget(Integer scoreget) {
        this.scoreget = scoreget;
    }

    public Integer getScoreall() {
        return scoreall;
    }

    public void setScoreall(Integer scoreall) {
        this.scoreall = scoreall;
    }
}
