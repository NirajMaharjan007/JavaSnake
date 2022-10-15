package resources;

import resources.activities.*;

import javax.swing.*;

public class MainOperation extends JFrame {
    public MainOperation() {
        setTitle("Snake Game");
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(new OperatingPanel());
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setAlwaysOnTop(true);
    }
}
