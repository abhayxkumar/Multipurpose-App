package com.example.ak;

import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothAdapter;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class Bluetooth extends AppCompatActivity {
    ImageButton ib;
    BluetoothAdapter ba;
    private boolean bluetooth = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bluetooth);
        ib = findViewById(R.id.imageButton);
        ba = BluetoothAdapter.getDefaultAdapter();

        ib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!bluetooth)
                {
                    ba.enable();
                    bluetooth = true;
                    ib.setImageResource(R.drawable.bluetoothon);
                }
                else
                {
                    ba.disable();
                    bluetooth = false;
                    ib.setImageResource(R.drawable.bluetoothoff);
                }
            }
        });
    }
}