package com.codecool.dungeoncrawl.util;

public enum Direction {
    UP(1),
    DOWN(0),
    RIGHT(3),
    LEFT(2);

     public int value;
    Direction(int value) {
        this.value = value;
    }



}
