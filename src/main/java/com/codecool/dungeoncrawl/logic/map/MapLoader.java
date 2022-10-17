package com.codecool.dungeoncrawl.logic.map;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class MapLoader {

    public BufferedImage loadBackground() {
        BufferedImage image = null;
        InputStream is = getClass().getResourceAsStream("/background/ground-map.png");
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
        return image;
    }

}

