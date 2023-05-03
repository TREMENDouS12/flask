package com.example.navscrreens;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUp extends AppCompatActivity {


    private FirebaseAuth auth;
    private EditText signupEmail, signupPassword;
    private Button signupButton,btnback;
    private TextView loginredirectText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        auth = FirebaseAuth.getInstance();
        signupEmail = findViewById(R.id.edtemail);
        signupPassword = findViewById(R.id.edtpwd);
        signupButton = findViewById(R.id.btnsignup);
        loginredirectText = findViewById(R.id.txtLogin);
        btnback=findViewById(R.id.btnbackmain2);


        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = signupEmail.getText().toString().trim();
                String pass = signupPassword.getText().toString().trim();

                if (user.isEmpty()) {

                    //searches if the email field is empty

                    signupEmail.setError("Email Cannot be empty");

                } else if (!user.contains("dut")) {

                    //searches if the email field is contains a Dut Email Address

                    signupEmail.setError("Not a Dut Email");

                } else if (pass.isEmpty()) {

                    //searches password field if its empty

                    signupPassword.setError("Password is empty");

                } else if (!(pass.length() == 9)) {

                    //searched if the length of the entered password is equals to
                    signupPassword.setError("Password must be 9 characters");

                } else {

                    //Passes the user email and password to the firebase auth to create an account

                    auth.createUserWithEmailAndPassword(user, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            //if the task was successful it makes toast message and redirects to the login screen

                            if (task.isSuccessful()) {

                                Toast.makeText(SignUp.this, "Sign Up Successful", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(SignUp.this, Login.class));

                            } else {

                                Toast.makeText(SignUp.this, "Sign Up Failed" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }

                        }
                    });
                }
            }



            });

       loginredirectText.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               startActivity(new Intent(SignUp.this, Login.class));
               finish();
           }
       });

       btnback.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {

               startActivity(new Intent(SignUp.this, MainActivity.class));
               finish();

           }
       });


        }
    }