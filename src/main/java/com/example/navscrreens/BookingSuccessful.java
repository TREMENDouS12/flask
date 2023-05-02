package com.example.navscrreens;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class BookingSuccessful extends AppCompatActivity {

    TextView dateDisplay,timeDisplay,campusDisplay;

    private Button redirecttohomescreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_successful);
        Intent intent = getIntent();
        String dateSuccess = intent.getStringExtra("dateShow");
        String timeSuccess = intent.getStringExtra("timeShow");
        String campusSuccess = intent.getStringExtra("campusShow");

        dateDisplay = findViewById(R.id.txtDateSuccess);
        timeDisplay = findViewById(R.id.txtTimeSuccess);
        campusDisplay = findViewById(R.id.txtCampusSuccess);
        redirecttohomescreen=findViewById(R.id.btnAgree);

        dateDisplay.setText(dateSuccess);
        timeDisplay.setText(timeSuccess);
        campusDisplay.setText(campusSuccess);

        redirecttohomescreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BookingSuccessful.this,HomeScreen2.class));
                finish();
            }
        });
    }
}