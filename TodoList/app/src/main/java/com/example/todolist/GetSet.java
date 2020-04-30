package com.example.todolist;

public class GetSet {

     String title, plan, time, key;

    public GetSet() {
    }

    public GetSet(String title, String plan, String time, String key) {
        this.title = title;
        this.plan = plan;
        this.time = time;
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPlan() {
        return plan;
    }

    public void setPlan(String plan) {
        this.plan = plan;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
