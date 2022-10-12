package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.logic.entities.Entity;
import com.codecool.dungeoncrawl.logic.entities.Player;
import com.codecool.dungeoncrawl.main.GamePanel;

import java.awt.*;

public class LogicHandler {

    private KeyHandler keyH;
    private MouseHandler mouseH;

    Entity player;
    public LogicHandler() {
        this.keyH = new KeyHandler();
        this.mouseH = new MouseHandler();
        player = new Player("/Samurai.png", 100,100, 64, keyH);
    }


    public KeyHandler getKeyH() {
        return keyH;
    }

    public MouseHandler getMouseH() {
        return mouseH;
    }

    public void update() {
        player.move();
    }
    public void draw(Graphics2D g2d) {
        g2d.drawImage(player.getAnimations()[0][0], player.getPosition().getX(), player.getPosition().getY(), null);
    }


}
