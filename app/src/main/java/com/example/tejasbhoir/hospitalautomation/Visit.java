package com.example.tejasbhoir.hospitalautomation;

import android.util.Log;

public class Visit implements Comparable<Visit> {

    private int mPriority;
    private int mID;
    private int mPatientID;
    private int mDuration;
    private Patient mPatient;
    private Doctor mDoctor;
    private boolean mIsWaiting;

    public Visit() {
        mPatient= null;
        mDoctor= null;
        mIsWaiting= true;
    }

    // Getters
    public Patient getmPatient() {return mPatient;}
    public Doctor getmDoctor() { return mDoctor; }
    public boolean getmIsWaiting() {return mIsWaiting;}
    public int getmPriority() {return mPriority;}
    public int getmID() {return mID;}
    public int getmDuration() {return mDuration;}
    public int getmPatientID() {return mPatientID;}

    // Setters
    public void setPatient(Patient patient) {
        this.mPatient = patient;
    }

    public int compareTo(Visit o) {
        if(o != null) {
            Visit other = (Visit) o;

            if(mPriority> other.getmPriority())
                return 1;
            if(mPriority<other.getmPriority())
                return -1;
            return 0;

        }
        else {
            // The object trying to be compared is different
            System.err.println("Cannot compare visit to non visit obj");
            System.exit(1);
        }
        return -1;
    }

    public String toString() {

        String ret = "";
        try {
            ret = mPatient.getmName();
        } catch(NullPointerException e) {
            Log.e("Visit","Null Pointer to Patient");
            ret = "Null Pointer Found";
        }

        return ret;
    }
}
