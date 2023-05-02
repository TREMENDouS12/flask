package com.example.navscrreens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import  javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import java.security.Policy;
import java.util.Properties;


public class Cancel extends AppCompatActivity {


    FirebaseAuth auth;

    FirebaseUser user;
    EditText txtMessage;
    Button btnSendEmail;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cancel);

        auth=FirebaseAuth.getInstance();
        user=auth.getCurrentUser();

        txtMessage=findViewById(R.id.edtMessage);
        btnSendEmail=findViewById(R.id.btnsend);

        btnSendEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String username = "d039d3bd21af40";
                final String passwrod = "67abbb432a5410";
                String messageToSend = txtMessage.getText().toString();
                String SenderEmail=user.getEmail();
                Properties props = new Properties();
                props.put("mail.smtp.auth", "true");
                props.put("mail.smtp.starttls.enable", "true");
                props.put("mail.smtp.host", "sandbox.smtp.mailtrap.io");
                props.put("mail.smtp.port", "2525");

                Session session = Session.getInstance(props,
                        new javax.mail.Authenticator() {
                            @Override
                            protected PasswordAuthentication getPasswordAuthentication() {
                                return new PasswordAuthentication(username, passwrod);

                            }


                        });

                try{
                    Message message = new MimeMessage(session);
                    message.setFrom(new InternetAddress(username));
                    message.setRecipients(Message.RecipientType.TO,InternetAddress.parse("shavanamanilall@gmail.com"));
                    message.setSubject("Sending email without opening gmail apps");
                    message.setText(messageToSend+SenderEmail);
                    Transport.send(message);
                    Toast.makeText(getApplication(),"email send successfully",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Cancel.this,CancelSuccessful.class));


                }catch (MessagingException e){
                    throw new RuntimeException(e);
                }


            }

        });

        StrictMode.ThreadPolicy policy= new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

    }



}

