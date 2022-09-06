package resources.activities;

import javax.swing.*;

import resources.activities.obstacle.Obstacle;

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
    int snakeSize = 4, count = 0;

    char direction = 'R';
    boolean running = false;

    Timer timer;

    int appleX, appleY;

    Random random = new Random();

    Obstacle o = new Obstacle(width, height, tickSize);

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
        Font font = new Font("Times New Roman", Font.BOLD, 25);

        if (running) {
            g.setColor(new Color(200, 200, 200));
            // for (int i = 0; i < height / tickSize; i++) {
            // g.drawLine(i * tickSize, 0, i * tickSize, height);
            // g.drawLine(0, i * tickSize, width, i * tickSize);
            // }

            drawSnake(g);
            drawApple(g);
            o.addObstacle(g);

            g.setColor(Color.BLACK);
            g.setFont(font);

            g.drawString("Score: " + count, 250, 50);
        } else
            gameOver(g);
    }

    private void drawSnake(Graphics g) {
        for (int i = 0; i < snakeSize; i++) {
            if (i == 0) {
                g.setColor(new Color(0, 180, 0));
                g.fillRect(snakeX[i], snakeY[i], tickSize, tickSize);
            } else {
                g.setColor(new Color(100, 236, 100));
                g.fillRect(snakeX[i], snakeY[i], tickSize, tickSize);
            }
        }
    }

    private void drawApple(Graphics g) {
        g.setColor(Color.ORANGE);
        g.fillOval(appleX, appleY, tickSize, tickSize);
    }

    protected void setApple() {
        appleX = random.nextInt((int) (width / tickSize)) * tickSize;
        appleY = random.nextInt((int) (height / tickSize)) * tickSize;
    }

    protected void checkApple() {
        if (snakeX[0] == appleX && snakeY[0] == appleY) {
            setApple();
            o.setObstacle();
            count++;
            snakeSize++;
        }
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
        o.setObstacle();
        running = true;
        timer = new Timer(80, this);
        timer.start();
    }

    protected void gameOver(Graphics g) {
        Font font = new Font("Arial", Font.BOLD, 30);
        g.setColor(Color.red);
        g.setFont(font);

        FontMetrics metrics2 = getFontMetrics(g.getFont());
        g.drawString("Score: " + count, (width - metrics2.stringWidth("Score: " + count)) / 2, 50);

        FontMetrics metrics = getFontMetrics(g.getFont());
        String str = "GAME OVER";
        g.drawString(str, (width - metrics.stringWidth(str)) / 2, height / 2);
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

        for (int i = snakeSize; i > 0; i--)
            if ((snakeX[0] == snakeX[i]) && (snakeY[0] == snakeY[i]))
                running = false;

        if (o.isTouchObject(snakeX[0], snakeY[0]))
            running = false;

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (running) {
            move();
            checkApple();
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
