package com.example.puneeth.petris;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {
    MediaPlayer backgroundMusic;
    ImageButton play, exit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        getSupportActionBar().hide();
        backgroundMusic = MediaPlayer.create(this, R.raw.background_music);
        backgroundMusic.start();
        backgroundMusic.setLooping(true);
        play = findViewById(R.id.playButton);
        exit = findViewById(R.id.exitButton);
    }

    public void gamePlay(View view){
        startActivity(new Intent(MainActivity.this, GamePlayActivity.class));

    }
    public void exit(View view){
        finish();
    }

    @Override
    protected void onPause() {
        super.onPause();
        backgroundMusic.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        backgroundMusic.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        backgroundMusic.stop();
    }
}
