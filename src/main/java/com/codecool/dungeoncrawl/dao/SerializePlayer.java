package com.codecool.dungeoncrawl.dao;

import com.codecool.dungeoncrawl.logic.entities.Player;
import com.codecool.dungeoncrawl.logic.map.Level;
import com.codecool.dungeoncrawl.main.Game;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class SerializePlayer {
//    opens java.awt.image.BufferedImage to com.google.gson; -- module-info.java ??
    public static void serialize(Player player) {
        try (FileWriter writer = new FileWriter("./src/main/resources/player-save/player.json")) {
            Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setPrettyPrinting().create();
            gson.toJson(player, writer);
            System.out.println("Saved to player.json");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void serialize(Level level) {
            try (FileWriter writer = new FileWriter("./src/main/resources/level-save/level.json")) {
                Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setPrettyPrinting().create();
                gson.toJson(level, writer);
                System.out.println("Saved to level.json");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    public static Player getFromJSON() {
        try (BufferedReader reader = Files.newBufferedReader(
                Paths.get("./src/main/resources/player-save/player.json"))) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            // convert the JSON string to a Player object
            Type type = new TypeToken<HashMap<String, Object>>(){}.getType();
            HashMap<String, Object> playerMap = gson.fromJson(reader, type);
            System.out.println("getFromJSON test:");
            System.out.println(playerMap);
            System.out.println("Position test:");
            LinkedTreeMap<String, Double> positionMap = (LinkedTreeMap<String, Double>) playerMap.get("position");
            int x = positionMap.get("x").intValue();
            int y = positionMap.get("y").intValue();
            return new Player(x, y, Game.TILE_SIZE);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
