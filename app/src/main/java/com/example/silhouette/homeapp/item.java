package com.example.silhouette.homeapp;

/**
 * Created by Silhouette on 2018/4/2.
 */

public class item {

    private String content;
    private String result;
    private String ac;

    public item() {
    }

    public item(String content, String result,String ac) {
        this.content = content;
        this.result = result;
        this.ac = ac;
    }

    public String getContent() {
        return content;
    }

    public String getResult() {
        return result;
    }

    public String getAc() {
        return ac;
    }


    public void setContent(String content) {
        this.content = content;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public void setAc(String ac) {
        this.ac = ac;
    }

}

