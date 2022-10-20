package com.codecool.dungeoncrawl.logic.entities;

public class Spirit extends Entity {

    private static final String CHARACTER_IMAGE_URL = "/enemies/spirit.png";
    public Spirit(int x, int y, int size) {
        super(CHARACTER_IMAGE_URL, x, y,3, size);

    }

    @Override
    protected boolean attack() {
        return false;
    }

    @Override
    protected void endAttack(long time) {

    }

    @Override
    public void move() {

    }
}
