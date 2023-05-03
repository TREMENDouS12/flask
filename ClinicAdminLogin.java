package com.example.navscrreens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


public class ClinicAdminLogin extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    String[] campuses = {"Steve Biko", "Ritson",
            "ML Sultan", "City Campus",
            "Midlands", "iNdumiso", "Riverside"};

    public static final String EXTRA_NAME="selectedCampus";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clinic_admin_login);

        Spinner spinnerCampus = findViewById(R.id.Campus_dropdown);
        spinnerCampus.setOnItemSelectedListener(this);
        EditText password = findViewById(R.id.edtpwdcal);

        Button loginbtn = findViewById(R.id.btnlogincal);


        ArrayAdapter ad
                = new ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item,
                campuses);

        ad.setDropDownViewResource(
                android.R.layout
                        .simple_spinner_dropdown_item);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.campus, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinnerCampus.setAdapter(adapter);


        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String selectedCampus;

                selectedCampus = spinnerCampus.getSelectedItem().toString();


                if ((campuses[0] == "Steve Biko") && password.getText().toString().equals("SteveB#DUT")) {
                    //correct
                    Toast.makeText(ClinicAdminLogin.this, "LOGIN SUCCESSFUL", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), HomScreen1.class);
                    intent.putExtra("selectedCampus", selectedCampus);
                    startActivity(intent);
                    finish();
                } else if ((campuses[1] == "Ritson") && password.getText().toString().equals("Ritson#DUT")) {
                    //correct
                    Toast.makeText(ClinicAdminLogin.this, "LOGIN SUCCESSFUL", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), HomScreen1.class);
                    intent.putExtra("selectedCampus", selectedCampus);
                    startActivity(intent);
                    finish();
                } else if ((campuses[2] == "ML Sultan") && password.getText().toString().equals("MLS#DUT")) {
                    //correct
                    Toast.makeText(ClinicAdminLogin.this, "LOGIN SUCCESSFUL", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), HomScreen1.class);
                    intent.putExtra("selectedCampus", selectedCampus);
                    startActivity(intent);
                    finish();
                } else if ((campuses[3] == "City Campus") && password.getText().toString().equals("CityC#DUT")) {
                    //correct
                    Toast.makeText(ClinicAdminLogin.this, "LOGIN SUCCESSFUL", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), HomeScreen2.class);
                    intent.putExtra("selectedCampus", selectedCampus);
                    startActivity(intent);
                    finish();
                } else if ((campuses[4] == "Midlands") && password.getText().toString().equals("Midlands#DUT")) {
                    //correct
                    Toast.makeText(ClinicAdminLogin.this, "LOGIN SUCCESSFUL", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), HomeScreen2.class);
                    intent.putExtra("selectedCampus", selectedCampus);
                    startActivity(intent);
                    finish();
                } else if ((campuses[5] == "iNdumiso") && password.getText().toString().equals("iNdumiso#DUT")) {
                    //correct
                    Toast.makeText(ClinicAdminLogin.this, "LOGIN SUCCESSFUL", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), HomeScreen2.class);
                    intent.putExtra("selectedCampus", selectedCampus);
                    startActivity(intent);
                    finish();
                } else if ((campuses[6] == "Riverside") && password.getText().toString().equals("Riverside#DUT")) {
                    //correct
                    Toast.makeText(ClinicAdminLogin.this, "LOGIN SUCCESSFUL", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), HomeScreen2.class);
                    intent.putExtra("selectedCampus", selectedCampus);
                    startActivity(intent);
                    finish();
                } else if (password.getText().toString().trim().isEmpty()) {
                    Toast.makeText(ClinicAdminLogin.this, "Password field cannot be empty", Toast.LENGTH_SHORT).show();
                    password.setText("");
                    return;
                } else
                    //incorrect
                    Toast.makeText(ClinicAdminLogin.this, "Incorrect Password!!!", Toast.LENGTH_SHORT).show();
                password.setText("");
                return;

            }
        });


    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
        // make toast of name of campus
        // which is selected in spinner
        selectedCampus = campuses[position];
        Toast.makeText(getApplicationContext(),
                        selectedCampus,
                        Toast.LENGTH_LONG)
                .show();
        Intent intent = new Intent(ClinicAdminLogin.this,ViewAllAppt.class);
        intent.putExtra(EXTRA_NAME,selectedCampus);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    private String selectedCampus;
}




