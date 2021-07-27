package com.example.ak;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

import java.util.Locale;

public class TextToSpeech extends AppCompatActivity {
    EditText e1;
    ImageButton b1,b2,b3;
    android.speech.tts.TextToSpeech tts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_to_speech);
        e1 = (EditText)findViewById(R.id.editText);
        b1 = (ImageButton)findViewById(R.id.button);
        b2 = (ImageButton)findViewById(R.id.button2);
        b3 = (ImageButton)findViewById(R.id.button3);
        tts = new android.speech.tts.TextToSpeech(this, new android.speech.tts.TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                tts.setLanguage(Locale.ENGLISH);
                tts.setSpeechRate(0.7f);
            }
        });

        //Test-to-Speech
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s1 = e1.getText().toString();
                tts.speak(s1, android.speech.tts.TextToSpeech.QUEUE_FLUSH, null);
            }
        });

        //Back to Welcome Screen
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ws = new Intent(TextToSpeech.this, Welcome_screen.class);
                startActivity(ws);
            }
        });

        //Logout
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent logout = new Intent(TextToSpeech.this, Login.class);
                logout.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(logout);
                finish();
            }
        });
    }
}