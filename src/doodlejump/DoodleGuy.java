package doodlejump;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class DoodleGuy extends DoodleObject {
    private final int HORIZONTAL_SPEED = 10;
    
    private final int TELEPORT_BUFFER = 40;
    
    protected static BufferedImage image;

    public DoodleGuy(int panelWidth, int panelHeight) {
        super(0, 0, 60, 59);
        
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
        g2D.drawImage(image, x, y, WIDTH, HEIGHT, null);
    }
    
    @Override
    public void move() {
        super.move();
    }
    
    public void bounce() {
        velocityY = -30;
    }
    
    public boolean isGoingDown() {
        return velocityY > 0;
    }
    
    public static void loadImage() {
        try {
            String imagepath = "src/images/doodleGuy.png";
            
            System.out.println(imagepath);
            
            image = ImageIO.read(new File(imagepath));
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
}
