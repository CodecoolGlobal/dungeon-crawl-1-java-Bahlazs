package com.codecool.dungeoncrawl.logic.entities;

import com.codecool.dungeoncrawl.logic.KeyHandler;
import com.codecool.dungeoncrawl.logic.MouseHandler;
import com.codecool.dungeoncrawl.util.Direction;
import com.codecool.dungeoncrawl.util.ImageLoader;

import java.awt.image.BufferedImage;

public class Player extends  Entity{

    private static final String CHARACTER_URL = "/player/Samurai.png";
    private static final String ARMORED_CHARACTER_URL = "/player/Samurai-armored.png";


    private final KeyHandler keyH;
    private final MouseHandler mouseH;

    private BufferedImage armoredImage;


    private double attackDuration;
    private boolean moving;


    public Player(int x, int y, int size, KeyHandler keyH, MouseHandler mouseH ) {
        super(CHARACTER_URL, x, y, 3, size);
        this.keyH = keyH;
        this.mouseH = mouseH;
        attackDuration = 0.7;
        moving = true;
        armoredImage = ImageLoader.loadImage(ARMORED_CHARACTER_URL);
    }

    public double getAttackDuration() {
        return attackDuration;
    }


    public boolean isMoving() {
        return moving;
    }

    @Override
    public void move() {
        if (keyH.isUp()){
            position.setY(position.getY() - speed);
            hitBox.y -= speed;
            direction = Direction.UP;
        }
        if (keyH.isDown()){
            position.setY(position.getY() + speed);
            hitBox.y += speed;
            direction = Direction.DOWN;

        }
        if (keyH.isRight()){
            position.setX(position.getX() + speed);
            hitBox.x += speed;
            direction = Direction.RIGHT;
        }
        if (keyH.isLeft()){
            position.setX(position.getX() - speed);
            hitBox.x -= speed;
            direction = Direction.LEFT;

        }

    }

    @Override
    public boolean attack() {
        if (mouseH.isButtonOnePressed()) {
            moving = false;
            stopMovement();
            if (!moving) {
                endAttack(mouseH.getAttackTime());
            }
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void endAttack(long attackTime) {
        if (attackTime + (attackDuration*1000) <= System.currentTimeMillis()) {
            mouseH.setAttacking(false);
            speed = 2;
            moving = true;

        }
    }
}
