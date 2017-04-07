package com.info.Model;

/**
 * Created by wolfmatrix on 4/1/17.
 */

public class InfoModel {
    private String FName;
    private String LName;
    private String PhoneNumber;

    private static final InfoModel ourInstance = new InfoModel();

    public static InfoModel getInstance() {
        return ourInstance;
    }

    public InfoModel() {
    }

    public InfoModel(String FName, String LName, String phoneNumber) {
        this.FName = FName;
        this.LName = LName;
        PhoneNumber = phoneNumber;
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

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }
}
