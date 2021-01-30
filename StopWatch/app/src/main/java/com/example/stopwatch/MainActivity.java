package com.example.stopwatch;

import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;

import android.widget.Chronometer;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Chronometer chronometer;
    private long playtime = 0;
    private boolean isPlaying = false;
    private ToggleButton playButton;
    private ImageView stopButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        chronometer = findViewById(R.id.chronometer);
        playButton = findViewById(R.id.play);
        stopButton = findViewById(R.id.stop);

        playButton.setText("");
        playButton.setTextOn("");
        playButton.setTextOff("");

        playButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean paused) {
                if (paused) {
                    chronometer.setBase(SystemClock.elapsedRealtime()- playtime);
                    chronometer.start();
                    isPlaying = true;
                } else {
                    chronometer.stop();
                    playtime = SystemClock.elapsedRealtime()- chronometer.getBase();
                    isPlaying = false;
                }
            }
        });

        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isPlaying) {
                    playButton.setChecked(false);
                    
                    chronometer.stop();
                    chronometer.setBase(SystemClock.elapsedRealtime());
                    playtime = 0;
                    isPlaying = false;
                }
            }
        });
    }
}