package com.codecool.dungeoncrawl.logic.entities;

public abstract class Enemy extends Entity {

    public Enemy(String url, int x, int y, int speed, int size) {
        super(url, x, y, speed, size);
    }
}
