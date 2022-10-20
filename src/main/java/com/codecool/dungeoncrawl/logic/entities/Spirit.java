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
    public void endAttack(long time) {

    }

    @Override
    public void move() {
        if (playerInRange) { //TODO

        }
    }

    public void checkPlayerInRange(Player player) {
        if (Math.abs(player.getPosition().getX() - hitBox.x) <= range && Math.abs(player.getPosition().getY() - hitBox.y) <= range ) { //TODO
            playerInRange = true;
        } else {
            playerInRange = false;
        }
    }
}
