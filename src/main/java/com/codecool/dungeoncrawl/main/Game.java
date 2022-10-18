package com.codecool.dungeoncrawl.main;

import com.codecool.dungeoncrawl.logic.LogicHandler;

public class Game implements Runnable{

    private static final int ORIGINAL_TILE_SIZE = 16;
    private static final int SCALE = 4;
    public static final int TILE_SIZE = SCALE * ORIGINAL_TILE_SIZE;
    public static final int MAX_SCREEN_COL = 20;
    public static final int MAX_SCREEN_ROW = 12;
    public static final int SCREEN_WIDTH = TILE_SIZE * MAX_SCREEN_COL; //1280
    public static final int SCREEN_HEIGHT = TILE_SIZE * MAX_SCREEN_ROW; //800
    private static final int FPS_SET = 60;

    private static final int UPS_SET = 100;

    private Thread gameThread;

    private GameWindow gameWindow;
    private GamePanel gamePanel;

    private LogicHandler logicH;
    public Game() {
        logicH = new LogicHandler(SCREEN_WIDTH, SCREEN_HEIGHT);
        gamePanel = new GamePanel(logicH, SCREEN_WIDTH, SCREEN_HEIGHT);
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
