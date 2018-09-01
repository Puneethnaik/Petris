package com.example.puneeth.petris;

import android.graphics.Bitmap;

public class Particle extends GameObject {
    public Particle(int id, int x, int y, Bitmap image){
        super(id, x, y, image);
        setType("Particle");
        setRadius(50);
    }

}
