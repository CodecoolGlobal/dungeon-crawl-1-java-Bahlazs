package com.codecool.dungeoncrawl.logic.entities;

import com.codecool.dungeoncrawl.logic.KeyHandler;
import com.codecool.dungeoncrawl.logic.MouseHandler;
import com.codecool.dungeoncrawl.main.Game;
import com.codecool.dungeoncrawl.util.Direction;
import com.codecool.dungeoncrawl.util.ImageLoader;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class Player extends  Entity{

    private static final String CHARACTER_URL = "/player/Samurai.png";
    private static final String ARMORED_CHARACTER_URL = "/player/Samurai-armored.png";


    private final KeyHandler keyH;
    private final MouseHandler mouseH;

    private BufferedImage armoredImage;
    private int speed;

    private double attackDuration;
    private boolean moving;


    public Player(int x, int y, int size, KeyHandler keyH, MouseHandler mouseH ) {
        super(CHARACTER_URL, x, y, size);
        this.keyH = keyH;
        this.mouseH = mouseH;
        speed = 2;
        attackDuration = 0.7;
        moving = true;
        armoredImage = ImageLoader.imageLoader(ARMORED_CHARACTER_URL);
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
            direction = Direction.UP;
        }
        if (keyH.isDown()){
            position.setY(position.getY() + speed);
            direction = Direction.DOWN;

        }
        if (keyH.isRight()){
            position.setX(position.getX() + speed);
            direction = Direction.RIGHT;
        }
        if (keyH.isLeft()){
            position.setX(position.getX() - speed);
            direction = Direction.LEFT;

        }

    }

    @Override
    public void attack() {
        if (mouseH.isButtonOnePressed()) {
            moving = false;
            speed = 0;

        }
        if (!moving){
            endAttack(mouseH.getAttackTime());
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
