package com.codecool.dungeoncrawl.main;

import javax.swing.*;

public class GameWindow extends JFrame {

    public GameWindow(GamePanel gamePanel) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(gamePanel);
        setResizable(false);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);

    }
}
