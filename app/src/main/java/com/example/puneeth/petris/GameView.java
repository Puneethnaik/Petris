package com.example.puneeth.petris;

import android.content.Context;
import android.view.SurfaceView;

public class GameView extends SurfaceView implements Runnable {
    boolean playing;
    private Thread gameThread = null;
    public GameView(Context context){
        super(context);
    }
    @Override
    public void run() {
        while(playing){
            update();
            draw();
            control();
        }
    }
    private void update(){

    }
    private void draw(){

    }
    private void control(){
        try{
            gameThread.sleep(17);
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }
    public void pause(){
        try{
            gameThread.join();
        }
        catch(InterruptedException e){
            e.printStackTrace();
        }
        playing = false;
    }
    public void resume(){
        playing = true;
        gameThread = new Thread(this);
        gameThread.start();
    }
}
