package com.codecool.dungeoncrawl.logic;


import com.codecool.dungeoncrawl.logic.entities.*;
import com.codecool.dungeoncrawl.logic.map.Level;
import com.codecool.dungeoncrawl.logic.map.Tile;
import com.codecool.dungeoncrawl.main.Game;
import com.codecool.dungeoncrawl.util.Direction;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class LogicHandler {

    // level 1 data
    private static final String LEVEL_1_BACKGROUND_URL = "/background/level1-background.png";

    private static final String LEVEL_1_BLOCK_DATA_URL = "/Tiled-projects/blocks-level1_Blocks.csv";

    private static final String LEVEL_1_ENTITY_DATA_URL = "/Tiled-projects/blocks-level1_Entities.csv";

    private final KeyHandler keyH;
    private final MouseHandler mouseH;


    private final Level levelOne;

    private Level currentLevel;

    private final Player player;

    private List<Skeleton> skeletons;

    private List<Spirit> spirits;

    private double playerAttackDuration;

    private boolean playerCanMove;

    private boolean playerIsAttacking;

    public LogicHandler() {
        levelOne = new Level(LEVEL_1_BACKGROUND_URL, LEVEL_1_BLOCK_DATA_URL, LEVEL_1_ENTITY_DATA_URL);
        currentLevel = levelOne;
        player = levelOne.getPlayer();
        skeletons = currentLevel.getSkeletons();
        spirits = currentLevel.getSpirits();
        this.keyH = player.getKeyH();
        this.mouseH = player.getMouseH();
        playerCanMove = true;
    }

    public Drawable getPlayer() {
        return player;
    }

    public List<Drawable> getEnemies() {
        List<Drawable> enemies = new ArrayList<>();
        for (Skeleton skeleton : skeletons) {
            if (skeleton != null) {
                enemies.add(skeleton);
            }
        }
//        enemies.addAll(skeletons);
        enemies.addAll(spirits);
        return enemies;
    }


    public BufferedImage getCurrentLevelBackGround() {
        return currentLevel.getBackground();
    }


    public boolean playerIsAttacking() {
        return playerIsAttacking;
    }


    public KeyHandler getKeyH() {
        return keyH;
    }

    public MouseHandler getMouseH() {
        return mouseH;
    }

    // --------------------------------------------------------------- COLLISION ---------------------------------------------------------------------------------------

    private boolean checkTileCollision(Tile tile, Entity entity) {
        return entity.getHitBox().intersects(tile);
    }

    public boolean checkEntityCollision(Entity entity1, Entity entity2) {
        return entity1.getHitBox().intersects(entity2.getHitBox());
    }

    public void checkCollisions(Entity entity) {

        for (int i = 0; i < currentLevel.getTileGrid().length; i++) {
            for (int j = 0; j < currentLevel.getTileGrid()[i].length; j++) {
                if (entity.getDirection() == Direction.DOWN) {
                    checkCollisionDown(currentLevel.getTileGrid()[i][j], entity);
                }
                if (entity.getDirection() == Direction.UP) {
                    checkCollisionUp(currentLevel.getTileGrid()[i][j], entity);
                }
                if (entity.getDirection() == Direction.LEFT) {
                    checkCollisionLeft(currentLevel.getTileGrid()[i][j], entity);
                }
                if (entity.getDirection() == Direction.RIGHT) {
                    checkCollisionRight(currentLevel.getTileGrid()[i][j], entity);
                }

            }

        }
    }

    private void checkCollisionDown(Tile tile, Entity entity) {
        if (tile.y > entity.getPosition().getY()) {
            if (checkTileCollision(tile, entity)) {
                if (tile.isSolid()) {
                    entity.setPayerPosByCollision(entity.getPosition().getX(), tile.y - Game.TILE_SIZE);
                }
            }
        }
    }

    private void checkCollisionUp(Tile tile, Entity entity) {
        if (tile.y < entity.getPosition().getY()) {
            if (checkTileCollision(tile, entity)) {
                if (tile.isSolid()) {
                    entity.setPayerPosByCollision(entity.getPosition().getX(), tile.y + Game.TILE_SIZE - Entity.HIT_BOX_Y_OFFSET);
                }
            }
        }
    }

    private void checkCollisionRight(Tile tile, Entity entity) {
        if (tile.x >= entity.getPosition().getX()) {
            if (checkTileCollision(tile, entity)) {
                if (tile.isSolid()) {
                    entity.setPayerPosByCollision(tile.x - (Game.TILE_SIZE - Entity.HIT_BOX_X_OFFSET), entity.getPosition().getY());
                }
            }
        }
    }

    private void checkCollisionLeft(Tile tile, Entity entity) {
        if (tile.x < entity.getPosition().getX()) {
            if (checkTileCollision(tile, entity)) {
                if (tile.isSolid()) {
                    entity.setPayerPosByCollision(tile.x + (Game.TILE_SIZE - Entity.HIT_BOX_X_OFFSET), entity.getPosition().getY());
                }
            }
        }
    }

    //------------------------------------------------------------- ACTOR ACTIONS ----------------------------------------------------------------------------

    private void attackSkeleton() {
        Iterator<Skeleton> it = skeletons.iterator();
        while (it.hasNext()) {
            if (checkEntityCollision(player, it.next())) {
                System.out.println("HIT!!!!!!!!!");
                it.remove();
                break;
            }
        }
    }

    private void skeletonActions() {
        if (skeletons.size() != 0) {
            for (Skeleton skeleton : skeletons) {
                checkCollisions(skeleton);
                skeleton.move();
            }

        }
    }

    private void attackSpirit() {
        Iterator<Spirit> it = spirits.iterator();
        while (it.hasNext()) {
            if (checkEntityCollision(player, it.next())) {
                System.out.println("HIT!!!!!!!!!");
                it.remove();
                break;
            }
        }
    }

    private void spiritActions() {
        if (spirits.size() != 0) {
            for (Spirit spirit : spirits) {
                checkCollisions(spirit);
                spirit.checkPlayerInRange(player);
                checkCollisions(spirit);
                spirit.move(player);
            }
        }
    }
    //------------------------------------------------------------- UPDATE GAME STATE ----------------------------------------------------------------------------

    private void setPlayerDetails() {
        playerAttackDuration = player.getAttackDuration();
        playerCanMove = player.isMoving();
        playerIsAttacking = player.attack();
    }

    public void update() {
        checkCollisions(player);
        player.move();
        skeletonActions();
        spiritActions();
        if (player.attack()) {
            attackSkeleton();
            attackSpirit();
        }
        setPlayerDetails();
    }
}
