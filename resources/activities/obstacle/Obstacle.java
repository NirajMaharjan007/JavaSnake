package resources.activities.obstacle;

import java.awt.*;
import java.util.Random;

public class Obstacle {
    private int width, height, x, y;
    private int size;
    private Random random = new Random();

    public Obstacle(int width, int height, int size) {
        this.width = width;
        this.height = height;
        this.size = size;
    }

    public void addObstacle(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(x, y, size, size);

    }

    public void setObstacle() {
        x = random.nextInt((int) (width / size)) * size;
        y = random.nextInt((int) (height / size)) * size;
    }

    public boolean isTouchObject(int headX, int headY) {
        if (headX == x && headY == y)
            return true;
        else
            return false;
    }

}
