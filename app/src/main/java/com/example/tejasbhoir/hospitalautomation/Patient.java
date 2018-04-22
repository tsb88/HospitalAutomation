package com.example.tejasbhoir.hospitalautomation;

public class Patient {
    private String mName;
    private int mAge;
    private String mGender;
    private String mID;

    Patient(String name, int age, String gender, String ID){
        mName = name;
        mAge = age;
        mGender = gender;
        mID = ID;
    }

    Patient() {
        mName = "";
        mAge = 0;
        mGender = "";
        mID = "";
    }

    public String getmName() {return mName;}

    public int getmAge() {return mAge;}

    public String getmGender() {return mGender;}

    public String getmID() {return mID;}
}
