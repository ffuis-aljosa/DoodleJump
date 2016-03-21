package doodlejump;

import java.awt.Color;
import java.awt.Graphics2D;

public class DoodleGuy {
    private int x;
    private int y;
    private final int WIDTH = 50;
    private final int HEIGHT = 80;
    
    private int velocityX;
    private int velocityY;
    
    private final int GRAVITY = 1;

    public DoodleGuy(int panelWidth, int panelHeight) {
        this.x = (panelWidth - WIDTH) / 2;
        //this.y = (panelHeight - HEIGHT) / 2;
        this.y = 0;
        
        velocityX = 0;
        velocityY = 0;
    }
    
    public boolean isAtBottom(int panelHeight) {
        return y + HEIGHT >= panelHeight;
    }
    
    public void draw(Graphics2D g2D) {
        g2D.setPaint(Color.BLACK);
        g2D.fillRect(x, y, WIDTH, HEIGHT);
    }
    
    public void move() {
        velocityY += GRAVITY;
        
        x += velocityX;
        y += velocityY;
    }
    
    public void bounce() {
        velocityY = -30;
    }
}
