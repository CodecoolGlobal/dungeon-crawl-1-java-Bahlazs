package com.codecool.dungeoncrawl.logic.map;

import com.codecool.dungeoncrawl.main.Game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class MapLoader {



    public List<List<Integer>> getMapDetails(String url) {

        try {

            InputStream is = MapLoader.class.getResourceAsStream(url);

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
            System.out.println(e.getStackTrace());
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
            }
        }
        return tileGrid;
    }

}

