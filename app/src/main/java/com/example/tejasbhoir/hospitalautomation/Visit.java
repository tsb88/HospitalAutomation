package com.example.tejasbhoir.hospitalautomation;

public class Visit {

    private Patient patient;
    private Doctor doctor;
    private boolean isWaiting;
    private int startPriority;

    public Visit(Patient patient, int startPriority) {
        this.patient = patient;
        this.doctor = doctor;
        isWaiting = true;
        this.startPriority = startPriority;
    }

    // Getters
    public Patient getPatient() {return patient;}
    public Doctor getDoctor() { return doctor; }
    public boolean getIsWaiting() {return isWaiting;}
    public int getStartPriority() {return startPriority;}
}
