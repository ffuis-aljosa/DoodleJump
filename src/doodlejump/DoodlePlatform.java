package doodlejump;

import java.awt.Graphics2D;

public class DoodlePlatform extends DoodleObject {
    public DoodlePlatform(int x, int y) {
        super(x, y, 100, 20);
    }
    
    @Override
    public void draw(Graphics2D g2d)
    {
        g2d.drawRect(x, y, WIDTH, HEIGHT);
    }

    @Override
    public void move() {
        velocityY -= GRAVITY;
        
        super.move();
    }
    
    public void startMoving() {
        velocityY = 30;
    }
}
