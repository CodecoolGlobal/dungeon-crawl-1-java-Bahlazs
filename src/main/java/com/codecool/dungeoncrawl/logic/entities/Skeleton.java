package com.codecool.dungeoncrawl.logic.entities;

public class Skeleton extends Enemy {
    public static final String SKELETON_URL = "/enemies/skeleton.png";
    private float directionCoolDown;



    public Skeleton(int x, int y, int size, double attackCooldown) {
        super(SKELETON_URL, x, y, 1, size, attackCooldown,12);
        this.directionCoolDown = 0;

    }


    @Override
    public void move() {
        incrementCoolDown();
        changeDirectionByCoolDown();
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

    private void incrementCoolDown() {
        directionCoolDown += 0.01;
    }

    public void changeDirectionByCoolDown() {
        if (directionCoolDown >= 0.5) {
            directionCoolDown = 0;
            direction = direction.getRandomDirection();
        }
    }

    @Override
    public int getHp() {
        return 0;
    }
}
