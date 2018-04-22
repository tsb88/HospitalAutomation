package com.example.tejasbhoir.hospitalautomation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class OtherStaffActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_staff);

        Toast.makeText(getApplicationContext(), "This is working (Other page)", Toast.LENGTH_LONG).show();
    }
}
