package com.example.tejasbhoir.hospitalautomation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AddVisitActivity extends AppCompatActivity {
    FirebaseDatabase db = FirebaseDatabase.getInstance();
    DatabaseReference rootRef = db.getReference();
    DatabaseReference patientsRef = rootRef.child("patients");

    // Variables
    Visit visit;
    //List<String> listOfPatientsIDs;
    List<Patient> listOfPatients;
    List<String> patientNames;
    Patient selectedPatient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_visit);

        getListOfPatients();



        //int listSize = listOfPatients.size();
        //for (int i=0; i < listOfPatients.size(); i++) {
        //    patientNames.add(listOfPatients.get(i).getmName());
        //}
        /*
        Spinner inputPatientSpinner = findViewById(R.id.spinner_patient);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, patientNames);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        inputPatientSpinner.setAdapter(adapter);
        inputPatientSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //selectedPatient = (Patient) parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        */
    }


    public void getListOfPatients() {
        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    //staffList.add(postSnapshot.getValue(Doctor.class));
                    listOfPatients.add(ds.getValue(Patient.class));
                    //Patient patient = ds.getValue(Patient.class);
                    //listOfPatients.add(patient);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        patientsRef.addListenerForSingleValueEvent(eventListener);
    }
}
