package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.logic.entities.Player;
import com.codecool.dungeoncrawl.logic.map.Level;
import com.codecool.dungeoncrawl.main.Game;
import com.codecool.dungeoncrawl.util.Direction;
import com.codecool.dungeoncrawl.util.Position;

import java.awt.*;
import java.awt.image.BufferedImage;

public class LogicHandler {
    private static final String LEVEL_1_BACKGROUND_URL = "/background/level1-background.png";

    private static final int LEVEL_1_MAX_WORLD_COL = 50;

    private static final int LEVEL_1_MAX_WORLD_ROW = 50;

    private KeyHandler keyH;
    private MouseHandler mouseH;


    private final Level levelOne;

    private Level currentLevel;

    private final Player player;

    // player details

    private BufferedImage playerImage;

    private Position playerPosition;

    private Direction playerDirection;

    private double playerAttackDuration;

    private Boolean playerCanMove;

    public LogicHandler(int width, int height) {
        levelOne = new Level(LEVEL_1_BACKGROUND_URL, LEVEL_1_MAX_WORLD_COL, LEVEL_1_MAX_WORLD_ROW);
        currentLevel = levelOne;
        this.keyH = new KeyHandler();
        this.mouseH = new MouseHandler();
        player = new Player( 1000,1000, 64, keyH, mouseH);
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

    public void update() {
        player.move();
        player.attack();
        setPlayerDetails();
    }

    private void setPlayerDetails() {
        playerImage = player.getImage();
        playerDirection = player.getDirection();
        playerPosition = player.getPosition();
        playerAttackDuration = player.getAttackDuration();
        playerCanMove = player.isMoving();
    }



}
