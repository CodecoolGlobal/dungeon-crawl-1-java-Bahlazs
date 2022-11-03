package com.codecool.dungeoncrawl.logic.entities;

import com.codecool.dungeoncrawl.logic.KeyHandler;
import com.codecool.dungeoncrawl.logic.MouseHandler;
import com.codecool.dungeoncrawl.logic.items.Armor;
import com.codecool.dungeoncrawl.logic.items.Item;
import com.codecool.dungeoncrawl.logic.items.Key;
import com.codecool.dungeoncrawl.logic.items.Potion;
import com.codecool.dungeoncrawl.logic.map.Tile;
import com.codecool.dungeoncrawl.util.Direction;


import java.util.ArrayList;
import java.util.List;

public class Player extends  Entity{

    public static final String CHARACTER_URL = "/player/Samurai.png";
    public static final String ARMORED_CHARACTER_URL = "/player/Samurai-armored.png";

    public static final int PLAYER_BASE_SPEED = 3;
    private final int baseHP = 100;

    private List<String> inventory;

    private boolean hasKey;

    private boolean hasWon;

    private final double attackDuration;
    private boolean moving;

    private int hp;
    private int maxHp = 100;
    private boolean hasArmor;

    public Player(int x, int y, int size) {
        super(CHARACTER_URL, x, y, PLAYER_BASE_SPEED, size);
        hp = baseHP;
        hasArmor = false;
        attackDuration = 0.4;
        moving = true;
        inventory = new ArrayList<>();
        hasKey = false;
        hasWon = false;
    }


    public double getAttackDuration() {
        return attackDuration;
    }

    public boolean hasKey() {
        return hasKey;
    }

    public boolean hasArmor() {
        return hasArmor;
    }

    public boolean hasWon() {
        return hasWon;
    }

    public int getHp() {
        return this.hp;
    }

    public int getMaxHp() {
        return maxHp;
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



    public boolean playerAttack(MouseHandler mouseHandler) {
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

    public void raiseMaxHp(int amount) {
        this.maxHp += amount;
    }

    public void raiseHp(int amount) {
        if ((hp + amount) > maxHp) {
            this.hp = maxHp;
        } else {
            this.hp += amount;
        }
    }

    public void damagePlayer(int amount) {
        this.hp -= amount;
    }

    public void endAttack(MouseHandler mouseHandler) {
        if (mouseHandler.getAttackTime() + (attackDuration*1000) <= System.currentTimeMillis()) {
            mouseHandler.setAttacking(false);
            speed = PLAYER_BASE_SPEED;
            moving = true;

        }
    }

    public void pickUp(Item item, boolean eIsPressed) {
        if (eIsPressed) {
            if (item instanceof Armor) {
                pickUpArmor(item);
            }
            if (item instanceof Key) {
                pickUpKey(item);
            }
            if (item instanceof Potion) {
                pickUpPotion(item);
            }
        }
    }

    private void pickUpKey(Item item) {
        hasKey = true;
        item.setPickedUpTrue();
        inventory.add(item.getName());
    }

    private void pickUpArmor(Item item) {
        hasArmor = true;
        imageUrl = ARMORED_CHARACTER_URL;
        item.setPickedUpTrue();
        raiseMaxHp(Armor.ARMOR_DEF_VALUE);
        raiseHp(Armor.ARMOR_DEF_VALUE);
        inventory.add(item.getName());
    }

    private void pickUpPotion(Item item) {
        item.setPickedUpTrue();
        inventory.add(item.getName());
        raiseHp(Potion.POTION_HEAL_VALUE);
    }

    public void openDoor(Tile tile, boolean eIsPressed) {
        if (hasKey && eIsPressed && tile != null) {
            hasWon = true;
        }
        if (eIsPressed && tile != null) {
            System.out.println("The door is locked");
        }
    }

    public boolean checkPlayerIsAlive()  {
        return hp > 0;
    }
}
