package resources.activities;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class OperatingPanel extends JPanel implements ActionListener {
    final static int width = 600,
            height = 600,
            tickSize = 25,
            boardSize = (width * height) / tickSize;

    int[] snakeX = new int[boardSize],
            snakeY = new int[boardSize];
    int snakeSize = 5;

    char direction = 'R';
    boolean running = false;

    Timer timer;

    int appleX, appleY;

    Random random = new Random();

    public OperatingPanel() {
        System.out.println("OperatingPanel.OperatingPanel()");
        new Controller();
        start();
        setLayout(new FlowLayout());
        setBackground(Color.WHITE);
        setFocusable(true);
        setPreferredSize(new Dimension(width, height));
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(new Color(200, 200, 200));
        for (int i = 0; i < height / tickSize; i++) {
            g.drawLine(i * tickSize, 0, i * tickSize, height);
            g.drawLine(0, i * tickSize, width, i * tickSize);
        }

        drawSnake(g);
        drawApple(g);
    }

    private void drawSnake(Graphics g) {
        for (int i = 0; i < snakeSize; i++) {
            if (i == 0) {
                g.setColor(Color.green);
                g.fillRect(snakeX[i], snakeY[i], tickSize, tickSize);
            } else {
                g.setColor(Color.pink);
                g.fillRect(snakeX[i], snakeY[i], tickSize, tickSize);
            }
        }
    }

    private void drawApple(Graphics g) {
        g.setColor(Color.RED);
        g.fillOval(appleX, appleY, tickSize, tickSize);
    }

    public void setApple() {
        appleX = random.nextInt((int) (width / tickSize)) * tickSize;
        appleY = random.nextInt((int) (height / tickSize)) * tickSize;
    }

    protected void move() {
        for (int i = snakeSize; i > 0; i--) {
            snakeX[i] = snakeX[i - 1];
            snakeY[i] = snakeY[i - 1];
        }

        switch (direction) {
            case 'U':
                snakeY[0] -= tickSize;
                break;

            case 'D':
                snakeY[0] += tickSize;
                break;

            case 'L':
                snakeX[0] -= tickSize;
                break;

            case 'R':
                snakeX[0] += tickSize;
                break;

        }
    }

    public void start() {
        setApple();
        running = true;
        timer = new Timer(80, this);
        timer.start();
    }

    private void setCollision() {
        // if (x > 575)
        if (snakeX[0] > (width - tickSize))
            snakeX[0] = 0;

        // if (y > 575)
        if (snakeY[0] > (height - tickSize))
            snakeY[0] = 0;

        if (snakeY[0] < 0)
            snakeY[0] = (height - tickSize);

        if (snakeX[0] < 0)
            snakeX[0] = (width - tickSize);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (running) {
            move();
            setCollision();
        }
        repaint();
    }

    protected class Controller extends JFrame {
        TextField control;
        JLabel label;

        Controller() {
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setVisible(true);
            setResizable(true);
            setFocusable(true);
            setTitle("Controller");
            setSize(300, 100);

            control = new TextField(10);
            label = new JLabel("", JLabel.CENTER);

            control.setEditable(false);

            JPanel panel = new JPanel(new GridLayout(2, 1, 5, 5));

            panel.add(control);
            panel.add(label);

            add(panel);

            control.addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent event) {
                    if (running) {
                        switch (event.getKeyCode()) {
                            case KeyEvent.VK_LEFT:
                                label.setText("Left");
                                if (direction != 'R')
                                    direction = 'L';
                                break;

                            case KeyEvent.VK_RIGHT:
                                label.setText("Right");
                                if (direction != 'L')
                                    direction = 'R';
                                break;

                            case KeyEvent.VK_UP:
                                label.setText("Up");
                                if (direction != 'D')
                                    direction = 'U';
                                break;

                            case KeyEvent.VK_DOWN:
                                label.setText("Down");
                                if (direction != 'U')
                                    direction = 'D';
                                break;
                        }
                    }
                }
            });
        }
    }
}
