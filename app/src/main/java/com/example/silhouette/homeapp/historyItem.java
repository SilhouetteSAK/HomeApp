package com.example.silhouette.homeapp;

/**
 * Created by Silhouette on 2018/4/8.
 */

public class historyItem {

    private String operation;
    private String time;
    private int icon;

    public historyItem() {
    }

    public historyItem(String operation, String time, int icon) {
        this.operation = operation;
        this.time = time;
        this.icon = icon;
    }

    public String getOperation() {
        return operation;
    }

    public String getTime() {
        return time;
    }

    public int getIcon() {
        return icon;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }
}
