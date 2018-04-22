package com.example.tejasbhoir.hospitalautomation;

public class Patient {
    private int mID;
    private int mAge;
    private String mGender;
    private String mName;

    Patient() {}

    public String getmName() {return mName;}
    public int getmAge() {return mAge;}
    public String getmGender() {return mGender;}
    public int getmID() {return mID;}

    public void setmName(String mName) {this.mName = mName;}
    public void setmID(int mID) { this.mID = mID; }
    public void setmGender(String mGender) {this.mGender = mGender;}
    public void setmAge(int mAge) {this.mAge = mAge;}
}
