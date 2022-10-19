package com.codecool.dungeoncrawl.main;

import com.codecool.dungeoncrawl.logic.LogicHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class GamePanel extends JPanel {

    private final int width;
    private final int height;
    LogicHandler logicH;

    //player animation details

    private  double playerAnimationIndexX;

    private  double playerAnimationIndexY;

    public GamePanel(LogicHandler logicH, int width, int height) {
        this.width = width;
        this.height = height;
        this.logicH = logicH;
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
            playerAnimationIndexX = logicH.getPlayerDirection().value;
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

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        BufferedImage[][] playerAnimationGrid = getAnimationFrames(logicH.getPlayerImage());
        animatePlayer();
        g2d.drawImage(logicH.getCurrentLevelBackGround().getSubimage(logicH.getPlayerPosition().getX()-(Game.SCREEN_WIDTH /2-Game.TILE_SIZE/2), logicH.getPlayerPosition().getY() -(Game.SCREEN_HEIGHT/2- Game.TILE_SIZE/2),
                Game.SCREEN_WIDTH, Game.SCREEN_HEIGHT), 0,0, null);
        g2d.drawImage(logicH.getSkeletonImage().getSubimage(0,0,Game.TILE_SIZE,Game.TILE_SIZE),logicH.getSkeletonPosition().getX(),logicH.getSkeletonPosition().getY(),null);
        g2d.drawImage(playerAnimationGrid[(int) playerAnimationIndexY][(int) playerAnimationIndexX],
                Game.SCREEN_WIDTH /2 -(Game.TILE_SIZE/2),
                Game.SCREEN_HEIGHT/2 - (Game.TILE_SIZE/2), null);

    }
}
