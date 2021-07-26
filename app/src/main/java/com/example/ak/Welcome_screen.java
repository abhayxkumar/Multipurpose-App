package com.example.ak;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.firebase.auth.EmailAuthCredential;
import com.google.firebase.auth.FirebaseAuth;

public class Welcome_screen extends AppCompatActivity {
    ImageButton b1,b2,b3,b4,b5,b6;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);
        ImageButton b1 = findViewById(R.id.button);
        ImageButton b2 = findViewById(R.id.button2);
        ImageButton b3 = findViewById(R.id.button3);
        ImageButton b4 = findViewById(R.id.button4);
        ImageButton b5 = findViewById(R.id.button5);
        ImageButton b6 = findViewById(R.id.button6);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent bs = new Intent(Welcome_screen.this, BasicServices.class);
                startActivity(bs);
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mp = new Intent(Welcome_screen.this, MusicPlayer.class);
                startActivity(mp);
            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent c = new Intent(Welcome_screen.this, Camera.class);
                startActivity(c);
            }
        });

        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent of = new Intent(Welcome_screen.this, OtherFeatures.class);
                startActivity(of);
            }
        });

        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent f = new Intent(Welcome_screen.this, Feedback.class);
                startActivity(f);
            }
        });

        b6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent logout = new Intent(Welcome_screen.this, Login.class);
                logout.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(logout);
                finish();
            }
        });

    }
}