package com.codecool.dungeoncrawl.dao;

import com.codecool.dungeoncrawl.logic.entities.Player;
//import com.codecool.dungeoncrawl.model.PlayerModel;
import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;
import java.sql.SQLException;

public class GameDatabaseManager {
    private PlayerDao playerDao;

    public void setup() throws SQLException {
        DataSource dataSource = connect();
        playerDao = new PlayerDaoJdbc(dataSource);
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

    public void getPlayer() {
        playerDao.get();
    }

    private DataSource connect() throws SQLException {
        PGSimpleDataSource dataSource = new PGSimpleDataSource();
//        String dbName = "test";
//        String user = "test";
//        String password = "test";
        String dbName = System.getenv("DB_NAME");
        String user = System.getenv("USER");
        String password = System.getenv("PASSWORD");

        dataSource.setDatabaseName(dbName);
        dataSource.setUser(user);
        dataSource.setPassword(password);

        System.out.println("Trying to connect");
        dataSource.getConnection().close();
        System.out.println("Connection ok.");

        return dataSource;
    }
}
