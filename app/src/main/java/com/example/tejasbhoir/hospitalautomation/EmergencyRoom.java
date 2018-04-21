package com.example.tejasbhoir.hospitalautomation;

import com.example.tejasbhoir.hospitalautomation.Patient;

public class EmergencyRoom {

    /*
     * Class to represent an emergency room
     */

    private boolean isUsed;
    private Patient patient;
    private long roomCode;

    public EmergencyRoom(long roomCode) {
        isUsed = false;
        patient = null;
        this.roomCode = roomCode;
    }

    public boolean getIsUsed() {return isUsed;}
    public Patient getPatient() {return patient;}
    public long getRoomCode() {return roomCode;}

}
