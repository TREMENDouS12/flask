package com.example.navscrreens;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class Booking extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    Spinner spnCampus;
    Spinner spnTime;
    final String[] timeSlots = {"8:30", "9:00", "9:30", "10:00", "10:30", "11:00", "11:30", "12:00", "12:30", "13:00", "13:30", "14:00", "14:30", "15:00", "15:30", "16:00", "16:30", "17:00"};

    public String[] newTimes = {"", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""};
    ArrayAdapter<String> spinnerArrayAdapter;
    private Button btnSelectDate;
    private Button btnBook;

    private String dateSuccess, timeSuccess, campusSuccess;

    FirebaseFirestore firestore;

    private TextView txtVBCuser;

    private FirebaseAuth auth;

    private FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);
        btnSelectDate = findViewById(R.id.btnSelectDate);
        btnBook = findViewById(R.id.btnBook);
        txtVBCuser = findViewById(R.id.txtBuser);
        auth=FirebaseAuth.getInstance();
        user=auth.getCurrentUser();

        txtVBCuser.setText(user.getEmail());


        final Calendar cal = Calendar.getInstance();
        final int year = cal.get(Calendar.YEAR);
        final int month = cal.get(Calendar.MONTH);
        final int day = cal.get(Calendar.DAY_OF_MONTH);
        /*Time slot code
        spnTime = findViewById(R.id.spnTimeSlot);
        spinnerArrayAdapter = new ArrayAdapter<>(Booking.this,
                android.R.layout.simple_spinner_dropdown_item, timeSlots);
        spnTime.setAdapter(spinnerArrayAdapter);*/

        //Time slot code
        //Collections.sort(newTimes);
        spnTime = findViewById(R.id.spnTimeSlot);
        spinnerArrayAdapter = new ArrayAdapter<>(Booking.this,
                android.R.layout.simple_spinner_dropdown_item, newTimes);
        spnTime.setAdapter(spinnerArrayAdapter);

        //Button select date
        btnSelectDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog dialog = new DatePickerDialog(Booking.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        month = month + 1;
                        String date = day + "/" + month + "/" + year;
                        btnSelectDate.setText(date);

                        //Show available time slots for the day
                        FirebaseFirestore db = FirebaseFirestore.getInstance();
                        CollectionReference usersRef = db.collection("users");
                        //Code to count entries per time slot
                        for (int l=0; l<18;l++){
                            Query query3 = usersRef.whereEqualTo("Date", btnSelectDate.getText().toString()).whereEqualTo("Campus", spnCampus.getSelectedItem().toString()).whereEqualTo("Time", timeSlots[l]);
                            int finalL = l;
                            query3.get().addOnCompleteListener(task3 -> {
                                if (task3.isSuccessful()){
                                    int NumEntries3 = task3.getResult().size();
                                    if (NumEntries3 < 2){
                                        newTimes[finalL] = timeSlots[finalL];
                                    }
                                }
                            });
                        }
                    }
                }, year, month, day);
                Calendar calendar = Calendar.getInstance();

                // Start tomorrow
                calendar.add(Calendar.DAY_OF_YEAR, 1);
                Date startDate = calendar.getTime();

                // 1 week from tomorrow
                calendar.add(Calendar.DAY_OF_YEAR, 7);
                Date endDate = calendar.getTime();
                dialog.getDatePicker().setMinDate(startDate.getTime());
                dialog.getDatePicker().setMaxDate(endDate.getTime());
                dialog.show();

            }
        });

        //Get Campus
        spnCampus = findViewById(R.id.spnCampus);

        //Database code

        btnBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Check if the button was clicked and display a toast message if it wasn't
                FirebaseFirestore db = FirebaseFirestore.getInstance();
                CollectionReference usersRef = db.collection("users");
                //Code to count entries per user
                Query query = usersRef.whereEqualTo("Date", btnSelectDate.getText().toString()).whereEqualTo("UserEmail",  txtVBCuser.getText().toString());
                // Query for not allowing a user to book twice in one day
                query.get().addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        int numEntries = task.getResult().size();
                        //Code to make sure no more than 2 entries per time slot
                        //Code to count entries per user
                        Query query2 = usersRef.whereEqualTo("Time", spnTime.getSelectedItem().toString()).whereEqualTo("Date", btnSelectDate.getText().toString()).whereEqualTo("Campus", spnCampus.getSelectedItem().toString());
                        // Query for not allowing a user to book twice in one day
                        query2.get().addOnCompleteListener(task2 -> {
                            if (task2.isSuccessful()) {
                                int numEntries2 = task2.getResult().size();
                                if (numEntries < 1 && numEntries2 < 2 && !(btnSelectDate.getText().toString().equals("Select a date to book"))) {
                                    Map<String, Object> user = new HashMap<>();
                                    user.put("UserEmail",txtVBCuser.getText().toString());
                                    user.put("Campus", spnCampus.getSelectedItem().toString());
                                    user.put("Date", btnSelectDate.getText().toString());
                                    user.put("Time", spnTime.getSelectedItem().toString());
                                    newTimes = null;

                                    //Pop up message
                                    usersRef.add(user).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                        @Override
                                        public void onSuccess(DocumentReference documentReference) {
                                            //Store temp variables for next activity
                                            dateSuccess = btnSelectDate.getText().toString();
                                            timeSuccess = spnTime.getSelectedItem().toString();
                                            campusSuccess = spnCampus.getSelectedItem().toString();
                                            //End for storing variables
                                            Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_LONG).show();
                                            Intent intent = new Intent(Booking.this, BookingSuccessful.class);
                                            intent.putExtra("dateShow", dateSuccess);
                                            intent.putExtra("timeShow", timeSuccess);
                                            intent.putExtra("campusShow", campusSuccess);

                                            startActivity(intent);//Go to success screen
                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Toast.makeText(getApplicationContext(), "Failed to add booking", Toast.LENGTH_LONG).show();
                                        }
                                    });
                                    recreate();
                                    // Person has no entries for the specified date and has selected a date
                                } else if (btnSelectDate.getText().toString().equals("Select a date to book")){
                                    Toast.makeText(getApplicationContext(), "Please select a date", Toast.LENGTH_SHORT).show();
                                } else if (numEntries2 >= 2) {
                                    // Person has 1 entry for the specified date
                                    Toast.makeText(getApplicationContext(), "This time slot is not available anymore, please select another.", Toast.LENGTH_LONG).show();

                                    recreate();
                                } else if (numEntries >= 1) {
                                    // Person has 1 entry for the specified date
                                    Toast.makeText(getApplicationContext(), "You already made an entry for today.", Toast.LENGTH_LONG).show();
                                    recreate();
                                } else if(spnTime.getSelectedItem().toString() == ""){
                                    Toast.makeText(getApplicationContext(), "This time slot isn't available. Please choose another.", Toast.LENGTH_LONG).show();
                                    recreate();
                                }
                            }
                        });
                    }
                });
            }

        });
    }

}