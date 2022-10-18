package com.codecool.dungeoncrawl.logic.map;

import com.codecool.dungeoncrawl.util.ImageLoader;

import java.awt.image.BufferedImage;

public class Level {

    private String levelDetailsUrl;
    private BufferedImage background;


    private Tile[][] blockGrid;

    private final MapLoader mapLoader;

    public Level(String url, int maxWorldCol, int MaxWorldRow) {
        mapLoader = new MapLoader();
        this.background = ImageLoader.imageLoader(url);
    }

    public BufferedImage getBackground() {
        return background;
    }


}
