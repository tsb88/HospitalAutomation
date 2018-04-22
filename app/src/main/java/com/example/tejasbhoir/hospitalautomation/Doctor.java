package com.example.tejasbhoir.hospitalautomation;

import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.tejasbhoir.hospitalautomation.Staff;
import com.example.tejasbhoir.hospitalautomation.EmergencyRoom;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Doctor extends Staff implements Serializable{

    //private boolean isAvailable;
    //private EmergencyRoom emergencyRoom;
    private HashMap<String, Integer> mPatients;

    Doctor() {
       // isAvailable = true;
       // emergencyRoom = null;
    }

    // Getters
   // public boolean getIsAvailable() {return isAvailable;}
  //  public EmergencyRoom getEmergencyRoom() {return emergencyRoom;}

    public HashMap<String,Integer> getmPatients() {
        return mPatients;
    }

}
