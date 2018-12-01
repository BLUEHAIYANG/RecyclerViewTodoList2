package com.example.administrator.recyclerviewtodolist;

import org.litepal.crud.DataSupport;

public class TodoWhat extends DataSupport {
    private String title;
    private String detail;
    private int position;

    /*public  TodoWhat(String title,String detail,int position){
        this.title=title;
        this.detail=detail;
        this.position=position;
        //save();
    }*/

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
