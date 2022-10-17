package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.logic.entities.Player;
import com.codecool.dungeoncrawl.logic.map.Map;
import com.codecool.dungeoncrawl.main.GamePanel;

import java.awt.*;

public class LogicHandler {

    private KeyHandler keyH;
    private MouseHandler mouseH;

    int screenWidth;

    int screenHeight;

    private final Map map;

    private final Player player;
    public LogicHandler(int width, int height) {
        map = new Map();
        screenWidth = width;
        screenHeight = height;
        this.keyH = new KeyHandler();
        this.mouseH = new MouseHandler();
        player = new Player("/player/Samurai.png", 1000,1000, 64, keyH, mouseH, screenWidth, screenHeight);
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
    }
    public void draw(Graphics2D g2d) {
        g2d.drawImage(map.getBackground().getSubimage(player.getPosition().getX()-(screenWidth/2-player.getEntitySize()/2),
                player.getPosition().getY()-(screenHeight/2- player.getEntitySize()/2),
                screenWidth, screenHeight), 0,0, null);
        g2d.drawImage(player.getAnimations()[(int) player.getAnimationIndexY()][(int) player.getAnimationIndexX()], player.getScreenX(), player.getScreenY(), null);
    }


}
