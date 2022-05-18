package com.example.excersise2.model;

import java.io.Serializable;

public class Food implements Serializable {
    private long id;
    private String name;
    private long price;
    private String url;
    private String des;

    public Food() {}
    public Food(String name, long price, String url, String des) {
        this.name = name;
        this.price = price;
        this.url = url;
        this.des = des;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }
}
