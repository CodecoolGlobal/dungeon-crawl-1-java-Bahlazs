package com.codecool.dungeoncrawl.logic.entities;

import com.codecool.dungeoncrawl.logic.KeyHandler;
import com.codecool.dungeoncrawl.logic.MouseHandler;
import com.codecool.dungeoncrawl.util.Direction;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class Player extends  Entity{

    private final KeyHandler keyH;
    private  final MouseHandler mouseH;

    private final int screenX;

    private final int screenY;
    private BufferedImage armoredImage;
    private int speed;

    private double attackDuration;
    private boolean moving;


    public Player(String url, int x, int y, int size, KeyHandler keyH, MouseHandler mouseH , int screenWidth, int screenHeight) {
        super(url, x, y, size);
        screenX = screenWidth/2 -(getEntitySize()/2);
        screenY = screenHeight/2 -(getEntitySize()/2);
        this.keyH = keyH;
        this.mouseH = mouseH;
        speed = 2;
        attackDuration = 0.7;
        moving = true;
        importArmorImage();
    }

    public int getScreenX() {
        return screenX;
    }

    public int getScreenY() {
        return screenY;
    }

    private void importArmorImage() {
        InputStream is = getClass().getResourceAsStream("/player/Samurai-armored.png");
        try {
            armoredImage = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void move() {
        if (keyH.isUp()){
            position.setY(position.getY() - speed);
            direction = Direction.UP;
            animate();
        }
        if (keyH.isDown()){
            position.setY(position.getY() + speed);
            direction = Direction.DOWN;
            animate();
        }
        if (keyH.isRight()){
            position.setX(position.getX() + speed);
            direction = Direction.RIGHT;
            animate();
        }
        if (keyH.isLeft()){
            position.setX(position.getX() - speed);
            direction = Direction.LEFT;
            animate();
        }


    }


    @Override
    protected void animate() {
        animationIndexX = direction.value;
        animationIndexY += 0.07;
        if (animationIndexY > 4) {
            animationIndexY = 0;
        }
    }

    public void attack() {
        if (mouseH.isButtonOnePressed()) {
            moving = false;
            speed = 0;
            animationIndexY = 4;
        }
        if (!moving){
            endAttack(mouseH.getAttackTime());
        }
    }

    protected void endAttack(long attackTime) {
        if (attackTime + (attackDuration*1000) <= System.currentTimeMillis()) {
            animationIndexY = 0;
            mouseH.setAttacking(false);
            speed = 2;
            moving = true;

        }
    }
}
