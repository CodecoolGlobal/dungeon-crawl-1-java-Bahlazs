package com.codecool.dungeoncrawl.logic.map;
import com.codecool.dungeoncrawl.logic.entities.Player;
import com.codecool.dungeoncrawl.logic.entities.Skeleton;
import com.codecool.dungeoncrawl.logic.entities.Spirit;
import com.codecool.dungeoncrawl.logic.items.Item;
import com.codecool.dungeoncrawl.util.ImageLoader;
import com.google.gson.annotations.Expose;

import java.awt.image.BufferedImage;
import java.util.Iterator;
import java.util.List;

public class Level {

    private final String levelDetailsUrl;

    private final String backgroundUrl; //

    private final List<Skeleton> skeletons; //

    private final List<Spirit> spirits; //

    private final List<Item> items; //

    private final Player player; //

    private final Tile[][] tileGrid;

    private final LevelBuilder levelBuilder;


    public Level(String imageUrl, String blockDataUrl, String entityDateUrl, String itemDataUrl) {
        levelBuilder = new LevelBuilder();
        this.backgroundUrl = imageUrl;
        levelDetailsUrl = blockDataUrl;
        tileGrid = levelBuilder.createMapBlocks(levelBuilder.getLayerDetails(levelDetailsUrl));
        items = levelBuilder.createItems(levelBuilder.getLayerDetails(itemDataUrl));
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

    public List<Item> getItems() {
        return items;
    }

    public String getBackground() {
        return backgroundUrl;
    }

    public Tile[][] getTileGrid() {
        return tileGrid;
    }

    public void clearPickedUpItems() {
        Iterator<Item> it = items.iterator();
        while (it.hasNext()) {
            if (it.next().isPickedUp()) {
                it.remove();
                break;
            }
        }
    }
}
