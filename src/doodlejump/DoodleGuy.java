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
    private final int HORIZONTAL_SPEED = 10;
    
    private final int TELEPORT_BUFFER = 40;

    public DoodleGuy(int panelWidth, int panelHeight) {
        this.x = (panelWidth - WIDTH) / 2;
        //this.y = (panelHeight - HEIGHT) / 2;
        this.y = 0;
        
        velocityX = 0;
        velocityY = 0;
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
