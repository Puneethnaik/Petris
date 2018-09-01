package com.example.puneeth.petris;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Player extends GameObject{
    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    private int size;
    public Player(int id, int x, int y, Bitmap image){
        super(id, x, y, image);
        setType("Player");
        setRadius(40);
        size = 1;
    }
    public void update(){

        Bitmap nBitmap = Bitmap.createScaledBitmap(getImage(), (int)(150 + 150 * size * 0.01), (int)(120 + 120 * size * 0.01), true);
        setImage(nBitmap);
    }
}
