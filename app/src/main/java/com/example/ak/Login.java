package com.example.ak;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.annotations.NotNull;

public class Login extends AppCompatActivity {
    EditText e1,e2;
    ImageButton b1,b2,b3,b4;
    TextView t1;
    FirebaseAuth firebaseAuth;
    GoogleSignInClient googleSignInClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        EditText e1 = findViewById(R.id.editText);
        EditText e2 = findViewById(R.id.editText2);
        ImageButton b1 = findViewById(R.id.button);
        ImageButton b2 = findViewById(R.id.button2);
        ImageButton b3 = findViewById(R.id.button3);
        ImageButton b4 = findViewById(R.id.button4);
        TextView t1 = findViewById(R.id.textView4);

        //For Local Database Login
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s1 = e1.getText().toString();
                String s2 = e2.getText().toString();
                if (s1.equals("") || s2.equals(""))
                {
                    Toast.makeText(Login.this, "Please Fill Both Fields", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    SQLiteDatabase sql = openOrCreateDatabase("RegistrationData", MODE_PRIVATE, null);
                    sql.execSQL("create table if not exists student (email varchar, password varchar)");
                    String s4 = "select * from student where email= '"+s1+"' and password= '"+s2+"'";
                    Cursor ca = sql.rawQuery(s4, null);
                    if(ca.getCount()>0)
                    {
                        Toast.makeText(Login.this, "Login Complete", Toast.LENGTH_SHORT).show();
                        Intent k = new Intent(Login.this, Welcome_screen.class);
                        startActivity(k);
                        finish();
                    }
                    else
                    {
                        Toast.makeText(Login.this, "You are not registered! Please register first.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        //For Register
        t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent register = new Intent(Login.this, Local_database.class);
                startActivity(register);
            }
        });

        //For Email Login
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent email = new Intent(Login.this, Login_email.class);
                startActivity(email);
            }
        });

        //For OTP Login
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent otp = new Intent(Login.this, Otp_one.class);
                startActivity(otp);
            }
        });

        //For Google Login
        firebaseAuth = FirebaseAuth.getInstance();
        GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestIdToken("822868001599-kvu39bm3oa4d2nbfre3g6rhoglh8cger.apps.googleusercontent.com").requestEmail().build();
        googleSignInClient = GoogleSignIn.getClient(Login.this,googleSignInOptions);
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = googleSignInClient.getSignInIntent();
                startActivityForResult(i, 99);
            }
        });
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        if(firebaseUser!=null)
        {
            Intent j = new Intent(Login.this,Welcome_screen.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(j);
            finish();
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==99)
        {
            Task<GoogleSignInAccount> signInAccountTask = GoogleSignIn.getSignedInAccountFromIntent(data);
            if(signInAccountTask.isSuccessful())
            {
                Toast.makeText(this, "Google SignIn Done", Toast.LENGTH_SHORT).show();
                try {
                    GoogleSignInAccount googleSignInAccount = signInAccountTask.getResult(ApiException.class);
                    if (googleSignInAccount != null)
                    {
                        AuthCredential authCredential = GoogleAuthProvider.getCredential(googleSignInAccount.getIdToken(), null);
                        firebaseAuth.signInWithCredential(authCredential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull @NotNull Task<AuthResult> task) {
                                if(task.isSuccessful())
                                {
                                    Toast.makeText(Login.this, "Firebase Updated", Toast.LENGTH_SHORT).show();
                                    Intent m = new Intent(Login.this,Welcome_screen.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                    startActivity(m);
                                    finish();
                                }
                                else
                                {
                                    Toast.makeText(Login.this, "Firebase not Updated", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                }catch (ApiException e)
                {
                    e.printStackTrace();
                }
            }
        }
    }
}