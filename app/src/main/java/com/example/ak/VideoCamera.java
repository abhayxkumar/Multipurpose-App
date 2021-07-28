package com.example.ak;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class VideoCamera extends AppCompatActivity {
    Button b;
    VideoView v;
    MediaController m;
    Uri u;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_camera);
        b = findViewById(R.id.button);
        v = findViewById(R.id.videoView);
        m = new MediaController(this);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
                startActivityForResult(i, 0);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0)
        {
            u = data.getData();
            v.setVideoURI(u);
            v.setMediaController(m);
            m.setAnchorView(v);
            v.start();
        }
        else
        {
            Toast.makeText(this, "Video is not recording!", Toast.LENGTH_SHORT).show();
        }
    }
}