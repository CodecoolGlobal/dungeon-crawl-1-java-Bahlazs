package com.codecool.dungeoncrawl.logic.map;

import com.codecool.dungeoncrawl.util.ImageLoader;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Level {

    private String levelDetailsUrl;
    private BufferedImage background;


    private ArrayList<Block> collisionBlocks;

    private final MapLoader mapLoader;

    public Level(String url) {
        mapLoader = new MapLoader();
        this.background = ImageLoader.imageLoader(url);
    }

    public BufferedImage getBackground() {
        return background;
    }


}
