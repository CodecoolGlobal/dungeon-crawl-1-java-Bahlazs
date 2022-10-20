package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.util.Direction;
import com.codecool.dungeoncrawl.util.Position;

import java.awt.image.BufferedImage;

public interface Drawable {

    BufferedImage getImage();

    Position getPosition();

    Direction getDirection();

}
