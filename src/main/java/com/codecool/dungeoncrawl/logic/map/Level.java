package com.codecool.dungeoncrawl.logic.map;
import com.codecool.dungeoncrawl.logic.entities.Player;
import com.codecool.dungeoncrawl.logic.entities.Skeleton;
import com.codecool.dungeoncrawl.logic.entities.Spirit;
import com.codecool.dungeoncrawl.util.ImageLoader;

import java.awt.image.BufferedImage;
import java.util.List;

public class Level {

    private final String levelDetailsUrl;


    private final BufferedImage background;

    private final List<Skeleton> skeletons;

    private final List<Spirit> spirits;

    private final Player player;

    private final Tile[][] tileGrid;

    private final LevelBuilder levelBuilder;

    public Level(String imageUrl, String blockDataUrl, String entityDateUrl) {
        levelBuilder = new LevelBuilder();
        this.background = ImageLoader.loadImage(imageUrl);
        levelDetailsUrl = blockDataUrl;
        tileGrid = levelBuilder.createMapBlocks(levelBuilder.getLayerDetails(levelDetailsUrl));
        skeletons = levelBuilder.spawnSkeleton(levelBuilder.getLayerDetails(entityDateUrl));
        spirits = levelBuilder.spawnSpirit(levelBuilder.getLayerDetails(entityDateUrl));
        player = levelBuilder.spawnPlayer(levelBuilder.getLayerDetails(entityDateUrl));
    }




    public List<Skeleton> getSkeletons() {
        return skeletons;
    }

    public List<Spirit> getSpirits() {
        return spirits;
    }

    public Player getPlayer() {
        return player;
    }

    public BufferedImage getBackground() {
        return background;
    }

    public Tile[][] getTileGrid() {
        return tileGrid;
    }
}
