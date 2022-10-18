package com.codecool.dungeoncrawl.logic.entities;

import com.codecool.dungeoncrawl.util.Direction;

public class Skeleton extends Enemy {
    private static final String CHARACTER_URL = "/player/Samurai.png";
    private float directionCooldown;
    private final int speed;

    public Skeleton(int x, int y, int size) {
        super(CHARACTER_URL, x, y, size);
        this.speed = 1;
        this.directionCooldown = 0;
    }

    @Override
    public void attack() {

    }

    @Override
    public void endAttack(long time) {

    }

    @Override
    public void move() {
        incrementCooldown();
        changeDirectionByCooldown();
        switch (direction) {
            case UP:
                position.setY(position.getY() - speed);
                break;
            case DOWN:
                position.setY(position.getY() + speed);
                break;
            case LEFT:
                position.setX(position.getX() - speed);
                break;
            case RIGHT:
                position.setX(position.getX() + speed);
                break;
        }

    }

    private void incrementCooldown() {
        directionCooldown += 0.01;
    }

    private void changeDirectionByCooldown() {
        if (directionCooldown >= 2) {
            directionCooldown = 0;
            direction = direction.getRandomDirection();
        }
    }
}
