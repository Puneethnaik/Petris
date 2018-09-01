package com.example.puneeth.petris;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.media.MediaPlayer;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameView extends SurfaceView implements Runnable {
    boolean playing;
    private Thread gameThread = null;
    private Canvas canvas;
    private Paint paint;
    private Player player;
    private int id;
    private ArrayList<GameObject> objects;
    private SurfaceHolder surfaceHolder;
    private int screenX, screenY;
    Context context;
    private long score;
    MediaPlayer sound;
    public GameView(Context context, int screenX, int screenY){
//        this.context = contextt;
        super(context);
        this.context = context;
        this.screenX = screenX;
        this.screenY = screenY;
        id = 1;
        Bitmap playerImage = BitmapFactory.decodeResource(getResources(), R.mipmap.player);

        player = new Player(id++, screenX / 2 - 110, screenY - 300, playerImage);
        paint = new Paint();
        surfaceHolder = getHolder();
        objects = new ArrayList<GameObject>();
        objects.add(player);

    }
    public void handleTouch(View v, MotionEvent e){
        player.setX((int)e.getX() - 150);
//        player.setY((int)e.getY() - 190);
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
//        player.setX((player.getX() + 1));
        player.update();
        if(player.getSize() >= 10){
            context.startActivity(new Intent(context, GameOverActivity.class));
        }
        if(objects.size() >= 11){

        }
        else{
            //add a new object
            Random random = new Random();
            Random random1 = new Random();
            int number = random.nextInt(random1.nextInt(50) + 1);
            GameObject object;
            if(number % 17 == 0){
                //if number is a 0 make a bomb
                Bitmap pBomb = BitmapFactory.decodeResource(context.getResources(), R.mipmap.bomb);
                object = new Bomb(id++, random.nextInt(screenX), random.nextInt(Math.max(5, player.getY() - 250)), pBomb);
            }
            else{
                //else make a particle
                Bitmap pParticle = BitmapFactory.decodeResource(context.getResources(), R.mipmap.particle);
                object = new Particle(id++, random.nextInt(screenX), random.nextInt(Math.max(5, player.getY() - 250)), pParticle);
            }
            objects.add(object);
        }

        for(int i = 0 ; i < objects.size(); i++){
            GameObject obj = objects.get(i);
            if(obj.getType().equals("Player")){
                //skip any update if the object is a player.
                continue;
            }
            if(obj.getY() >= screenY){
                objects.remove(i);
                i--;
            }
            else{
                obj.setY(obj.getY() + 5);
            }
        }

        //now perform collision detection
        for(int i = 0 ; i < objects.size(); i++){
            GameObject obj = objects.get(i);
            if(!obj.getType().equals("Player")){
                if(GameObject.isCollide(player, obj)){
                    if(obj.getType().equals("Particle")) {
                        Log.i("message", "Collided with Particle");
                        player.setSize(player.getSize() + 1);
                        player.setRadius(player.getRadius() + 1);
                        sound = MediaPlayer.create(context, R.raw.particle_sound);
                        sound.start();
                    }
                    else{
                        Log.i("message", "Collided with Bomb");
                        player.setSize(Math.max(player.getSize() - 5, 1));
                        player.setRadius(Math.max(player.getRadius() - 5, 40));
                        sound = MediaPlayer.create(context, R.raw.bomb_sound);
                        sound.start();
                    }
                    objects.remove(i);
                    i--;
                }
            }

        }


    }
    private void draw(){
        if(surfaceHolder.getSurface().isValid()){
            canvas = surfaceHolder.lockCanvas();
            canvas.drawColor(getResources().getColor(R.color.colorPrimary));
            for(GameObject obj: objects){
                canvas.drawBitmap(
                        obj.getImage(),
                        obj.getX(),
                        obj.getY(),
                        paint
                        );
            }
            surfaceHolder.unlockCanvasAndPost(canvas);
        }
    }
    private void control(){
        try{
            gameThread.sleep(17);
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }
    public void pause(){

        playing = false;
        try{
            gameThread.join();
        }
        catch(InterruptedException e){
            e.printStackTrace();
        }
    }
    public void resume(){
        playing = true;
        gameThread = new Thread(this);
        gameThread.start();
    }
}
