package com.codecool.dungeoncrawl.dao;

import com.codecool.dungeoncrawl.logic.KeyHandler;
import com.codecool.dungeoncrawl.logic.MouseHandler;
import com.codecool.dungeoncrawl.logic.entities.Player;
import com.codecool.dungeoncrawl.main.Game;
import com.codecool.dungeoncrawl.util.Position;
//import com.codecool.dungeoncrawl.model.PlayerModel;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;

public class PlayerDaoJdbc implements PlayerDao {
    private DataSource dataSource;

    public PlayerDaoJdbc(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void add(Player player) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "INSERT INTO player (hp, x, y) VALUES (?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS); // TODO kell key?
//            statement.setString(1, player.getPlayerName());
            Position position = player.getPosition();
            statement.setInt(1, player.getHp());
            statement.setInt(2, position.getX());
            statement.setInt(3, position.getY());
            statement.executeUpdate();
//            ResultSet resultSet = statement.getGeneratedKeys();
//            resultSet.next();
//            player.setId(resultSet.getInt(1));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Player player) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "UPDATE player SET hp = ?, x = ?, y = ? WHERE id = 1"; // TODO make sure id == 1
            PreparedStatement statement = conn.prepareStatement(sql);
//            statement.setString(1, player.getPlayerName());
            Position position = player.getPosition();
            statement.setInt(1, player.getHp());
            statement.setInt(2, position.getX());
            statement.setInt(3, position.getY());
            statement.executeUpdate();
//            ResultSet resultSet = statement.getGeneratedKeys();
//            resultSet.next();
//            player.setId(resultSet.getInt(1));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Player get() {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT hp, x, y FROM player WHERE id = 1"; // TODO make sure id == 1
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            int hp = resultSet.getInt(1); // TODO needs to be implemented
            int x = resultSet.getInt(2);
            int y = resultSet.getInt(3);
            return new Player(x, y, Game.TILE_SIZE, new KeyHandler(), new MouseHandler());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

//    @Override
//    public List<Player> getAll() {
//        return null;
//    }
}
