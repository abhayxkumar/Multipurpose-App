package com.example.ak;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class Local_database extends AppCompatActivity {
    EditText e1,e2,e3;
    ImageButton b1;
    TextView t1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_database);
        EditText e1 = findViewById(R.id.editText);
        EditText e2 = findViewById(R.id.editText2);
        EditText e3 = findViewById(R.id.editText3);
        ImageButton b1 = findViewById(R.id.button);
        TextView t1 = findViewById(R.id.textView4);

        t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent login = new Intent(Local_database.this, Login.class);
                startActivity(login);
            }
        });

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s1 = e1.getText().toString();
                String s2 = e2.getText().toString();
                String s3 = e3.getText().toString();

                if (s1.equals("")||s2.equals("")||s3.equals(""))
                {
                    Toast.makeText(Local_database.this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    SQLiteDatabase sql = openOrCreateDatabase("RegistrationData", MODE_PRIVATE, null);
                    sql.execSQL("create table if not exists student (name varchar, email varchar, password varchar)");
                    String s4 = "select * from student where name = '"+s1+"' and email = '"+s2+"'";
                    Cursor ca = sql.rawQuery(s4, null);
                    if(ca.getCount()>0)
                    {
                        Toast.makeText(Local_database.this, "User already exists", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        sql.execSQL("insert into student values ('"+s1+"', '"+s2+"', '"+s3+"')");
                        Toast.makeText(Local_database.this, "Registration complete, now sign in.", Toast.LENGTH_SHORT).show();
                        Intent m = new Intent(Local_database.this, Login.class);
                        startActivity(m);
                        finish();
                    }
                }
            }
        });
    }
}