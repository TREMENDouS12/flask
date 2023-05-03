package com.example.navscrreens;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {

    //Declared Fields


    private FirebaseAuth auth;
    private EditText loginEmail, loginPassword;
    private TextView signupredirectText;

    private Button loginButton, backbtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Linking fields to their right Edittext ,TextView, Buttons


        auth = FirebaseAuth.getInstance();
        loginEmail = findViewById(R.id.edtemaill);
        loginPassword = findViewById(R.id.edtpwdl);
        loginButton = findViewById(R.id.btnlogin);
        signupredirectText = findViewById(R.id.txtsignup);
        backbtn = findViewById(R.id.btnbackmain1);


        //When button login is clicked uses auth from firebase to check if the account exist

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = loginEmail.getText().toString();
                String pass = loginPassword.getText().toString();

                if (email.isEmpty()) {

                    //searches if the email field is empty

                    loginEmail.setError("Email Cannot be empty");

                } else if (!email.contains("dut")) {

                    //searches if the email field is contains a Dut Email Address

                    loginEmail.setError("Not a Dut Email");

                } else if (pass.isEmpty()) {

                    //searches password field if its empty

                    loginPassword.setError("Password is empty");

                } else if (!(pass.length() == 9)) {

                    //searched if the length of the entered password is equals to
                    loginPassword.setError("Password must be 9 characters");

                } else {

                    //if all the above statement is false then it executes the firebase auth code
                    if (Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                        auth.signInWithEmailAndPassword(email, pass)

                                //if the its successful will close the login activity and open the home screen for student and staff

                                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {

                                        if (task.isSuccessful()) {

                                            Toast.makeText(Login.this, "Login Successful", Toast.LENGTH_SHORT).show();
                                            startActivity(new Intent(Login.this, HomeScreen2.class));

                                        } else {

                                            Toast.makeText(Login.this, "Login Failed" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                        }


                                    }


                                });
                    }


                }


            }
        });

        //redirects the user to the signup screen once they have clicked on th text view

        signupredirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Login.this, SignUp.class));
                finish();

            }
        });

        //redirects the user to the main screen

        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Login.this, MainActivity.class));
                finish();
            }
        });


    }


}