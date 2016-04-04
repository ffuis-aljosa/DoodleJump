package doodlejump;

import java.awt.Color;
import java.awt.Graphics2D;

public class DoodleGuy extends DoodleObject {
    private final int HORIZONTAL_SPEED = 10;
    
    private final int TELEPORT_BUFFER = 40;

    public DoodleGuy(int panelWidth, int panelHeight) {
        super(0, 0, 50, 80);
        
        this.x = (panelWidth - WIDTH) / 2;
        this.y = (panelHeight - HEIGHT) / 2;
    }
    
    public void moveLeft() {
        velocityX = -HORIZONTAL_SPEED;
    }
    
    public void moveRight() {
        velocityX = HORIZONTAL_SPEED;
    }
    
    public void stopHorizontal() {
        velocityX = 0;
    }
    
    public boolean isAtBottom(int panelHeight) {
        return y + HEIGHT >= panelHeight;
    }
    
    public boolean isMaxLeft() {
        return x <= -TELEPORT_BUFFER;
    }
    
    public boolean isMaxRight(int panelWidth) {
        return x + WIDTH >= panelWidth + TELEPORT_BUFFER;
    }
    
    public void teleportRight(int panelWidth) {
        x = panelWidth - WIDTH;
    }
    
    public void teleportLeft() {
        x = 0;
    }
    
    @Override
    public void draw(Graphics2D g2D) {
        g2D.setPaint(Color.BLACK);
        g2D.fillRect(x, y, WIDTH, HEIGHT);
    }
    
    @Override
    public void move() {
        velocityY += GRAVITY;
        
        super.move();
    }
    
    public void bounce() {
        velocityY = -30;
    }
    
    public boolean isGoingDown() {
        return velocityY > 0;
    }
}
