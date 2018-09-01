package com.example.puneeth.petris;

import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;

public class GamePlayActivity extends AppCompatActivity {
    private GameView gameView;
    private int screenX, screenY;
    public void getScreenDimensions(){
        Display display = getWindowManager().getDefaultDisplay();
        Point point = new Point();
        display.getSize(point);
        screenX = point.x;
        screenY = point.y;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getScreenDimensions();

        Log.i("coord", Integer.toString(screenX) + " " + Integer.toString(screenY));
        gameView = new GameView(this, screenX, screenY);

        setContentView(gameView);
        gameView.run();
        gameView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                gameView.handleTouch(v, event);
                return true;
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        gameView.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        gameView.resume();
    }
}
