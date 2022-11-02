package com.codecool.dungeoncrawl.logic.items;

public class Armor extends Item {

    public static final int ARMOR_DEF_VALUE = 30;

    public static final String ARMOR_URL = "/items/armor.png";
    public Armor(int x, int y, int width, int height, String name, String imageUrl) {
        super(x, y, width, height, name, imageUrl);
    }
}
