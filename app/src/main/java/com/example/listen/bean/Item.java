package com.example.listen.bean;

public class Item {
    public int logo;
    public String title;
    public int id;
    public int getId() {
        return id;
    }

    public Item(int logo, String title){
        super();
        this.logo=logo;
        this.title=title;
    }

    public void setId(int id) {
        this.id = id;
    }




    public int getLogo() {
        return logo;
    }

    public void setLogo(int logo) {
        this.logo = logo;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
