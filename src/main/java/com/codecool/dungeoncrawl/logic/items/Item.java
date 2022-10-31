package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Drawable;
import com.codecool.dungeoncrawl.util.Direction;
import com.codecool.dungeoncrawl.util.ImageLoader;
import com.codecool.dungeoncrawl.util.Position;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Item extends Rectangle implements Drawable {

    protected String name;

    protected BufferedImage image;

    protected Position position;

    public Item(int x, int y, int width, int height, String name, String imageUrl) {
        super(x, y, width, height);
        this.position = new Position(x, y);
        this.name = name;
        this.image = ImageLoader.loadImage(imageUrl);
    }

    @Override
    public BufferedImage getImage() {
        return image;
    }

    @Override
    public Position getPosition() {
        return position;
    }

    public Direction getDirection() {
        return null;
    }
}
