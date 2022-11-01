package com.codecool.dungeoncrawl.dao;

import com.codecool.dungeoncrawl.logic.entities.Player;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    public static Player getFromJSON() {
        try {
            Reader reader = Files.newBufferedReader(Paths.get("player.json"));
            // convert the JSON string to a Player object
            Player player = new Gson().fromJson(reader, Player.class);

            System.out.println(player);
            reader.close();
            return player;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
