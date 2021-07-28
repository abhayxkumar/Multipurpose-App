package com.example.ak;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login_email extends AppCompatActivity {
    EditText e1,e2;
    ImageButton b1;
    TextView t1;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_email);
        EditText e1 = findViewById(R.id.editText3);
        EditText e2 = findViewById(R.id.editText4);
        ImageButton b1 = findViewById(R.id.button5);
        TextView t1 = findViewById(R.id.textView4);
        firebaseAuth = FirebaseAuth.getInstance();

        //For Email Login
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s1 = e1.getText().toString();
                String s2 = e2.getText().toString();
                if (s1.isEmpty())
                {
                    e1.setError("Please fill username");
                    return;
                }
                else
                {
                    if (s2.isEmpty())
                    {
                        e2.setError("Please fill password");
                        return;
                    }
                }
                firebaseAuth.createUserWithEmailAndPassword(s1,s2).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull @org.jetbrains.annotations.NotNull Task<AuthResult> task) {
                        if (task.isSuccessful())
                        {
                            Toast.makeText(Login_email.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                            Intent register = new Intent(Login_email.this, Welcome_screen.class);
                            startActivity(register);
                        }
                        else
                        {
                            Toast.makeText(Login_email.this, "Invalid email/password or you're already registered.", Toast.LENGTH_SHORT).show();
                        }
                    }
        });

        //For Register
        t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent register = new Intent(Login_email.this, Local_database.class);
                startActivity(register);
            }
        });
    }
});
    }}