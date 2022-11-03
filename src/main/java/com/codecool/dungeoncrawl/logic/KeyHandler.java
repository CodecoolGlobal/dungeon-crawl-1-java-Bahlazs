package com.codecool.dungeoncrawl.logic;


import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class KeyHandler implements KeyListener{

    private boolean up, down, right, left, mPressed, ePressed, lPressed, escPressed, enterPressed; // TODO will have pickup

    public boolean isUp() {
        return up;
    }

    public boolean isDown() {
        return down;
    }

    public boolean isRight() {
        return right;
    }

    public boolean isLeft() { return left; }

    public boolean mIsPressed() {
        return mPressed;
    }

    public boolean lIsPressed() {
        return lPressed;
    }

    public boolean isEscPressed() {
        return escPressed;
    }

    public boolean isEnterPressed() {
        return enterPressed;
    }

    public boolean eIsPressed() {
        return ePressed;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_W) {
            up = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_S) {
            down = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_D) {
            right = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_A) {
            left = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_M) {
            mPressed = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_L) {
            lPressed = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_E) {
            ePressed = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            escPressed = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            enterPressed = false;
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_W) {
            up = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_S) {
            down = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_D) {
            right = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_A) {
            left = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_M) {
            mPressed = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_L) {
            lPressed = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_E) {
            ePressed = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            escPressed = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            enterPressed = true;
        }
    }
}
