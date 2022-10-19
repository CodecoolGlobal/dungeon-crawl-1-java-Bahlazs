package com.codecool.dungeoncrawl.logic.map;

import com.codecool.dungeoncrawl.util.ImageLoader;

import java.awt.image.BufferedImage;

public class Level {

    private String levelDetailsUrl;
    private BufferedImage background;


    private Tile[][] tileGrid;

    private final MapLoader mapLoader;

    public Level(String imageUrl, String dataUrl) {
        mapLoader = new MapLoader();
        this.background = ImageLoader.loadImage(imageUrl);
        levelDetailsUrl = dataUrl;
        tileGrid = mapLoader.createMapBlocks(mapLoader.getMapDetails(levelDetailsUrl));
    }

    public BufferedImage getBackground() {
        return background;
    }

    public Tile[][] getTileGrid() {
        return tileGrid;
    }
}
