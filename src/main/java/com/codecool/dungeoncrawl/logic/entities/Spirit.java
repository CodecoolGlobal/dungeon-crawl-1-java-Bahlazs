package com.codecool.dungeoncrawl.logic.entities;

public class Spirit extends Enemy{

    private static final String CHARACTER_URL = "/enemies/spirit.png";
    private final int range;
    private boolean playerInRange;

    public Spirit(int x, int y, int size) {
        super(CHARACTER_URL, x, y, 2, size);
        this.range = 300;
    }

    @Override
    public boolean attack() {
        return false;
    }

    @Override
    public void endAttack() {

    }

    @Override
    public void move() {

    }

    public void move(Player player) {
        if (playerInRange) {
            moveX(player);
            moveY(player);

        }
    }

    private void moveX(Player player) {
        if (this.position.getX() < player.position.getX()) {
            this.position.setX(this.position.getX() + speed);
            this.getHitBox().x += speed;
        } else {
            this.position.setX(this.position.getX() - speed);
            this.getHitBox().x -= speed;
        }
    }

    private void moveY(Player player) {
        if (this.position.getY() < player.position.getY()) {
            this.position.setY(this.position.getY() + speed);
            this.getHitBox().y += speed;
        } else {
            this.position.setY(this.position.getY() - speed);
            this.getHitBox().y -= speed;
        }
    }

    public void checkPlayerInRange(Player player) {
        if (checkDiagonal(player) || checkHorizontal(player) || checkVertical(player)) {
            playerInRange = true;
        } else {
            playerInRange = false;
        }
    }

    private boolean checkDiagonal (Player player) {
        return Math.abs(player.getPosition().getX() - hitBox.x) <= range && Math.abs(player.getPosition().getY() - hitBox.y) <= range;
    }
    private boolean checkHorizontal (Player player) {
        return (player.getPosition().getX() == hitBox.x && (Math.abs(player.getPosition().getY() - hitBox.y) <= range));
    }

    private boolean checkVertical (Player player) {
        return (player.getPosition().getY() == hitBox.y && (Math.abs(player.getPosition().getX() - hitBox.x) <= range));
    }
}

