package com.example.tejasbhoir.hospitalautomation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.tejasbhoir.hospitalautomation.Heap;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AdminActivity extends AppCompatActivity {

    Heap<Visit> pq;
    List<Visit> nextVisit;
    List<Patient> patients;

    FirebaseDatabase db = FirebaseDatabase.getInstance();
    DatabaseReference myRef = db.getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        Toast.makeText(getBaseContext(),"This is working (Admin page)",Toast.LENGTH_LONG).show();
        Log.v("Visit","ADMIN ACTIVITY OPENED");

        pq = new Heap<Visit>();
        nextVisit = new ArrayList<Visit>();
        patients = new ArrayList<Patient>();
        getPatientList();
        //getVisitList();

    }

    public void getVisitList() {
        myRef.child("visit").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    nextVisit.add(postSnapshot.getValue(Visit.class));
                }
                // New visits were added to the nextVisit list
                // Enqueue all of these
                Log.v("Visit","Visit change detected");
                enqueue();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void getPatientList() {
        myRef.child("patients").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                patients.clear();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    patients.add(postSnapshot.getValue(Patient.class));
                    Log.v("AdminActivity","A patient was found");
                    Log.v("AdminActivity","Patient name: " + patients.get(patients.size()-1).getmName());
                }
                // New visits were added to the nextVisit list
                // Enqueue all of these
                Log.v("AdminActivity","Patient Set updated");
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


    private void enqueue() {

        boolean foundPatient;
        for(Visit next: nextVisit) {
            foundPatient = false;
            int id = next.getId();
            for(Patient patient:patients) {
                if(id == patient.getmID()) {
                    next.setPatient(patient);
                    foundPatient = true;
                    break;
                }
            }
            if(!foundPatient) {
                Log.e("Admin", "Patient associated with visit not found");
            }
            pq.insert(next);
        }
        myRef.child("visit").setValue(null);
        nextVisit.clear();
        Log.v("AdminActivity","priority Queue string next");
        Log.v("AdminActivity",pq.toString());
    }

}
