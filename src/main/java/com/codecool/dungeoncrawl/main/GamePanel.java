package com.codecool.dungeoncrawl.main;

import com.codecool.dungeoncrawl.logic.LogicHandler;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {

    private int width;
    private int height;
    LogicHandler logicH;
    public GamePanel(LogicHandler logicH, int width, int height) {
        this.width = width;
        this.height = height;
        this.logicH = logicH;
        setPanelSize();
        setDoubleBuffered(true);
        addKeyListener(logicH.getKeyH());
        addMouseListener(logicH.getMouseH());
        addMouseMotionListener(logicH.getMouseH());

    }

    private void setPanelSize() {
        Dimension size = new Dimension(width, height);
        setPreferredSize(size);
    }



    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        logicH.draw(g2d);

    }
}
