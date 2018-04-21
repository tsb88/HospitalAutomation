package com.example.tejasbhoir.hospitalautomation;

public class Patient {
    private String mName;
    private int mAge;
    private char mGender;
    private int mID;

    Patient(String name, int age, char gender, int ID){
        mName = name;
        mAge = age;
        mGender = gender;
        mID = ID;
    }

    Patient() {
        mName = "";
        mAge = 0;
        mGender = '\0';
        mID = 0;
    }

    public String getmName() {return mName;}

    public int getmAge() {return mAge;}

    public char getmGender() {return mGender;}

    public int getmID() {return mID;}
}
