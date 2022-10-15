package resources.activities.obstacle;

import java.awt.*;
import java.util.Random;

public class Obstacle {
    private int width, height, x, y;
    private int size;
    private Random random = new Random();

    private int w_size = random.nextInt(2, 7),
            h_size = random.nextInt(2, 7);

    public Obstacle(int width, int height, int size) {
        this.width = width;
        this.height = height;
        this.size = size;
    }

    public void addObstacle(Graphics g, int appleX, int appleY) {
        g.setColor(Color.RED);
        if (appleX != x && appleY != y) {
            if (x < width && y < height) {
                g.fillRect(x, y, size * w_size, size * h_size);
            } else
                g.fillRect(x, y, size, size);
        }

    }

    public void setObstacle() {
        x = random.nextInt((int) (width / size)) * size;
        y = random.nextInt((int) (height / size)) * size;
    }

    public boolean isTouchObject(int headX, int headY) {
        Rectangle ob = new Rectangle(x, y, size * w_size, size * h_size);
        Rectangle snakeHead = new Rectangle(headX, headY, size, size);

        if (x < width && y > height) {
            if (headX == x)
                return true;
            else if (headY == y)
                return true;
        }

        if (snakeHead.intersects(ob))
            return true;

        else
            return false;
    }

}
