package resources.activities;

import javax.swing.*;
import java.awt.*;

public class OperatingPanel extends JPanel {
    final static int width = 600, height = 600;

    public OperatingPanel() {
        setLayout(new GridLayout());
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(width, height));
    }

    @Override
    public void paint(Graphics g) {
        super.paintComponent(g);
    }
}
