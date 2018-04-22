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
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DoctorActivity extends AppCompatActivity {

    ListView patientList;
    TextView recentMessage;

    FirebaseDatabase db = FirebaseDatabase.getInstance();
    DatabaseReference myRef = db.getReference();

    int id;
    String recentDBMessage;
    Doctor doctor;
    List<Long> mPatientIDs;
    HashMap<String, Long> mPatientHash;
    Collection<Long> coll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor);

        Toast.makeText(getApplicationContext(), "This is working (Doctor Page)", Toast.LENGTH_LONG).show();

        Intent intent = getIntent();
        id = intent.getIntExtra("ID",-1);

        patientList = findViewById(R.id.patientList);
        recentMessage = findViewById(R.id.recentMessage);

        checkDatabaseReference();

        recentMessage.setText(recentDBMessage);

        getDoctor();
        getPatientList();

        //ArrayAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dbPatientList);
       // patientList.setAdapter(adapter);
        //adapter.notifyDataSetChanged();

    }

    Query lastQuery = myRef.child("messages").child("doctors").child(""+id).orderByKey().limitToLast(1);
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
                Log.v("DoctorActivity","ID used to locate Doc: " + id);
                Log.v("DoctorActivity","Doctor Exists: " + dataSnapshot.exists());
                doctor = dataSnapshot.getValue(Doctor.class);
                Log.v("DoctorActivity","Doctor Name" + doctor.getmName());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void getPatientList() {

        mPatientIDs = new ArrayList<Long>();
        mPatientHash = new HashMap<String, Long>();

        myRef.child("staff").child("doctors").child(""+id).child("mPatients").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.v("DoctorActivity","Exists " + dataSnapshot.exists());
                Log.v("DoctorActivity","Has Children" + dataSnapshot.hasChildren());
                if(dataSnapshot.hasChildren()) {
                    Log.v("DoctorActivity","Number of Children" + dataSnapshot.getChildrenCount());
                }
                mPatientHash.putAll((Map<? extends String, ? extends Long>) dataSnapshot.getValue());
                coll = mPatientHash.values();
                mPatientIDs = new ArrayList<Long>(coll);
                for(Long a: mPatientIDs){
                    Log.v("Doctor","Next Patient Number: " + a);
                }
            }


            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }


}
