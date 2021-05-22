package com.example.userdb.model;

import android.util.Log;

public class Foods {
    private int food_id;
    private String image;
    private String title;
    private String desc;
    private String user;

    public Foods(String image, String title, String desc, String user){
        this.image = image;
        this.title = title;
        this.desc = desc;
        this.user = user;
    }

    public Foods(){}

    public int getFood_id() {
        return food_id;
    }

    public void setFood_id(int food_id) {
        this.food_id = food_id;
    }

    public String getFoodImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getUser(){ return this.user; }
    public void setUser(String user){this.user = user; }
}
