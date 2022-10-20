package com.codecool.dungeoncrawl.logic.entities;

import com.codecool.dungeoncrawl.util.ImageLoader;

public class Skeleton extends Enemy {
    private static final String CHARACTER_URL = "/enemies/skeleton.png";
    private float directionCoolDown;


    public Skeleton(int x, int y, int size) {
        super(CHARACTER_URL, x, y, 2, size);
        this.directionCoolDown = 0;

    }

    @Override
    public boolean attack() {
    return true;
    }

    @Override
    public void endAttack(long time) {

    }

    @Override
    public void move() {
        incrementCooldown();
        switch (direction) {
            case UP:
                position.setY(position.getY() - speed);
                hitBox.y -= speed;
                break;
            case DOWN:
                position.setY(position.getY() + speed);
                hitBox.y += speed;
                break;
            case LEFT:
                position.setX(position.getX() - speed);
                hitBox.x -= speed;
                break;
            case RIGHT:
                position.setX(position.getX() + speed);
                hitBox.x += speed;
                break;
        }

    }

    private void incrementCooldown() {
        directionCoolDown += 0.01;
    }

    public void changeDirectionByCooldown() {
        if (directionCoolDown >= 0.5) {
            directionCoolDown = 0;
            direction = direction.getRandomDirection();
        }
    }
}
