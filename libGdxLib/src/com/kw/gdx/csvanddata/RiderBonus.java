package com.kw.gdx.csvanddata;

public class RiderBonus {
    private int id;
    private String picture;
    private int occur;
    private int perfect;
    private int dir;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public int getOccur() {
        return occur;
    }

    public void setOccur(int occur) {
        this.occur = occur;
    }

    public int getPerfect() {
        return perfect;
    }

    public void setPerfect(int perfect) {
        this.perfect = perfect;
    }

    public int getDir() {
        return dir;
    }

    public void setDir(int dir) {
        this.dir = dir;
    }

    @Override
    public String toString() {
        return "RiderBonus{" +
                "id=" + id +
                ", picture='" + picture + '\'' +
                ", occur=" + occur +
                ", perfect=" + perfect +
                '}';
    }
}
