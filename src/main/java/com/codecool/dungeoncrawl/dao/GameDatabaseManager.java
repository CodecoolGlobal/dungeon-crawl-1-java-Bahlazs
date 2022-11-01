package com.codecool.dungeoncrawl.dao;

import com.codecool.dungeoncrawl.logic.entities.Player;
//import com.codecool.dungeoncrawl.model.PlayerModel;
import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;

public class GameDatabaseManager {
    private PlayerDao playerDao;

    public void setup() {
        try {
            DataSource dataSource = connect();
            playerDao = new PlayerDaoJdbc(dataSource);
        } catch (SQLException e) {
            System.out.println("Connection failed");
        }
    }

//    public void savePlayer(Player player) {
//        PlayerModel model = new PlayerModel(player);
//        playerDao.add(model);
//    }

    public void savePlayer(Player player) {
        playerDao.add(player);
    }

    public void updatePlayer(Player player) {
        playerDao.update(player);
    }

    public Player getPlayer() {
        return playerDao.get();
    }

    private DataSource connect() throws SQLException {
        PGSimpleDataSource dataSource = new PGSimpleDataSource();
//        String dbName = "test";
//        String user = "test";
//        String password = "test";
        String dbName = System.getenv("DB_NAME");
        String user = System.getenv("USER");
        String password = System.getenv("PASSWORD");
        System.out.println(dbName);
        System.out.println(user);
        System.out.println(password);

        dataSource.setDatabaseName(dbName);
        dataSource.setUser(user);
        dataSource.setPassword(password);

        System.out.println("Trying to connect");
        dataSource.getConnection().close();
        System.out.println("Connection ok.");

        return dataSource;
    }
}
