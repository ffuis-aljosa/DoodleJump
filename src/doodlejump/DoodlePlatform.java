package doodlejump;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class DoodlePlatform extends DoodleObject {
    protected static BufferedImage image;
    
    public DoodlePlatform(int x, int y) {
        super(x, y, 58, 15);
    }
    
    @Override
    public void draw(Graphics2D g2d)
    {
        g2d.drawImage(image, x, y, WIDTH, HEIGHT, null);
    }

    @Override
    public void move() {
        super.move();
    }
    
    public void startMoving() {
        velocityY = 30;
    }
    
    public static void loadImage() {
        try {
            String imagepath = "src/images/platformGreen.png";
            
            System.out.println(imagepath);
            
            image = ImageIO.read(new File(imagepath));
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
}
