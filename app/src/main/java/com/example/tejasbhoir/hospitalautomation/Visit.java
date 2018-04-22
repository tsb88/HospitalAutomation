package com.example.tejasbhoir.hospitalautomation;

import android.util.Log;

public class Visit implements Comparable<Visit> {

    private int mPriority;
    private int mID;
    private int mPatientID;
    private int mDuration;
    private int mDoctorID;
    private boolean mIsWaiting;

    public Visit() {
        mIsWaiting= true;
        mDoctorID = -1;
    }

    // Getters

    public boolean getmIsWaiting() {return mIsWaiting;}
    public int getmPriority() {return mPriority;}
    public int getmID() {return mID;}
    public int getmDuration() {return mDuration;}
    public int getmPatientID() {return mPatientID;}
    public int getmDoctorID(){return  mDoctorID;}

    // Setters

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
            ret = "" + mPatientID;
            ret+= " P: " + mPriority;
            ret+= " D: " + mDuration;
        } catch(NullPointerException e) {
            Log.e("Visit","Null Pointer to Patient");
            ret = "Patient object not assigned to this Visit object";
        }

        return ret;
    }
}
