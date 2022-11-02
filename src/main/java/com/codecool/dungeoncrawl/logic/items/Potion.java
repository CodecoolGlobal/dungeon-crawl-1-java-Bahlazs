package com.codecool.dungeoncrawl.logic.items;

public class Potion extends Item{
    private int healAmount;
    public Potion(int x, int y, int width, int height, String name, String imageUrl) {
        super(x, y, width, height, name, imageUrl);
        healAmount = 20;
    }

    public int getHealAmount() {
        return healAmount;
    }
}
