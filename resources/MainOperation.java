package resources;

import resources.activities.*;

import javax.swing.*;

public class MainOperation extends JFrame {
    public MainOperation() {
        System.out.println("Operation.Operation()");

        setTitle("Snake Game");
        setVisible(true);
        setResizable(false);
        setFocusable(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        add(new OperatingPanel());
        pack();
    }
}
