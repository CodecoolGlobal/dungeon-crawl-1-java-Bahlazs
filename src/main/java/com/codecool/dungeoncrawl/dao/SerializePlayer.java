package com.codecool.dungeoncrawl.dao;

import com.codecool.dungeoncrawl.logic.entities.Player;
import com.google.gson.Gson;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

public class SerializePlayer {
    public void serialize(Player player) {
        try {
            new Gson().toJson(player, new FileWriter("src/resources/player/player.json"));
            System.out.println("Saved to player.json");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Player getFromJSON() {
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
