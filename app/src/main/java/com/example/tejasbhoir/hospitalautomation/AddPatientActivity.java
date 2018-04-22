package com.example.tejasbhoir.hospitalautomation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Random;

public class AddPatientActivity extends AppCompatActivity {
    String patientName;
    int patientAge;
    String patientGender;
    int patientID;

    String genders[] = {"m","f"};
    Patient inputPatient;

    FirebaseDatabase db = FirebaseDatabase.getInstance();
    DatabaseReference myRef = db.getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_patient);

        final EditText inputName = findViewById(R.id.editText_patientname);
        final EditText inputAge = findViewById(R.id.editText_patientage);
        Spinner inputGenderSpinner = findViewById(R.id.spinner_gender);
        //Log.v("test", "MADE IT");

        Button addPatientButton = findViewById(R.id.button_addpatientdb);
        addPatientButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                patientName = inputName.getText().toString();
                patientAge = Integer.parseInt(inputAge.getText().toString());
                patientID = randomID();

                inputPatient = new Patient();
                inputPatient.setmName(patientName);
                inputPatient.setmAge(patientAge);
                inputPatient.setmGender(patientGender);
                inputPatient.setmID(patientID);


                myRef = myRef.child("patients");
                myRef.child(""+patientID).setValue(inputPatient);

            }
        });

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, genders);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        inputGenderSpinner.setAdapter(adapter);
        inputGenderSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        patientGender = parent.getItemAtPosition(position).toString();
                        break;
                    case 1:
                        patientGender = parent.getItemAtPosition(position).toString();
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    public int randomID() {
        Random rand = new Random();

        return rand.nextInt(99999) + 1;
    }

}
