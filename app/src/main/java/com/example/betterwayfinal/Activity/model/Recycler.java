package com.example.betterwayfinal.Activity.model;

import java.io.Serializable;

public class Recycler implements Serializable {
    private long id;
    private String CordX;
    private String CordY;
    private String CordZ;
    private String tipoDeDesnivel;

    public String getCordX() {
        return CordX;
    }
    public void setCordX(String cord) {
        this.CordX = cord;
    }


    public String getCordY() {
        return CordY;
    }
    public void setCordY(String cord) {
        this.CordY = cord;
    }


    public String getCordZ() {
        return CordZ;
    }
    public void setCordZ(String cord) {
        this.CordZ = cord;
    }


    public String gettipoDeDesnivel() {
        return tipoDeDesnivel;
    }
    public void settipoDeDesnivel(String cord) {
        this.tipoDeDesnivel = cord;
    }


    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
}
