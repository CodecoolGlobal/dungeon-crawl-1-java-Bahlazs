package com.codecool.dungeoncrawl.main;

import com.codecool.dungeoncrawl.logic.Drawable;
import com.codecool.dungeoncrawl.logic.DrawableItem;
import com.codecool.dungeoncrawl.logic.LogicHandler;
import com.codecool.dungeoncrawl.logic.entities.Player;
import com.codecool.dungeoncrawl.logic.entities.Skeleton;
import com.codecool.dungeoncrawl.logic.entities.Spirit;
import com.codecool.dungeoncrawl.logic.items.Armor;
import com.codecool.dungeoncrawl.logic.items.Key;
import com.codecool.dungeoncrawl.logic.items.Potion;
import com.codecool.dungeoncrawl.util.ImageLoader;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;

public class GamePanel extends JPanel {

    private final int width;
    private final int height;
    LogicHandler logicH;

    //actors

    private Drawable player;

    private List<Drawable> enemies;

    private List<DrawableItem> items;

    //player animation details
    private BufferedImage playerImage;

    private BufferedImage playerArmoredImage;

    private  BufferedImage skeletonImage;
    private  BufferedImage spiritImage;
    private  BufferedImage potionImage;
    private  BufferedImage keyImage;
    private  BufferedImage armorImage;
    private  BufferedImage levelBackgroundImage;
    private double playerAnimationIndexX;
    private double playerAnimationIndexY;


    public GamePanel(LogicHandler logicH, int width, int height) {
        this.width = width;
        this.height = height;
        this.logicH = logicH;
        player = logicH.getPlayer();
        loadImages();
        enemies = logicH.getEnemies();
        items = logicH.getItems();
        setPanelSize();
        setDoubleBuffered(true);
        addKeyListener(logicH.getKeyHandler());
        addMouseListener(logicH.getMouseHandler());
        addMouseMotionListener(logicH.getMouseHandler());

    }

    public void setPlayerAfterLoad(Drawable player) {
        this.player = player;
    }


    public void setEnemiesAfterLoad(List<Drawable> enemies) {
        this.enemies = enemies;
    }

