package com.info.Model;

/**
 * Created by wolfmatrix on 4/1/17.
 */

public class InfoModel {
    private String FName;
    private String LName;

    private static final InfoModel ourInstance = new InfoModel();

    public static InfoModel getInstance() {
        return ourInstance;
    }

    public InfoModel() {
    }

    public InfoModel(String FName, String LName) {
        this.FName = FName;
        this.LName = LName;
    }

    public String getFName() {
        return FName;
    }

    public void setFName(String FName) {
        this.FName = FName;
    }

    public String getLName() {
        return LName;
    }

    public void setLName(String LName) {
        this.LName = LName;
    }
}
