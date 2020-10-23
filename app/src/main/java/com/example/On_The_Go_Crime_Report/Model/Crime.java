package com.example.On_The_Go_Crime_Report.Model;

public class Crime {
    private  String area, desc, image, status;

    public Crime(){

    }

    public Crime(String area, String desc, String image, String status) {
        this.area = area;
        this.desc = desc;
        this.image = image;
        this.status= status;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
    public String getStatus() {
        return status;
    }
}
