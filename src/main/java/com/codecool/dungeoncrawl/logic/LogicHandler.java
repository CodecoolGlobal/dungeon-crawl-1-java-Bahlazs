package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.logic.entities.Player;
import com.codecool.dungeoncrawl.logic.map.Level;
import com.codecool.dungeoncrawl.main.Game;

import java.awt.*;

public class LogicHandler {

    private KeyHandler keyH;
    private MouseHandler mouseH;


    private final Level map;

    private final Player player;
    public LogicHandler(int width, int height) {
        map = new Level();
        this.keyH = new KeyHandler();
        this.mouseH = new MouseHandler();
        player = new Player( 1000,1000, 64, keyH, mouseH);
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
        g2d.drawImage(map.getBackground().getSubimage(player.getPosition().getX()-(Game.SCREEN_WIDTH /2-player.getEntitySize()/2),
                player.getPosition().getY()-(Game.SCREEN_HEIGHT/2- player.getEntitySize()/2),
                Game.SCREEN_WIDTH, Game.SCREEN_HEIGHT), 0,0, null);
        g2d.drawImage(player.getAnimations()[(int) player.getAnimationIndexY()][(int) player.getAnimationIndexX()],
                player.getScreenX(),
                player.getPLAYER_SCREEN_POS_Y(), null);
    }


}
