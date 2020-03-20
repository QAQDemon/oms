package com.neu.edu.oms.entity;

public class Goal {
    private Integer goalId;

    private Integer subjectId;

    private String subjectName;

    private String goalName;

    private String reserve1;

    private String reserve2;

    public Goal(Integer goalId, Integer subjectId, String subjectName, String goalName, String reserve1, String reserve2) {
        this.goalId = goalId;
        this.subjectId = subjectId;
        this.subjectName = subjectName;
        this.goalName = goalName;
        this.reserve1 = reserve1;
        this.reserve2 = reserve2;
    }

    public Goal() {
        super();
    }

    public Integer getGoalId() {
        return goalId;
    }

    public void setGoalId(Integer goalId) {
        this.goalId = goalId;
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

    public String getGoalName() {
        return goalName;
    }

    public void setGoalName(String goalName) {
        this.goalName = goalName == null ? null : goalName.trim();
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