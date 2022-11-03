package com.codecool.dungeoncrawl.dao;

import com.codecool.dungeoncrawl.logic.items.Armor;
import com.codecool.dungeoncrawl.logic.items.Item;
import com.codecool.dungeoncrawl.logic.items.Key;
import com.codecool.dungeoncrawl.logic.items.Potion;
import com.codecool.dungeoncrawl.logic.map.Level;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Serializer {
    public static void serialize(Level level, Gson gson) {
        try (FileWriter writer = new FileWriter("./src/main/resources/level-save/level.json")) {
            gson.toJson(level, writer);
            System.out.println("Saved to level.json");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Level getLevelFromJSON(Gson gson) {
        try (BufferedReader reader = Files.newBufferedReader(
                Paths.get("./src/main/resources/level-save/level.json"))) {
            System.out.println("getFromJSON test:");
            Level level = gson.fromJson(reader, Level.class);
            System.out.println(level);
            return level;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Gson createGson() {
        RuntimeTypeAdapterFactory<Item> itemAdapterFactory
                = RuntimeTypeAdapterFactory.of(Item.class);
        itemAdapterFactory.registerSubtype(Armor.class, "Armor");
        itemAdapterFactory.registerSubtype(Key.class, "Key");
        itemAdapterFactory.registerSubtype(Potion.class, "Potion");
        return new GsonBuilder()
                .registerTypeAdapterFactory(itemAdapterFactory)
                .setPrettyPrinting()
                .create();
    }

}
