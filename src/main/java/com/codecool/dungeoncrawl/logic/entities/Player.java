package com.codecool.dungeoncrawl.logic.entities;

import com.codecool.dungeoncrawl.logic.KeyHandler;
import com.codecool.dungeoncrawl.logic.MouseHandler;
import com.codecool.dungeoncrawl.util.Direction;
import com.codecool.dungeoncrawl.util.ImageLoader;

import java.awt.image.BufferedImage;

public class Player extends  Entity{

    public static final String CHARACTER_URL = "/player/Samurai.png";
    public static final String ARMORED_CHARACTER_URL = "/player/Samurai-armored.png";

    public static final int PLAYER_BASE_SPEED = 3;


    private final KeyHandler keyH;
    private final MouseHandler mouseH;

    private BufferedImage armoredImage;


    private final double attackDuration;
    private boolean moving;


    public Player(int x, int y, int size, KeyHandler keyH, MouseHandler mouseH ) {
        super(CHARACTER_URL, x, y, PLAYER_BASE_SPEED, size);
        this.keyH = keyH;
        this.mouseH = mouseH;
        attackDuration = 0.7;
        moving = true;
        armoredImage = ImageLoader.loadImage(ARMORED_CHARACTER_URL);
    }

    public KeyHandler getKeyH() {
        return keyH;
    }

    public MouseHandler getMouseH() {
        return mouseH;
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
        else if (keyH.isDown()){
            position.setY(position.getY() + speed);
            hitBox.y += speed;
            direction = Direction.DOWN;

        }
        else if (keyH.isRight()){
            position.setX(position.getX() + speed);
            hitBox.x += speed;
            direction = Direction.RIGHT;
        }
        else if (keyH.isLeft()){
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
