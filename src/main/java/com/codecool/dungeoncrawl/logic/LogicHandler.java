package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.logic.entities.Enemy;
import com.codecool.dungeoncrawl.logic.entities.Entity;
import com.codecool.dungeoncrawl.logic.entities.Player;
import com.codecool.dungeoncrawl.logic.entities.Skeleton;
import com.codecool.dungeoncrawl.logic.map.Level;
import com.codecool.dungeoncrawl.logic.map.Tile;
import com.codecool.dungeoncrawl.main.Game;
import com.codecool.dungeoncrawl.util.Direction;
import com.codecool.dungeoncrawl.util.Position;
import javafx.geometry.Pos;

import java.awt.*;
import java.awt.image.BufferedImage;

public class LogicHandler {

    // level 1 data
    private static final String LEVEL_1_BACKGROUND_URL = "/background/level1-background.png";

    private static final String LEVEL_1_DATA_URL = "/Tiled-projects/blocks-level1_Blocks.csv";

    private KeyHandler keyH;
    private MouseHandler mouseH;


    private final Level levelOne;

    private Level currentLevel;

    private final Player player;

    private Skeleton skeleton;
    private BufferedImage skeletonImage;

    private Position skeletonPosition;

    // player details

    private BufferedImage playerImage;
    private Position playerPosition;

    private Direction playerDirection;

    private double playerAttackDuration;

    private Boolean playerCanMove;

    public LogicHandler(int width, int height) {
        levelOne = new Level(LEVEL_1_BACKGROUND_URL, LEVEL_1_DATA_URL);
        currentLevel = levelOne;
        this.keyH = new KeyHandler();
        this.mouseH = new MouseHandler();
        player = new Player( 1000,1000, 64, keyH, mouseH);
        createEnemies();
    }

    public BufferedImage getSkeletonImage() {
        return skeletonImage;
    }

    public Position getSkeletonPosition() {
        return skeletonPosition;
    }

    public BufferedImage getCurrentLevelBackGround() {
        return currentLevel.getBackground();
    }

    public BufferedImage getPlayerImage() {
        return playerImage;
    }

    public Position getPlayerPosition() {
        return playerPosition;
    }

    public Direction getPlayerDirection() {
        return playerDirection;
    }

    public double getPlayerAttackDuration() {
        return playerAttackDuration;
    }

    public Boolean canPlayerMove() {
        return playerCanMove;
    }

    public KeyHandler getKeyH() {
        return keyH;
    }

    public MouseHandler getMouseH() {
        return mouseH;
    }

    private boolean checkTileCollision(Tile tile, Entity entity) {
        return entity.getHitBox().intersects(tile);
    }

    private boolean checkEntityCollision(Entity entity1, Entity entity2) {
        return entity1.getHitBox().intersects(entity2.getHitBox());
    }

    public void checkCollisions() {

        for (int i = 0; i < levelOne.getTileGrid().length; i++) {
            for (int j = 0; j < levelOne.getTileGrid()[i].length; j++) {
                checkCollisionDown(levelOne.getTileGrid()[i][j]);
                checkCollisionUp(levelOne.getTileGrid()[i][j]);
                checkCollisionRight(levelOne.getTileGrid()[i][j]);
                checkCollisionLeft(levelOne.getTileGrid()[i][j]);

            }

        }
    }

    private void checkCollisionDown(Tile tile) {
        if (tile.y > player.getPosition().getY()) {
            if (checkTileCollision(tile, player)) {
                System.out.println("valami");
                if (tile.isSolid()) {
                    System.out.println("collide");
                    player.stopMovement();
                }
            }
        }
    }
    private void checkCollisionUp(Tile tile) {
        if (tile.y < player.getPosition().getY()) {
            if (checkTileCollision(tile, player)) {
                System.out.println("valami");
                if (tile.isSolid()) {
                    System.out.println("collide");
                    player.stopMovement();
                }
            }
        }
    }
    private void checkCollisionRight(Tile tile) {
        if (tile.x > player.getPosition().getX()) {
            if (checkTileCollision(tile, player)) {
                System.out.println("valami");
                if (tile.isSolid()) {
                    System.out.println("collide");
                    player.stopMovement();
                }
            }
        }
    }
    private void checkCollisionLeft(Tile tile) {
        if (tile.x < player.getPosition().getX()) {
            if (checkTileCollision(tile, player)) {
                System.out.println("valami");
                if (tile.isSolid()) {
                    System.out.println("collide");
                    player.stopMovement();
                }
            }
        }
    }


    public void update() {
        player.move();
        player.attack();
        setPlayerDetails();
        checkCollisions();
        skeleton.move();
    }

    private void setPlayerDetails() {
        playerImage = player.getImage();
        playerDirection = player.getDirection();
        playerPosition = player.getPosition();
        playerAttackDuration = player.getAttackDuration();
        playerCanMove = player.isMoving();
        skeletonImage = skeleton.getImage();
        skeletonPosition = skeleton.getPosition();
    }

    public void createEnemies() {
        skeleton = new Skeleton(1,1,Game.TILE_SIZE);
    }


}
