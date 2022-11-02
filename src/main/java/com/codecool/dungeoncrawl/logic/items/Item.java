package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Drawable;
import com.codecool.dungeoncrawl.logic.DrawableItem;
import com.codecool.dungeoncrawl.util.Direction;
import com.codecool.dungeoncrawl.util.ImageLoader;
import com.codecool.dungeoncrawl.util.Position;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Item implements DrawableItem {

    public static final int PICK_UP_RADIUS_OFFSET = 20;

    protected String name;

    protected BufferedImage image;

    protected final Rectangle  pickUpRadius;

    protected boolean pickedUp;

    protected Position position;

    public Item(int x, int y, int width, int height, String name, String imageUrl) {
        position = new Position(x, y);
        this.name = name;
        image = ImageLoader.loadImage(imageUrl);
        pickUpRadius = new Rectangle(x - PICK_UP_RADIUS_OFFSET/2, y - PICK_UP_RADIUS_OFFSET/2,
                                width + PICK_UP_RADIUS_OFFSET, height + PICK_UP_RADIUS_OFFSET);
        pickedUp = false;
    }

    @Override
    public BufferedImage getImage() {
        return image;
    }

    @Override
    public Position getPosition() {
        return position;
    }


    public Rectangle getPickUpRadius() {
        return pickUpRadius;
    }

    public boolean isPickedUp() {
        return pickedUp;
    }

    public void setPickedUpTrue() {
        pickedUp = true;
    }

    public String getName() {
        return name;
    }
}
