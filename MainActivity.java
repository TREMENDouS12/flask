package com.example.navscrreens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    //Field values created for buttons

    private Button clinicadminlogin;
    private Button stafstuddlogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        clinicadminlogin=findViewById(R.id.btnClinicAdminLogin);
        stafstuddlogin=findViewById(R.id.btnStaffStud);

        //redirects the user to the clinic admin login

        clinicadminlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,ClinicAdminLogin.class));
                finish();
            }
        });

        //redirects the user to the staff/students login

        stafstuddlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,Login.class));
                finish();
            }
        });

    }
}