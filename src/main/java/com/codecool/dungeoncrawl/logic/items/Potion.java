package com.codecool.dungeoncrawl.logic.items;

public class Potion extends Item{

    public static final String POTION_URL = "/items/lifepot.png";
    public static final int POTION_HEAL_VALUE = 20;
    public Potion(int x, int y, int width, int height, String name, String imageUrl) {
        super(x, y, width, height, name, imageUrl);
    }

}
