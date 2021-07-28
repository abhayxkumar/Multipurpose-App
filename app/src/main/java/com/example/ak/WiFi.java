package com.example.ak;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class WiFi extends AppCompatActivity {
    ImageButton b1;
    WifiManager wm;
    private boolean wifi = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wi_fi);
        wm = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        b1 = findViewById(R.id.imageButton);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!wifi)
                {
                    wm.setWifiEnabled(true);
                    wifi = true;
                    b1.setImageResource(R.drawable.wifion);

                }
                else
                {
                    wm.setWifiEnabled(false);
                    wifi = false;
                    b1.setImageResource(R.drawable.wifioff);
                }
            }
        });

    }
}