    public void setItemsAfreLoad(List<DrawableItem> items) {
        this.items = items;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    private void setPanelSize() {
        Dimension size = new Dimension(width, height);
        setPreferredSize(size);
    }

    private void loadImages() {
        playerImage = ImageLoader.loadImage(player.getImageUrl());
        playerArmoredImage = ImageLoader.loadImage(Player.ARMORED_CHARACTER_URL);
        skeletonImage = ImageLoader.loadImage(Skeleton.SKELETON_URL);
        spiritImage = ImageLoader.loadImage(Spirit.SPIRIT_URL);
        potionImage = ImageLoader.loadImage(Potion.POTION_URL);
        keyImage = ImageLoader.loadImage(Key.KEY_URL);
        armorImage = ImageLoader.loadImage(Armor.ARMOR_URL);
        levelBackgroundImage = ImageLoader.loadImage(LogicHandler.LEVEL_1_BACKGROUND_URL);
    }

    public void updateObjectDrawStatus() {
        enemies = logicH.getEnemies();
        items = logicH.getItems();
        if (logicH.checkPayerArmorStatus()) {
            playerImage = playerArmoredImage;
        }
    }

    private BufferedImage[][] getAnimationFrames(BufferedImage image) {
        BufferedImage[][] animationGrid = new BufferedImage[image.getHeight() / Game.TILE_SIZE][image.getWidth() / Game.TILE_SIZE];
        for (int j = 0; j < animationGrid.length; j++) {
            for (int i = 0; i < animationGrid[j].length; i++) {
                animationGrid[j][i] = image.getSubimage(i * Game.TILE_SIZE, j * Game.TILE_SIZE, Game.TILE_SIZE, Game.TILE_SIZE);
            }
        }
        return animationGrid;
    }

    private void animatePlayer() {
        if (logicH.getKeyHandler().isLeft() || logicH.getKeyHandler().isRight() || logicH.getKeyHandler().isUp() || logicH.getKeyHandler().isDown()) {
            playerAnimationIndexX = player.getDirection().value;
            playerAnimationIndexY += 0.1;
            if (playerAnimationIndexY > 4) {
                playerAnimationIndexY = 0;
            }
        }
        if (logicH.playerIsAttacking()) {
            playerAnimationIndexY = 4;
        }
        if (!logicH.playerIsAttacking() && playerAnimationIndexY >= 4) {
            playerAnimationIndexY = 0;
        }
    }

    private void drawCameraView(Graphics2D g2d) {
        int cameraX = player.getPosition().getX() - (Game.SCREEN_WIDTH / 2 - Game.TILE_SIZE / 2);
        int cameraY = player.getPosition().getY() - (Game.SCREEN_HEIGHT / 2 - Game.TILE_SIZE / 2);
        g2d.drawImage(levelBackgroundImage.getSubimage(cameraX, cameraY,
                Game.SCREEN_WIDTH, Game.SCREEN_HEIGHT), 0, 0, null);
    }

    private void drawPlayer(Graphics2D g2d) {
        BufferedImage[][] playerAnimationGrid = getAnimationFrames(playerImage);
        g2d.drawImage(playerAnimationGrid[(int) playerAnimationIndexY][(int) playerAnimationIndexX],
                Game.SCREEN_WIDTH / 2 - (Game.TILE_SIZE / 2),
                Game.SCREEN_HEIGHT / 2 - (Game.TILE_SIZE / 2), null);
    }

    private void drawEnemies(Graphics2D g2d) {
        if (enemies.size() != 0) {
            BufferedImage enemyImage;
            for (Drawable enemy : enemies) {
                if (enemy instanceof Skeleton) {
                    enemyImage = skeletonImage;
                } else {
                    enemyImage = spiritImage;
                }
                int enemyXScreenPos = enemy.getPosition().getX() - player.getPosition().getX() + Game.SCREEN_WIDTH / 2 - (Game.TILE_SIZE / 2);
                int enemyYScreenPos = enemy.getPosition().getY() - player.getPosition().getY() + Game.SCREEN_HEIGHT / 2 - (Game.TILE_SIZE / 2);
                g2d.drawImage(enemyImage.getSubimage(0, 0, Game.TILE_SIZE, Game.TILE_SIZE),
                        enemyXScreenPos,
                        enemyYScreenPos, null);
            }
        }
    }

    private void drawItems(Graphics2D g2d) {
        if (items.size() != 0) {
            BufferedImage itemImage;
            for (DrawableItem item : items) {
                if (item instanceof Potion) {
                    itemImage = potionImage;
                } else if (item instanceof Key) {
                    itemImage = keyImage;
                } else {
                    itemImage = armorImage;
                }
                int itemXScreenPos = item.getPosition().getX() - player.getPosition().getX() + Game.SCREEN_WIDTH / 2 - (Game.TILE_SIZE / 2);
                int itemYScreenPos = item.getPosition().getY() - player.getPosition().getY() + Game.SCREEN_HEIGHT / 2 - (Game.TILE_SIZE / 2);
                g2d.drawImage(itemImage, itemXScreenPos, itemYScreenPos, null);
            }
        }
    }

    private void drawPlayerStats(Graphics2D g2d) {
        g2d.setFont(new Font("Arial", Font.BOLD, 24));
        g2d.setColor(Color.WHITE);
        g2d.drawString("HP: " + logicH.getPlayerMaxHp() + " / " + player.getHp(), 30, 30);

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        animatePlayer();
        drawCameraView(g2d);
        drawEnemies(g2d);
        drawItems(g2d);
        drawPlayer(g2d);
        drawPlayerStats(g2d);
        g2d.dispose();

    }


}
