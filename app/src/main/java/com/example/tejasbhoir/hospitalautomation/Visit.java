package com.example.tejasbhoir.hospitalautomation;

import android.util.Log;

public class Visit implements Comparable<Visit> {

    private Patient patient;
    private Doctor doctor;
    private boolean isWaiting;
    private int priority;
    private int id;

    public Visit() {}

    // Getters
    public Patient getPatient() {return patient;}
    public Doctor getDoctor() { return doctor; }
    public boolean getIsWaiting() {return isWaiting;}
    public int getStartPriority() {return priority;}
    public int getId() {return id;}

    // Setters
    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public int compareTo(Visit o) {
        if(o != null) {
            Visit other = (Visit) o;

            if(this.priority> other.priority)
                return 1;
            if(this.priority<other.priority)
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
        return patient.getmName();
    }
}
