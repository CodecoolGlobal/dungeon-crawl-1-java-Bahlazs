package com.codecool.dungeoncrawl.logic;


import com.codecool.dungeoncrawl.logic.entities.Entity;
import com.codecool.dungeoncrawl.logic.entities.Player;
import com.codecool.dungeoncrawl.logic.entities.Skeleton;
import com.codecool.dungeoncrawl.logic.map.Level;
import com.codecool.dungeoncrawl.logic.map.Tile;
import com.codecool.dungeoncrawl.main.Game;
import com.codecool.dungeoncrawl.util.Direction;
import com.codecool.dungeoncrawl.util.Position;
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

    public Skeleton getSkeleton() {
        return skeleton;
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

    public boolean checkEntityCollision(Entity entity1, Entity entity2) {
        return entity1.getHitBox().intersects(entity2.getHitBox());
    }

    public void checkCollisions(Entity entity) {

        for (int i = 0; i < currentLevel.getTileGrid().length; i++) {
            for (int j = 0; j < currentLevel.getTileGrid()[i].length; j++) {
                if (entity.getDirection() != Direction.UP) {
                    checkCollisionDown(currentLevel.getTileGrid()[i][j], entity);
                }
                if (entity.getDirection() != Direction.DOWN) {
                    checkCollisionUp(currentLevel.getTileGrid()[i][j], entity);
                }
                if (entity.getDirection() == Direction.RIGHT) {
                    checkCollisionRight(currentLevel.getTileGrid()[i][j], entity);
                }
                if (entity.getDirection() == Direction.LEFT) {
                    checkCollisionLeft(currentLevel.getTileGrid()[i][j], entity);
                }

            }

        }
    }

    private void checkCollisionDown(Tile tile, Entity entity) {
        if (tile.y > entity.getPosition().getY()) {
            if (checkTileCollision(tile, entity)) {
                if (tile.isSolid()) {
                    entity.setPayerPosByCollision(getPlayerPosition().getX(),tile.y - Game.TILE_SIZE);
                }
            }
        }
    }
    private void checkCollisionUp(Tile tile, Entity entity) {
        if (tile.y < entity.getPosition().getY()) {
            if (checkTileCollision(tile, entity)) {
                if (tile.isSolid()) {
                    entity.setPayerPosByCollision(getPlayerPosition().getX(),tile.y + Game.TILE_SIZE);
                }
            }
        }
    }
    private void checkCollisionRight(Tile tile, Entity entity) {
        if (tile.x > entity.getPosition().getX()) {
            if (checkTileCollision(tile, entity)) {
                if (tile.isSolid()) {
                    entity.setPayerPosByCollision(tile.x- Game.TILE_SIZE, getPlayerPosition().getY());
                }
            }
        }
    }
    private void checkCollisionLeft(Tile tile, Entity entity) {
        if (tile.x < entity.getPosition().getX()) {
            if (checkTileCollision(tile, entity)) {
                if (tile.isSolid()) {
                    entity.setPayerPosByCollision(tile.x + Game.TILE_SIZE, getPlayerPosition().getY());
                }
            }
        }
    }


    public void update() {
        checkCollisions(player);
        player.move();
        if(player.attack()) {
            if(skeleton != null){
                if(checkEntityCollision(player,skeleton)) {
                    skeleton = null;
                }
            }
        }
        setPlayerDetails();
        if (skeleton != null) {
            checkCollisions(skeleton);
            skeleton.move();
        }
    }

    private void setPlayerDetails() {
        playerImage = player.getImage();
        playerDirection = player.getDirection();
        playerPosition = player.getPosition();
        playerAttackDuration = player.getAttackDuration();
        playerCanMove = player.isMoving();
        if (skeleton != null) {
            skeletonImage = skeleton.getImage();
            skeletonPosition = skeleton.getPosition();
        }
    }

    public void createEnemies() {
        skeleton = new Skeleton(1500,1500, Game.TILE_SIZE);
    }


}
