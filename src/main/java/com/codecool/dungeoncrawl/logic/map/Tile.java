package com.codecool.dungeoncrawl.logic.map;

import com.codecool.dungeoncrawl.util.Position;

import java.awt.*;

public class Tile extends Rectangle {

    private Position position;

    private final int Id;

    private boolean solid;

    public Tile(int x, int y, int width, int height, int id) {
        super(x, y, width, height);
        this.position = new Position(x,y);
        Id = id;
        solid = false;
    }

    public boolean isSolid() {
        return solid;
    }

    public Position getPosition() {
        return position;
    }

    public void setSolid() {
        if (!solid) {
            solid = true;
        }
    }
}
