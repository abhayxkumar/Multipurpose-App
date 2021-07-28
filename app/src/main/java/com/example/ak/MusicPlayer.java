package com.example.ak;

import androidx.appcompat.app.AppCompatActivity;


import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;


public class MusicPlayer extends AppCompatActivity {
    ImageButton pp,h;
    MediaPlayer m;
    private boolean music = false;
    private boolean like = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_player);
        pp = findViewById(R.id.imageButton);
        h = findViewById(R.id.imageButton2);
        m = MediaPlayer.create(this,R.raw.anywhere);

        //For Play and Pause
        pp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!music)
                {
                    m.start();
                    pp.setImageResource(R.drawable.play);
                    music = true;
                }
                else
                {
                    m.pause();
                    pp.setImageResource(R.drawable.pause);
                    m.seekTo(0);
                    music = false;
                }
            }
        });

        //For Like
        h.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!like)
                {
                    h.setImageResource(R.drawable.redheart);
                    like = true;
                }
                else
                {
                    h.setImageResource(R.drawable.whiteheart);
                    like = false;
                }
            }
        });
    }
}