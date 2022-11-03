package com.codecool.dungeoncrawl.main;

import com.codecool.dungeoncrawl.dao.Serializer;
import com.codecool.dungeoncrawl.logic.KeyHandler;
import com.codecool.dungeoncrawl.logic.LogicHandler;
import com.codecool.dungeoncrawl.logic.MouseHandler;
import com.codecool.dungeoncrawl.logic.map.Level;
import com.google.gson.Gson;

public class Game implements Runnable {

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

    private boolean gameIsActive;

    private final GamePanel gamePanel;

    private final LogicHandler logicHandler;

    private final MouseHandler mouseHandler;

    private final KeyHandler keyHandler;

    private final Gson gson;

    public Game() {
        gson = Serializer.createGson();
        keyHandler = new KeyHandler();
        mouseHandler = new MouseHandler();
        logicHandler = new LogicHandler(keyHandler, mouseHandler);
        gamePanel = new GamePanel(logicHandler, SCREEN_WIDTH, SCREEN_HEIGHT);
        new GameWindow(gamePanel);
        gamePanel.requestFocus();
        startGameThread();
    }

    public boolean isGameIsActive() {
        return gameIsActive;
    }

    private void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
        gameIsActive = true;
    }

    private void pauseGame() {
        gameIsActive = false;
    }

    private void resumeGame() {
        gameIsActive = true;
    }


    public void saveGame() {
        Level currentLevel = logicHandler.getLevel();
        Serializer.serialize(currentLevel, gson);
    }

    public void loadGame() {
        Level loadedLevel = Serializer.getLevelFromJSON(gson);
        logicHandler.setCurrentLevel(loadedLevel);
        gamePanel.setPlayerAfterLoad(logicHandler.getPlayer());
        gamePanel.setItemsAfreLoad(logicHandler.getItems());
        gamePanel.setEnemiesAfterLoad(logicHandler.getEnemies());
    }

    private void update() {

    }

    private void hotKeyHandler() {
        if (!gameIsActive && keyHandler.isEnterPressed()) {
            resumeGame();
        }
        if (keyHandler.mIsPressed()){
            saveGame();
        }
        if (keyHandler.lIsPressed()) {
            loadGame();
        }
    }

    // Game loop
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


        while (gameThread != null) {

            long currentTime = System.nanoTime();

            deltaU += (currentTime - lastTime) / timePerUpdate;
            deltaF += (currentTime - lastTime) / timePerFrame;
            lastTime = currentTime;


            if (deltaU >= 1) {
                if (gameIsActive) {
                    logicHandler.update();
                    gamePanel.updateObjectDrawStatus();
                }
                if (keyHandler.isEscPressed()) {
                    pauseGame();
                }
                updates++;
                deltaU--;
            }
            hotKeyHandler();

            if (deltaF >= 1) {
                if (gameIsActive) {
                    gamePanel.repaint();
                }
                frames++;
                deltaF--;
            }

            if (System.currentTimeMillis() - lastChecked >= 1000) {
                lastChecked = System.currentTimeMillis();
//                    System.out.println("FPS:" + frames + " | UPS:" + updates);
//                    frames = 0;
//                    updates = 0;
            }
        }
    }
}
