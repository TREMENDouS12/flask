package com.example.navscrreens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomScreen1 extends AppCompatActivity {

    private Button buttonViewallappt,buttonlogout,buttonaddmedrecords;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hom_screen1);

        buttonViewallappt=findViewById(R.id.btnViewAllAppt);
        buttonlogout=findViewById(R.id.btnLogout);
        buttonaddmedrecords=findViewById(R.id.btnMakeMedRecord);

        buttonViewallappt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomScreen1.this,ViewAllAppt.class));

            }
        });

        buttonlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomScreen1.this,ClinicAdminLogin.class));
                finish();

            }
        });

        buttonaddmedrecords.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomScreen1.this,AddMedRecords.class));

            }
        });
    }
}