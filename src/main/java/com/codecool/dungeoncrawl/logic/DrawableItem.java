package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.util.Position;

import java.awt.image.BufferedImage;

public interface DrawableItem {
    BufferedImage getImage();

    Position getPosition();
}
