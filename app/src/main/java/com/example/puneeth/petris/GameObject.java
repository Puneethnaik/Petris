package com.example.puneeth.petris;

import android.graphics.Bitmap;

public class GameObject {
    private int id;
    private int x, y;
    private Bitmap image;
    public GameObject(){

    }
    public GameObject(int id, int x, int y, Bitmap image){
        this.id = id;
        this.x = x;
        this.y = y;
        this.image = image;
    }


}
