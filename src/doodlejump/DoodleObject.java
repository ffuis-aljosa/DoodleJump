package doodlejump;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public abstract class DoodleObject {
    protected int x;
    protected int y;
    protected int WIDTH;
    protected int HEIGHT;
    
    protected int velocityX;
    protected int velocityY;
    
    public static final int GRAVITY = 1;

    public DoodleObject(int x, int y, int WIDTH, int HEIGHT) {
        this.x = x;
        this.y = y;
        this.WIDTH = WIDTH;
        this.HEIGHT = HEIGHT;
        this.velocityX = 0;
        this.velocityY = 0;
    }
    
    public abstract void draw(Graphics2D g2D);
    
    public void move() {
        x += velocityX;
        y += velocityY;
    }
    
    public Rectangle2D.Double getRectangle() {
        return new Rectangle2D.Double(x, y, WIDTH, HEIGHT);
    }
    
    public boolean isIntersectingFromAbove(DoodleObject other) {
        int lowerEdge = this.y + this.HEIGHT;
        
        return lowerEdge >= other.y && lowerEdge < other.y + other.HEIGHT;
    }
    
    public void stopMoving() {
        velocityY = 0;
    }
}
