package com.codecool.dungeoncrawl.main;

import com.codecool.dungeoncrawl.logic.LogicHandler;

public class Game implements Runnable{

    private final int originalTileSize = 16;
    private final int scale = 4;
    private final int tilSize = scale * originalTileSize;

    private final int FPS_SET = 60;
    private final int UPS_SET = 100;

    private Thread gameThread;

    private final int width = tilSize * 20;
    private final int height = tilSize * 12;
    private GameWindow gameWindow;
    private GamePanel gamePanel;

    private LogicHandler logicH;
    public Game() {
        logicH = new LogicHandler(width, height);
        gamePanel = new GamePanel(logicH, width, height);
        gameWindow = new GameWindow(gamePanel);
        gamePanel.requestFocus();
        startGameThread();
    }

    private void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void fpsCounter(int frames, long lastChecked) {
        frames++;
        if (System.currentTimeMillis()-lastChecked >= 1000) {
            lastChecked = System.currentTimeMillis();
            System.out.println("FPS:" + frames);
            frames = 0;
        }
    }

    @Override
    public void run() {
        double timePerFrame = 1000000000.0 / FPS_SET;
        double timePerUpdate = 1000000000.0 / UPS_SET;
        long lastTime = System.nanoTime();
        int frames = 0;
        int updates = 0;
        long lastChecked = System.currentTimeMillis();

        double deltaU = 0;
        double deltaF = 0;

        while (true) {
            long currentTime = System.nanoTime();

            deltaU += (currentTime - lastTime) / timePerUpdate;
            deltaF += (currentTime - lastTime) / timePerFrame;
            lastTime = currentTime;

            if (deltaF >= 1) {
                gamePanel.repaint();
                frames++;
                deltaF--;
            }

            if (deltaU >= 1) {
                logicH.update();
                updates++;
                deltaU--;
            }

            if (System.currentTimeMillis()-lastChecked >= 1000) {
                lastChecked = System.currentTimeMillis();
                System.out.println("FPS:" + frames + " | UPS:" + updates);
                frames = 0;
                updates = 0;
            }
        }
    }
}
