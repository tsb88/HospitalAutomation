package com.example.tejasbhoir.hospitalautomation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DoctorActivity extends AppCompatActivity {

    ListView patientList;
    TextView recentMessage;
    Doctor doctor;

    FirebaseDatabase db = FirebaseDatabase.getInstance();
    DatabaseReference myRef = db.getReference();

    int id;
    String recentDBMessage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor);

        Toast.makeText(getApplicationContext(), "This is working (Doctor Page)", Toast.LENGTH_LONG).show();

        Intent intent = this.getIntent();
        id = intent.getIntExtra("ID",0);

        patientList = findViewById(R.id.patientList);
        recentMessage = findViewById(R.id.recentMessage);

        checkDatabaseReference();

        recentMessage.setText(recentDBMessage);

        getDoctor();

       // Log.v("Doctor", doctor.getmName());

        /*List<Integer> dbPatientListView;

        ArrayAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dbPatientListView);
        patientList.setAdapter(adapter);
        adapter.notifyDataSetChanged();*/

    }

    Query lastQuery = myRef.child("messages").child("doctors").child(Integer.toString(id)).orderByKey().limitToLast(1);
    public void checkDatabaseReference() {
        lastQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChild("messages")) {
                    if (dataSnapshot.child("messages").child("doctors").child("ID").hasChild("" + id)) {
                        recentDBMessage = dataSnapshot.child("message").getValue().toString();
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void getDoctor(){
        myRef.child("staff").child("doctors").child(""+id).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                doctor = dataSnapshot.getValue(Doctor.class);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
