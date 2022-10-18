package com.codecool.dungeoncrawl.logic.map;

import com.codecool.dungeoncrawl.util.Position;

import java.awt.*;

public class Tile extends Rectangle {

    private Position position;

    private final int Id;

    public Tile(int x, int y, int width, int height, Position position, int id) {
        super(x, y, width, height);
        this.position = position;
        Id = id;
    }
}
