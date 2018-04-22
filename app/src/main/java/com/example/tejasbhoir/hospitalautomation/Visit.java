package com.example.tejasbhoir.hospitalautomation;

public class Visit implements Comparable<Visit> {

    private Patient patient;
    private Doctor doctor;
    private boolean isWaiting;
    private int priority;

    public Visit(Patient patient, int startPriority) {
        this.patient = patient;
        this.doctor = doctor;
        isWaiting = true;
        priority = startPriority;
    }

    // Getters
    public Patient getPatient() {return patient;}
    public Doctor getDoctor() { return doctor; }
    public boolean getIsWaiting() {return isWaiting;}
    public int getStartPriority() {return priority;}

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

}
