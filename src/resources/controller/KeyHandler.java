package resources.controller;

import java.awt.event.*;

import resources.activities.OperatingPanel;

public class KeyHandler implements KeyListener {
    OperatingPanel panel;

    public boolean up, down, right, left;

    public KeyHandler(OperatingPanel panel) {
        this.panel = panel;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                up = true;
                break;

            case KeyEvent.VK_DOWN:
                down = true;
                break;

            case KeyEvent.VK_LEFT:
                left = true;
                break;

            case KeyEvent.VK_RIGHT:
                right = true;
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                up = false;
                break;

            case KeyEvent.VK_DOWN:
                down = false;
                break;

            case KeyEvent.VK_LEFT:
                left = false;
                break;

            case KeyEvent.VK_RIGHT:
                right = false;
                break;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

}
