package com.codecool.dungeoncrawl.logic.entities;

import com.codecool.dungeoncrawl.util.Direction;
import com.codecool.dungeoncrawl.util.ImageLoader;
import com.codecool.dungeoncrawl.util.Position;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public abstract class Entity extends Rectangle {




    protected int size;
    private BufferedImage image;

    protected BufferedImage[][] animations;

    protected double animationIndexX;
    protected double animationIndexY;
    protected Direction direction;

    protected Position position;

    public Entity(String characterUrl, int x, int y, int size) {
        position = new Position(x,y);
        direction = Direction.DOWN;
        this.size = size;
        image = ImageLoader.imageLoader(characterUrl);
        loadAnimations();

    }

    public double getAnimationIndexX() {
        return animationIndexX;
    }

    public double getAnimationIndexY() {
        return animationIndexY;
    }

    public Position getPosition() {
        return position;
    }


    public BufferedImage[][] getAnimations() {
        return animations;
    }

    protected void animate() {
        animationIndexX = direction.value;
        animationIndexY += 0.07;
        if (animationIndexY > 4) {
            animationIndexY = 0;
        }
    }


    public int getEntitySize() {
        return size;
    }
    public abstract void attack();
    public abstract void endAttack(long time);

    public abstract void move();

    protected void importImage(String url) {
        InputStream is = getClass().getResourceAsStream(url);
        try {
            image = ImageIO.read(is);
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

    private void loadAnimations() {
        animations = new BufferedImage[7][4];
        for (int j = 0; j < animations.length; j++) {
            for (int i = 0; i < animations[j].length; i++) {
                animations[j][i] = image.getSubimage(i* size, j*size, size, size);
            }
        }
    }
}
