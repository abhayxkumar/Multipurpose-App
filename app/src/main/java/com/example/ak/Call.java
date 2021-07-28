package com.example.ak;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class Call extends AppCompatActivity {
    EditText e1;
    ImageButton b1,b2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call);
        e1 = findViewById(R.id.editText);
        b1 = findViewById(R.id.button);
        b2 = findViewById(R.id.button2);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M)
                {
                    if(checkSelfPermission(Manifest.permission.CALL_PHONE)== PackageManager.PERMISSION_GRANTED)
                    {
                        Call();
                    }
                    else
                    {
                        requestPermissions(new String[]{Manifest.permission.CALL_PHONE},1);
                    }
                }
                else
                {
                    Toast.makeText(Call.this,"This call can't be proceeded", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //Logout
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent logout = new Intent(Call.this, Login.class);
                logout.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(logout);
                finish();
            }
        });
    }

    public void Call() {
        String s = e1.getText().toString().trim();
        if (s.length()==10) {
            Intent i = new Intent(Intent.ACTION_CALL);
            i.setData(Uri.parse("tel:"+s));
            startActivity(i);
        }
        else {
            e1.setError("Invalid Phone Number");
            return;
        }
    }
}