package com.example.puneeth.petris;

import android.graphics.Bitmap;

public class GameObject {
    private int id;
    private String type;
    private int radius;

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public int getRadius() {
        return radius;
    }
    public float getDistance(GameObject obj){
        float distance = (float)Math.sqrt((obj.getX() - (this.getX() + 30)) * (obj.getX() - (this.getX() + 30)) + (obj.getY() - (this.getY() + 30)) * (obj.getY() - (this.getY() + 30)));
        return distance;
    }
    public static boolean isCollide(GameObject obj1, GameObject obj2){
        //returns true if obj1 collides with the obj2
        boolean result = false;
        return(obj1.getDistance(obj2) < obj1.getRadius() + obj2.getRadius());
    }
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

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
