package com.codecool.dungeoncrawl.dao;

import com.codecool.dungeoncrawl.logic.entities.Player;
//import com.codecool.dungeoncrawl.model.PlayerModel;

import java.util.List;

public interface PlayerDao {
//    void add(PlayerModel player);
//    void update(PlayerModel player);
//    PlayerModel get(int id);
//    List<PlayerModel> getAll();
//}
   void add(Player player);
    void update(Player player);
    Player get();

//    List<Player> getAll();
}
