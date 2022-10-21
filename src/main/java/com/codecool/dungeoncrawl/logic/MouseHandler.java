package com.codecool.dungeoncrawl.logic;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseHandler implements MouseListener, MouseMotionListener {

    private boolean buttonOnePressed = false;

    long attackTime;

    public boolean isButtonOnePressed() {
        return buttonOnePressed;
    }


    public long getAttackTime() {
        return attackTime;
    }

    public void setAttacking(boolean attacking) {
        this.buttonOnePressed = attacking;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
            if (e.getButton() == MouseEvent.BUTTON1){
                attackTime = System.currentTimeMillis();
                buttonOnePressed = true;
            }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
