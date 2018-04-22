package com.example.tejasbhoir.hospitalautomation;

import android.provider.CalendarContract;

import java.util.Calendar;
import java.util.Date;

public class Visit {

    private Patient patient;
    private Doctor doctor;
    private boolean isWaiting;
    private int startPriority;
    private Calendar cal;

    public Visit(Patient patient, int startPriority, Calendar cal) {
        this.patient = patient;
        this.doctor = doctor;
        isWaiting = true;
        this.startPriority = startPriority;
        this.cal = cal;
    }

    // Getters
    public Patient getPatient() {return patient;}
    public Doctor getDoctor() { return doctor; }
    public boolean getIsWaiting() {return isWaiting;}
    public int getStartPriority() {return startPriority;}
    public Calendar getCal() {return cal;}
}
