package com.codecool.dungeoncrawl.logic.entities;

import com.codecool.dungeoncrawl.logic.KeyHandler;
import com.codecool.dungeoncrawl.util.Direction;

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
        speed = 2;
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
            direction = Direction.UP;
            animate();
        }
        if (keyH.isDown()){
            position.setY(position.getY() + speed);
            direction = Direction.DOWN;
            animate();
        }
        if (keyH.isRight()){
            position.setX(position.getX() + speed);
            direction = Direction.RIGHT;
            animate();
        }
        if (keyH.isLeft()){
            position.setX(position.getX() - speed);
            direction = Direction.LEFT;
            animate();
        }

    }

    @Override
    protected void animate() {
        animationIndexX = direction.value;
        animationIndexY += 0.07;
        if (animationIndexY > 4) {
            animationIndexY = 0;
        }
    }
}
