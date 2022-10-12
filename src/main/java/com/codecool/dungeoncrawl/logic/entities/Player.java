package com.codecool.dungeoncrawl.logic.entities;

import com.codecool.dungeoncrawl.logic.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class Player extends  Entity{

    private final KeyHandler keyH;

    private BufferedImage armoredImage;
    private int speed;

    public Player(String url, int x, int y, int size, KeyHandler keyH) {
        super(url, x, y, size);
        this.keyH = keyH;
        speed = 5;
        importArmorImage();
    }

    private void importArmorImage() {
        InputStream is = getClass().getResourceAsStream("/Samurai-armored.png");
        try {
            armoredImage = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void move() {
        if (keyH.isUp()){
            position.setY(position.getY() - speed);
        }
        if (keyH.isDown()){
            position.setY(position.getY() + speed);
        }
        if (keyH.isRight()){
            position.setX(position.getX() + speed);
        }
        if (keyH.isLeft()){
            position.setX(position.getX() - speed);
        }

    }
}
