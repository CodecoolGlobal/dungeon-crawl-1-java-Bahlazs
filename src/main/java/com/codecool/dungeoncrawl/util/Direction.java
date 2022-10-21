package com.codecool.dungeoncrawl.util;

import java.util.Random;

public enum Direction {
    UP(1),
    DOWN(0),
    RIGHT(3),
    LEFT(2);

     public int value;
    Direction(int value) {
        this.value = value;
    }

    public Direction getRandomDirection () {
        Random random = new Random();
        int i = random.nextInt(Direction.values().length);
        return Direction.values()[i];
    }



}
