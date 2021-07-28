package com.example.ak;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class BasicServices extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_services);
        ImageButton b1 = findViewById(R.id.button);
        ImageButton b2 = findViewById(R.id.button4);
        ImageButton b3 = findViewById(R.id.button5);
        ImageButton b4 = findViewById(R.id.button6);
        ImageButton b5 = findViewById(R.id.button7);
        ImageButton b6 = findViewById(R.id.button8);
        ImageButton b7 = findViewById(R.id.button9);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent tm = new Intent(BasicServices.this, TextToSpeech.class);
                startActivity(tm);
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent b = new Intent(BasicServices.this, Browser.class);
                startActivity(b);
            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent w = new Intent(BasicServices.this, WiFi.class);
                startActivity(w);
            }
        });

        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent bt = new Intent(BasicServices.this, Bluetooth.class);
                startActivity(bt);
            }
        });

        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent fl = new Intent(BasicServices.this, FlashLight.class);
                startActivity(fl);
            }
        });

        b6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cal = new Intent(BasicServices.this, Calculator.class);
                startActivity(cal);
            }
        });

        b7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent logout = new Intent(BasicServices.this, Login.class);
                logout.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(logout);
                finish();
            }
        });
    }
}