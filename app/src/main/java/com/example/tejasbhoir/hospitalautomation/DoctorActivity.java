package com.example.tejasbhoir.hospitalautomation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import java.util.List;

public class DoctorActivity extends AppCompatActivity {

    ListView patientList;
    TextView recentMessage;

    List<Patient> dbPatientList = new ArrayList<>();

    FirebaseDatabase db = FirebaseDatabase.getInstance();
    DatabaseReference myRef = db.getReference();

    Doctor current = new Doctor();
    String id = current.getID();
    String recentDBMessage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor);

        Toast.makeText(getApplicationContext(), "This is working (Doctor Page)", Toast.LENGTH_LONG).show();

        patientList = findViewById(R.id.patientList);
        recentMessage = findViewById(R.id.recentMessage);

        checkDatabaseReference();

        recentMessage.setText(recentDBMessage);

        ArrayAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dbPatientList);
        patientList.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }

    Query lastQuery = myRef.child("messages").child("doctors").child("ID").child(id).orderByKey().limitToLast(1);
    public void checkDatabaseReference() {
        lastQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChild("messages")) {
                    if (dataSnapshot.child("messages").child("doctors").child("ID").hasChild(id)) {
                        recentDBMessage = dataSnapshot.child("message").getValue().toString();
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        Query query = myRef.child("staff").child("doctors").orderByChild("ID").startAt(id);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChild("staff")) {
                    if (dataSnapshot.child("staff").child("doctors").hasChildren()) {
                        getPatientList();
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void getPatientList() {
        myRef.child("staff").child("doctors").orderByChild("ID").startAt(id).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    dbPatientList.add(postSnapshot.getValue(Patient.class));
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
