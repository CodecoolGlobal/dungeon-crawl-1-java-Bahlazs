package com.codecool.dungeoncrawl.logic.map;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Map {

    private BufferedImage background;

    private ArrayList<Tile> visibleTiles;

    private ArrayList<Tile> collisionTiles;

    private final MapLoader mapLoader;

    public Map() {
        mapLoader = new MapLoader();
        this.background = mapLoader.loadBackground();
    }

    public BufferedImage getBackground() {
        return background;
    }
}
