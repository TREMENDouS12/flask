package com.example.navscrreens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CancelSuccessful extends AppCompatActivity {

    private Button BackHomescreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cancel_successful);

        BackHomescreen=findViewById(R.id.btnbackhomescreen);


        BackHomescreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CancelSuccessful.this,HomeScreen2.class));
                finish();
            }
        });

    }
}