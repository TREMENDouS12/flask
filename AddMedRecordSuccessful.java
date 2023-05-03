package com.example.navscrreens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AddMedRecordSuccessful extends AppCompatActivity {

    private Button btnAddrec;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_med_record_successful);

        btnAddrec=findViewById(R.id.btnbackhm);

        btnAddrec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AddMedRecordSuccessful.this,HomScreen1.class));
                finish();
            }
        });

    }
}