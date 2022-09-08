package resources;

import resources.activities.*;

import javax.swing.*;

public class MainOperation extends JFrame {
    public MainOperation() {
        setTitle("Snake Game");
        setVisible(true);
        setResizable(false);
        setFocusable(true);
        setAlwaysOnTop(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocation(600, 100);
        add(new OperatingPanel());
        pack();
    }
}
