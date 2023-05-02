package com.example.navscrreens;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;



public class AddMedRecords extends AppCompatActivity {

    private Button btnSelectDate, btnAddRecord;
    private EditText edtPatient, edtPractitioner, edtIllness, edtMedicine;
    FirebaseFirestore firestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_med_records);

        btnSelectDate= findViewById(R.id.btnSelectDate2);
        btnAddRecord = findViewById(R.id.btnAddmr);
        edtPatient = findViewById(R.id.edtPatientEmail);
        edtIllness = findViewById(R.id.edtIllness);
        edtMedicine = findViewById(R.id.edtMedicine);
        edtPractitioner = findViewById(R.id.edtPractitionerName);

        final Calendar cal = Calendar.getInstance();
        final int year = cal.get(Calendar.YEAR);
        final int month = cal.get(Calendar.MONTH);
        final int day = cal.get(Calendar.DAY_OF_MONTH);

        btnSelectDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog dialog = new DatePickerDialog(AddMedRecords.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        month = month + 1;
                        String date = day + "/" + month + "/" + year;
                        btnSelectDate.setText(date);
                    }
                }, year, month, day);
                Calendar calendar = Calendar.getInstance();
                Date endDate = calendar.getTime();
                dialog.getDatePicker().setMaxDate(endDate.getTime());
                dialog.show();

            }
        });

        btnAddRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseFirestore db = FirebaseFirestore.getInstance();
                CollectionReference usersRef = db.collection("medRecords");
                Map<String, Object> medRecords = new HashMap<>();
                //check if empty fields
                if (edtIllness.getText().length()!=0 && edtPractitioner.getText().length()!=0 && edtPatient.getText().length()!=0 && btnSelectDate.getText() != "Enter date of check up"&& edtPatient.getText().toString().contains("dut")){
                    medRecords.put("StudentEmail", edtPatient.getText().toString());
                    medRecords.put("Practitioner", edtPractitioner.getText().toString());
                    medRecords.put("Date", btnSelectDate.getText().toString());
                    medRecords.put("Prescribed Medicine", edtMedicine.getText().toString());
                    medRecords.put("Illness", edtIllness.getText().toString());
                    //Pop up message
                    usersRef.add(medRecords).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_LONG).show();
                            startActivity(new Intent(AddMedRecords.this, AddMedRecordSuccessful.class));
                            finish();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getApplicationContext(), "Failed", Toast.LENGTH_LONG).show();
                        }
                    });
                    recreate();
                } else if (edtIllness.getText().length()==0) {
                    edtIllness.setError("Enter illness and cause");
                } else if (edtPatient.getText().length()==0) {
                    edtPatient.setError("Enter patient's dut email");
                } else if (edtPractitioner.getText().length()==0) {
                    edtPractitioner.setError("Enter practitioner's name");
                } else if (btnSelectDate.getText() == "Enter date of check up") {
                    btnSelectDate.setError("Enter date of check up");
                }
            }
        });
    }
}