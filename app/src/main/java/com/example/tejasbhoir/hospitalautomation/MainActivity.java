package com.example.tejasbhoir.hospitalautomation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        EditText ID = findViewById(R.id.ID);
        EditText password = findViewById(R.id.password);
        Button login = findViewById(R.id.login);

        final String id_input = ID.getText().toString();
        final String pass_input = password.getText().toString();

        checkDatabaseReference();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int size = staffList.size();
                for (int i = 0; i < size; i++) {
                    Staff staff = staffList.get(i);
                    String password = staff.getPassword();
                    String ID = staff.getID();
                    if (id_input == ID) {
                        if (pass_input == password) {
                            if (staff.getClass() == Doctor.class) {
                                // Start Doctor Activity
                                Intent intent = new Intent(getBaseContext(), DoctorActivity.class);
                                startActivity(intent);
                            }
                            else {
                                // Start Other Staff Activity
                                Intent intent = new Intent(getBaseContext(), OtherStaffActivity.class);
                                startActivity(intent);
                            }
                        }
                    }
                }
            }
        });

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
    }

        public void getDoctorList() {
            myRef.child("staff").child("doctors").addListenerForSingleValueEvent(new ValueEventListener() {
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
}

