package com.codecool.dungeoncrawl.logic.entities;

import com.codecool.dungeoncrawl.logic.Drawable;
import com.codecool.dungeoncrawl.logic.MouseHandler;
import com.codecool.dungeoncrawl.util.Direction;
import com.codecool.dungeoncrawl.util.ImageLoader;
import com.codecool.dungeoncrawl.util.Position;
import com.google.gson.annotations.Expose;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Entity implements Drawable {

    private static final int HIT_BOX_SIZE = 32;

    public static final int HIT_BOX_X_OFFSET = 16;

    public static final int HIT_BOX_Y_OFFSET = 32;
    protected int size;
    protected String imageUrl;

    protected int speed;

    protected Direction direction;
    @Expose
    protected Position position;

    protected Rectangle hitBox;

    protected Boolean collisionIsOn;

    public Entity(String url, int x, int y, int speed, int size) {
        position = new Position(x,y);
        direction = Direction.DOWN;
        this.speed = speed;
        this.size = size;
        imageUrl = url;
        hitBox = new Rectangle(position.getX()+HIT_BOX_X_OFFSET, position.getY()+HIT_BOX_Y_OFFSET, HIT_BOX_SIZE, HIT_BOX_SIZE);

    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public Rectangle getHitBox() {
        return hitBox;
    }

    public Direction getDirection() {
        return direction;
    }


    public Position getPosition() {
        return position;
    }


    public String getImageUrl() {
        return imageUrl;
    }

    public void stopMovement() {
        speed = 0;
    }


    public void setPayerPosByCollision(int x, int y) {
        position.setX(x);
        position.setY(y);
        hitBox.x = position.getX()+ HIT_BOX_X_OFFSET;
        hitBox.y = position.getY()+ HIT_BOX_Y_OFFSET;

    }
    public abstract void move();




}
