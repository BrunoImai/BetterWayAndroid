package com.example.betterwayfinal.Activity.model;

import java.io.Serializable;

public class Recycler implements Serializable {
    private long id;
    private double CordX;
    private double CordY;
    private double CordZ;
    private String tipoDeDesnivel;
    private double latitude;
    private double longitude;

    public double getCordX() {
        return CordX;
    }
    public void setCordX(double cord) {
        this.CordX = cord;
    }


    public double getCordY() {
        return CordY;
    }
    public void setCordY(double cord) {
        this.CordY = cord;
    }


    public double getCordZ() {
        return CordZ;
    }
    public void setCordZ(double cord) {
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

    public double getLatitude() {
        return latitude;
    }
    public void setLatitude(double cord) {
        this.latitude = cord;
    }

    public double getLongitude() {
        return longitude;
    }
    public void setLongitude(double cord) {
        this.longitude = cord;
    }
}
