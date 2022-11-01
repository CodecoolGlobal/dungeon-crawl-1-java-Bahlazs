package com.codecool.dungeoncrawl.logic.entities;

import com.codecool.dungeoncrawl.logic.KeyHandler;
import com.codecool.dungeoncrawl.logic.MouseHandler;
import com.codecool.dungeoncrawl.logic.items.Armor;
import com.codecool.dungeoncrawl.logic.items.Item;
import com.codecool.dungeoncrawl.util.Direction;
import com.codecool.dungeoncrawl.util.ImageLoader;

import java.awt.image.BufferedImage;

public class Player extends  Entity{

    public static final String CHARACTER_URL = "/player/Samurai.png";
    public static final String ARMORED_CHARACTER_URL = "/player/Samurai-armored.png";

    public static final int PLAYER_BASE_SPEED = 3;


    private BufferedImage armoredImage;


    private final double attackDuration;
    private boolean moving;
    private int hp = 100;


    public Player(int x, int y, int size) {
        super(CHARACTER_URL, x, y, PLAYER_BASE_SPEED, size);
        attackDuration = 0.4;
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

    }


    public void move(KeyHandler keyHandler) {
        if (keyHandler.isUp()){
            position.setY(position.getY() - speed);
            hitBox.y -= speed;
            direction = Direction.UP;
        }
        else if (keyHandler.isDown()){
            position.setY(position.getY() + speed);
            hitBox.y += speed;
            direction = Direction.DOWN;

        }
        else if (keyHandler.isRight()){
            position.setX(position.getX() + speed);
            hitBox.x += speed;
            direction = Direction.RIGHT;
        }
        else if (keyHandler.isLeft()){
            position.setX(position.getX() - speed);
            hitBox.x -= speed;
            direction = Direction.LEFT;

        }

    }

    @Override
    public boolean attack() {
        return false;
    }


    public boolean attack(MouseHandler mouseHandler) {
        if (mouseHandler.isButtonOnePressed()) {
            moving = false;
            stopMovement();
            if (!moving) {
                endAttack(mouseHandler);
            }
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void endAttack() {

    }


    public void endAttack(MouseHandler mouseHandler) {
        if (mouseHandler.getAttackTime() + (attackDuration*1000) <= System.currentTimeMillis()) {
            mouseHandler.setAttacking(false);
            speed = PLAYER_BASE_SPEED;
            moving = true;

        }
    }

    public int getHp() {
        return this.hp;
    }

    public void pickUp(Item item, boolean eIsPressed) {
        if (eIsPressed) {
            if (item instanceof Armor) {
                image = armoredImage;
                item.setPickedUpTrue();
                hp += 10;
            }
        }
    }
}
