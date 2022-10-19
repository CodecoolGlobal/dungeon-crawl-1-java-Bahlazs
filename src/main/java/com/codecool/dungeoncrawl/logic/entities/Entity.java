package com.codecool.dungeoncrawl.logic.entities;

import com.codecool.dungeoncrawl.util.Direction;
import com.codecool.dungeoncrawl.util.ImageLoader;
import com.codecool.dungeoncrawl.util.Position;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Entity {

    private static final int HIT_BOX_SIZE = 32;

    private static final int HIT_BOX_X_OFFSET = 8;

    private static final int HIT_BOX_Y_OFFSET = 16;

    protected int size;
    protected BufferedImage image;

    protected int speed;
    protected Direction direction;

    protected Position position;

    protected Rectangle hitBox;

    public Entity(String url, int x, int y, int speed, int size) {
        position = new Position(x,y);
        direction = Direction.DOWN;
        this.speed = speed;
        this.size = size;
        image = ImageLoader.loadImage(url);
        hitBox = new Rectangle(position.getX()+HIT_BOX_X_OFFSET, position.getY()+HIT_BOX_Y_OFFSET, HIT_BOX_SIZE, HIT_BOX_SIZE);

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


    public BufferedImage getImage() {
        return image;
    }

    public void stopMovement() {
        speed=0;
    }



    protected abstract void attack();
    protected abstract void endAttack(long time);

    public abstract void move();




}
