package com.example.ak;

import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class FlashLight extends AppCompatActivity {
    ImageButton ib;
    CameraManager cm;
    private boolean camera = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flash_light);
        ib = findViewById(R.id.imageButton);
        cm = (CameraManager)getSystemService(CAMERA_SERVICE);
        ib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (camera == false)
                {
                    try
                    {
                        String s1=cm.getCameraIdList()[0];
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            cm.setTorchMode(s1, true);
                        }
                        camera = true;
                        ib.setImageResource(R.drawable.flashon);
                    }
                    catch (CameraAccessException e)
                    {

                    }
                }
                else
                {
                    try
                    {
                        String s2=cm.getCameraIdList()[0];
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            cm.setTorchMode(s2, false);
                        }
                        camera = false;
                        ib.setImageResource(R.drawable.flashoff);
                    }
                    catch (CameraAccessException ignored)
                    {

                    }
                }
            }
        });
    }
}