package resources.activities;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class OperatingPanel extends JPanel implements ActionListener {
    final static int width = 600,
            height = 600,
            tickSize = 25,
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
        addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent event) {
                if (running) {
                    switch (event.getKeyCode()) {
                        case KeyEvent.VK_LEFT:
                            if (direction != 'R')
                                direction = 'L';
                            break;

                        case KeyEvent.VK_RIGHT:
                            if (direction != 'L')
                                direction = 'R';
                            break;

                        case KeyEvent.VK_UP:
                            if (direction != 'D')
                                direction = 'U';
                            break;

                        case KeyEvent.VK_DOWN:
                            if (direction != 'U')
                                direction = 'D';
                            break;
                    }
                }
            }
        });
    }

    @Override
    public void paint(Graphics g) {
        super.paintComponent(g);
        g.setColor(new Color(200, 200, 200));
        for (int i = 0; i < height / tickSize; i++) {
            g.drawLine(i * tickSize, 0, i * tickSize, height);
            g.drawLine(0, i * tickSize, width, i * tickSize);
        }

        g.setColor(Color.BLUE);
        start();
        if (running) {
            drawSnake(g);
        }
    }

    private void drawSnake(Graphics g) {
        for (int i = 0; i < snakeSize; i++) {
            g.fillRect(snakeX[i], snakeY[i], tickSize, tickSize);
        }
    }

    protected void move() {
        for (int i = snakeSize; i > 0; i--) {
            snakeX[i] = snakeX[i - 1];
            snakeY[i] = snakeY[i - 1];
        }

        switch (direction) {
            case 'U' -> snakeY[0] -= tickSize;
            case 'D' -> snakeY[0] += tickSize;
            case 'L' -> snakeX[0] -= tickSize;
            case 'R' -> snakeX[0] += tickSize;

        }
    }

    public void start() {
        snakeX = new int[boardSize];
        snakeY = new int[boardSize];
        snakeSize = 6;
        running = true;
        direction = 'R';
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (running) {
            move();
        }
        repaint();
    }
}
