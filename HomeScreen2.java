package com.example.navscrreens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.ktx.Firebase;

public class HomeScreen2 extends AppCompatActivity {

    private Button LogoutOfAcc,CancelAppointment,ViewAppointment,MakeAppointment,MedRecords;
    private TextView txtCurrentUser;

    private FirebaseAuth auth;

    private FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen2);
        LogoutOfAcc=findViewById(R.id.btnLogoutAcc);
        CancelAppointment=findViewById(R.id.btnCancelAppt);
        ViewAppointment=findViewById(R.id.btnViewAppt);
        MakeAppointment=findViewById(R.id.btnMakeAppt);
        txtCurrentUser=findViewById(R.id.txtvCurrentUser);
        MedRecords=findViewById(R.id.btnmedrecord);
        auth=FirebaseAuth.getInstance();
        user=auth.getCurrentUser();

        txtCurrentUser.setText(user.getEmail());

      //  Toast.makeText(HomeScreen2.this, "Login Successful", Toast.LENGTH_SHORT).show();


        LogoutOfAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(HomeScreen2.this,MainActivity.class));

            }
        });

        CancelAppointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeScreen2.this,Cancel.class));


            }
        });

        ViewAppointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeScreen2.this,ViewAppt.class));


            }
        });

        MakeAppointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeScreen2.this,Booking.class));

            }
        });

        MedRecords.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeScreen2.this, ViewMedRecords.class));

            }
        });

    }
}