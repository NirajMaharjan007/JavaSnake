package resources.activities;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class OperatingPanel extends JPanel implements ActionListener {
    final static int width = 800,
            height = 800,
            tickSize = 50,
            boardSize = (width * height) / (tickSize * tickSize);

    int[] snakeX, snakeY;
    int snakeSize;

    char direction = 'R';
    boolean running = false;

    final Timer timer = new Timer(150, this);

    public OperatingPanel() {
        setLayout(new GridLayout());
        setBackground(Color.WHITE);
        setFocusable(true);
        setPreferredSize(new Dimension(width, height));
    }

    @Override
    public void paint(Graphics g) {
        super.paintComponent(g);
        g.setColor(new Color(200, 200, 200));
        for (int i = 0; i < height / tickSize; i++) {
            g.drawLine(i * tickSize, 0, i * tickSize, height);
            g.drawLine(0, i * tickSize, width, i * tickSize);
        }
        if (running) {

        }
    }

    public void drawSnake(Graphics g) {
        for (int i = 0; i < snakeSize; i++) {
            g.fillRect(snakeX[i], snakeY[i], tickSize, tickSize);
        }
    }

    public void start() {
        snakeX = new int[boardSize];
        snakeY = new int[boardSize];
        snakeSize = 5;
        running = true;
        direction = 'R';
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }
}
