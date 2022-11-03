package com.codecool.dungeoncrawl.logic.map;

//import com.codecool.dungeoncrawl.logic.KeyHandler;
//import com.codecool.dungeoncrawl.logic.MouseHandler;
import com.codecool.dungeoncrawl.logic.entities.Player;
import com.codecool.dungeoncrawl.logic.entities.Skeleton;
import com.codecool.dungeoncrawl.logic.entities.Spirit;
import com.codecool.dungeoncrawl.logic.items.Armor;
import com.codecool.dungeoncrawl.logic.items.Item;
import com.codecool.dungeoncrawl.logic.items.Key;
import com.codecool.dungeoncrawl.logic.items.Potion;
import com.codecool.dungeoncrawl.main.Game;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LevelBuilder {



    public List<List<Integer>> getLayerDetails(String url) {

        try {
            InputStream is = LevelBuilder.class.getResourceAsStream(url);

            BufferedReader reader = new BufferedReader(new InputStreamReader(is));

            String line = reader.readLine();
            List<List<Integer>> tileIds = new ArrayList<>();

            while (line!=null){
                List<Integer> ids = new ArrayList<>();
                tileIds.add(ids);
                String[] strings = line.split(",");
                for (String string: strings) {
                    int id = Integer.parseInt(string);
                    ids.add(id);
                }
                line= reader.readLine();
            }
            return tileIds;

        } catch (Exception e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
        }

        return null;
    }

    public Tile[][] createMapBlocks(List<List<Integer>> tileIds) {
        Tile[][] tileGrid = new Tile[tileIds.size()][tileIds.size()];
        for ( int i =0; i < tileIds.size(); i++) {
            for (int j = 0; j < tileIds.get(i).size(); j++) {
                tileGrid[i][j] = new Tile(j* Game.TILE_SIZE, i* Game.TILE_SIZE, Game.TILE_SIZE, Game.TILE_SIZE, tileIds.get(j).get(i));
                if (tileIds.get(i).get(j) == 395) {
                    tileGrid[i][j].setSolid();
                }
                if (tileIds.get(i).get(j) == 242) {
                    tileGrid[i][j] = new Door(j* Game.TILE_SIZE, i* Game.TILE_SIZE, Game.TILE_SIZE, Game.TILE_SIZE, tileIds.get(j).get(i));
                    tileGrid[i][j].setSolid();
                }

            }
        }
        return tileGrid;
    }

    public List<Skeleton> spawnSkeleton(List<List<Integer>> tileIds) {
        List<Skeleton> skeleton = new ArrayList<>();
        for (int i = 0; i < tileIds.size(); i++) {
            for (int j = 0; j < tileIds.get(i).size(); j++) {
                if (tileIds.get(i).get(j) == 393) {
                    skeleton.add(new Skeleton(j* Game.TILE_SIZE, i* Game.TILE_SIZE, Game.TILE_SIZE, 1.2));
                }
            }
        }
        return skeleton;
    }

    public List<Spirit> spawnSpirit(List<List<Integer>> tileIds) {
        List<Spirit> spirit = new ArrayList<>();
        for (int i = 0; i < tileIds.size(); i++) {
            for (int j = 0; j < tileIds.get(i).size(); j++) {
                if (tileIds.get(i).get(j) == 391) {
                    spirit.add(new Spirit(j* Game.TILE_SIZE, i* Game.TILE_SIZE, Game.TILE_SIZE, 0.7));
                }
            }
        }
        return spirit;
    }

    public Player spawnPlayer(List<List<Integer>> tileIds) {
        Player player = null;
        for (int i = 0; i < tileIds.size(); i++) {
            for (int j = 0; j < tileIds.get(i).size(); j++) {
                if (tileIds.get(i).get(j) == 394) {
                    player = new Player(j* Game.TILE_SIZE, i* Game.TILE_SIZE, Game.TILE_SIZE);
                }
            }
        }
        return player;
    }

    public List<Item> createItems(List<List<Integer>> tileIds) {
        List<Item> items = new ArrayList<>();
        for (int i = 0; i < tileIds.size(); i++) {
            for (int j = 0; j < tileIds.get(i).size(); j++) {
                if (tileIds.get(i).get(j) == 25) {

                    items.add(new Armor(j* Game.TILE_SIZE, i* Game.TILE_SIZE, Game.TILE_SIZE, Game.TILE_SIZE, "armor", "/items/armor.png"));
                }
                if (tileIds.get(i).get(j) == 369) {

                    items.add(new Potion(j* Game.TILE_SIZE, i* Game.TILE_SIZE, Game.TILE_SIZE, Game.TILE_SIZE, "empty flask", "/items/lifepot.png"));
                }
                if (tileIds.get(i).get(j) == 0) {

                    items.add(new Key(j* Game.TILE_SIZE, i* Game.TILE_SIZE, Game.TILE_SIZE, Game.TILE_SIZE, "key", "/items/key.png"));
                }

            }
        }
        return items;
    }
}


