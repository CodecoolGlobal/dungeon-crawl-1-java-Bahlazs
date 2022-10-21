package com.codecool.dungeoncrawl.main;

import com.codecool.dungeoncrawl.logic.Drawable;
import com.codecool.dungeoncrawl.logic.LogicHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;

public class GamePanel extends JPanel {

    private final int width;
    private final int height;
    LogicHandler logicH;

    //actors
    private List<Drawable> enemies;
    private Drawable player;

    //player animation details
    private  double playerAnimationIndexX;
    private  double playerAnimationIndexY;


    public GamePanel(LogicHandler logicH, int width, int height) {
        this.width = width;
        this.height = height;
        this.logicH = logicH;
        player = logicH.getPlayer();
        enemies = logicH.getEnemies();
        setPanelSize();
        setDoubleBuffered(true);
        addKeyListener(logicH.getKeyH());
        addMouseListener(logicH.getMouseH());
        addMouseMotionListener(logicH.getMouseH());

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

    private BufferedImage[][] getAnimationFrames(BufferedImage image) {
        BufferedImage[][] animationGrid = new BufferedImage[image.getHeight()/Game.TILE_SIZE][image.getWidth()/Game.TILE_SIZE];
        for (int j = 0; j < animationGrid.length; j++) {
            for (int i = 0; i < animationGrid[j].length; i++) {
                animationGrid[j][i] = image.getSubimage(i* Game.TILE_SIZE, j*Game.TILE_SIZE, Game.TILE_SIZE, Game.TILE_SIZE);
            }
        }
        return animationGrid;
    }

    private void animatePlayer() {
        if (logicH.getKeyH().isLeft() || logicH.getKeyH().isRight() || logicH.getKeyH().isUp() || logicH.getKeyH().isDown()) {
            playerAnimationIndexX = player.getDirection().value;
            playerAnimationIndexY += 0.1;
            if (playerAnimationIndexY > 4) {
                playerAnimationIndexY = 0;
            }
        }
        if (logicH.getMouseH().isButtonOnePressed()) {
            playerAnimationIndexY = 4;
        }
        if (!logicH.canPlayerMove()) {
            if ((logicH.getMouseH().getAttackTime() + (logicH.getPlayerAttackDuration()*1000) <= System.currentTimeMillis())) {
                playerAnimationIndexY = 0;
            }
        }
    }

    private void drawCameraView(Graphics2D g2d) {
        BufferedImage levelBackground = logicH.getCurrentLevelBackGround();
        int cameraX = player.getPosition().getX() - (Game.SCREEN_WIDTH/2 - Game.TILE_SIZE/2);
        int cameraY = player.getPosition().getY() - (Game.SCREEN_HEIGHT/2 - Game.TILE_SIZE/2);
        g2d.drawImage(levelBackground.getSubimage(cameraX, cameraY,
                Game.SCREEN_WIDTH, Game.SCREEN_HEIGHT), 0,0, null);
    }

    private void drawPlayer(Graphics2D g2d) {
        BufferedImage[][] playerAnimationGrid = getAnimationFrames(player.getImage());
        g2d.drawImage(playerAnimationGrid[(int) playerAnimationIndexY][(int) playerAnimationIndexX],
                Game.SCREEN_WIDTH /2 -(Game.TILE_SIZE/2),
                Game.SCREEN_HEIGHT/2 - (Game.TILE_SIZE/2), null);
    }

    private void drawEnemies(Graphics2D g2d) {
        if(enemies.size() != 0) {
            for (Drawable enemy : enemies) {
                int enemyXScreenPos = enemy.getPosition().getX() - player.getPosition().getX() + Game.SCREEN_WIDTH / 2 - (Game.TILE_SIZE / 2);
                int enemyYScreenPos = enemy.getPosition().getY() - player.getPosition().getY() + Game.SCREEN_HEIGHT / 2 - (Game.TILE_SIZE / 2);
                g2d.drawImage(enemy.getImage().getSubimage(0, 0, Game.TILE_SIZE, Game.TILE_SIZE),
                enemyXScreenPos,
                enemyYScreenPos, null);
            }
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        animatePlayer();
        drawCameraView(g2d);
        drawEnemies(g2d);
        drawPlayer(g2d);

    }


}
