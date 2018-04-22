package com.example.tejasbhoir.hospitalautomation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    FirebaseDatabase db = FirebaseDatabase.getInstance();
    DatabaseReference myRef = db.getReference();
    List<Staff> staffList = new ArrayList<>();
    String id_input;
    String pass_input;
    boolean notFound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final EditText ID = findViewById(R.id.ID);
        final EditText password = findViewById(R.id.password);
        Button login = findViewById(R.id.login);

        notFound = false;

        checkDatabaseReference();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int size = staffList.size();
                id_input = ID.getText().toString();
                pass_input = password.getText().toString();
                for (int i = 0; i < size; i++) {
                    Staff staff = staffList.get(i);
                    String password = staff.getPassword();
                    String ID = staff.getID();
                    Log.v("Data", ID);
                    Log.v("Input", id_input);
                    Log.v("OnClick", "Outside of If Loops");
                    if (id_input.equals(ID)) {
                        Log.v("OnClick", "First If Loop");
                        if (pass_input.equals(password)) {
                            Log.v("OnClick", "Second If Loop");
                            if (staff.getClass() == Doctor.class) {
                                // Start Doctor Activity
                                Log.v("OnClick", "Third If Loop");
                                Intent intent = new Intent(getBaseContext(), DoctorActivity.class);
                                intent.putExtra("ID", staff.getID());
                                startActivity(intent);
                            }
                            else if (staff.getClass() == Admin.class){
                                // Start Admin Activity
                                Log.v("OnClick", "Third If Loop");
                                Intent intent = new Intent(getBaseContext(), AdminActivity.class);
                                startActivity(intent);
                            }
                            else {
                                Intent intent = new Intent(getBaseContext(), OtherStaffActivity.class);
                                startActivity(intent);
                            }
                        }
                    }
                    else
                        notFound = true;
                }
            }
        });

        if (notFound){
            Toast.makeText(getApplicationContext(), "Try Again", Toast.LENGTH_LONG).show();
        }

    }

    public void checkDatabaseReference() {
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChild("staff")) {
                    if (dataSnapshot.child("staff").hasChild("doctors")) {
                        getDoctorList();
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChild("staff")){
                    if(dataSnapshot.child("staff").hasChild("other")){
                        getOtherList();
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChild("staff")){
                    if(dataSnapshot.child("staff").hasChild("admin")){
                        getAdminList();
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

        public void getDoctorList() {
            myRef.child("staff").child("doctors").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        staffList.add(postSnapshot.getValue(Doctor.class));
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }

        public void getOtherList() {
            myRef.child("staff").child("other").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        staffList.add(postSnapshot.getValue(Staff.class));
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }

    public void getAdminList() {
        myRef.child("staff").child("admin").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    staffList.add(postSnapshot.getValue(Admin.class));
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}

