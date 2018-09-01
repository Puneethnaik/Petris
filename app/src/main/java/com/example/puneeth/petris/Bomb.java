package com.example.puneeth.petris;

import android.graphics.Bitmap;

public class Bomb extends GameObject {
    public Bomb(int id, int x, int y, Bitmap image){
        super(id, x, y, image);
        setType("Bomb");
        setRadius(50);
    }

}
