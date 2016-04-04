package doodlejump;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JPanel;
import javax.swing.Timer;

public class DoodlePanel extends JPanel implements ActionListener, KeyListener {

    private boolean gameStarted = false;

    private final Timer timer;
    private final int FRAMES_PER_SECOND = 30;

    private DoodleGuy dGuy;
    private ArrayList<DoodlePlatform> dPlatforms = new ArrayList<>();
    
    private final int MAX_JUMP_HEIGHT = 200;
    
    private final Random random;
    
    private final int FRAMES_BETWEEN_PLATFORMS = 5;
    private int framesSinceLastPlatform = 0;

    public DoodlePanel() {
        setBackground(Color.WHITE);

        timer = new Timer(1000 / FRAMES_PER_SECOND, this);
        timer.start();

        setFocusable(true);
        addKeyListener(this);
        
        random = new Random();
    }

    public void startGame() {
        Dimension size = this.getSize();

        dGuy = new DoodleGuy(size.width, size.height);
        dPlatforms.add(new DoodlePlatform(0, 40));
        dPlatforms.add(new DoodlePlatform(30, 100));
        dPlatforms.add(new DoodlePlatform(70, 200));

        gameStarted = true;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        if (gameStarted) {
            Graphics2D g2D = (Graphics2D) g;
            
            g2D.drawLine(0, MAX_JUMP_HEIGHT, 400, MAX_JUMP_HEIGHT);

            dGuy.draw(g2D);

            for (DoodlePlatform dPlatform : dPlatforms) {
                dPlatform.draw(g2D);
            }
        }
    }

    private void moveObjects() {
        if (dGuy.y <= MAX_JUMP_HEIGHT) {
            
            if (framesSinceLastPlatform >= FRAMES_BETWEEN_PLATFORMS)
                generatePlatform();
            else
                framesSinceLastPlatform++;
            
            dGuy.velocityY = 0;
            
            for (DoodlePlatform dPlatform : dPlatforms) {
                if (dPlatform.velocityY == 0)
                    dPlatform.startMoving();
                
                dPlatform.move();
            }
        }
        
        dGuy.move();
    }

    private void handleCollisions() {
        Dimension size = this.getSize();

        if (dGuy.isAtBottom(size.height)) {
            dGuy.bounce();
        }

        if (dGuy.isMaxLeft()) {
            dGuy.teleportRight(size.width);
        } else if (dGuy.isMaxRight(size.width)) {
            dGuy.teleportLeft();
        }

        if (dGuy.isGoingDown()) {
            Rectangle2D.Double dGuyRectangle = dGuy.getRectangle();

            for (DoodlePlatform dPlatform : dPlatforms) {
                if (dPlatform.getRectangle().intersects(dGuyRectangle) && dGuy.isIntersectingFromAbove(dPlatform)) {
                    dGuy.bounce();
                }
            }
        }
    }
    
    private void cleanUp() {
        Dimension size = this.getSize();
        int platformNumber = dPlatforms.size();
        
        for (int i = platformNumber - 1; i >= 0; i--) {
            DoodlePlatform dPlatform = dPlatforms.get(i);
            
            if (dPlatform.x > size.height)
                dPlatforms.remove(i);
        }
    }
    
    private void generatePlatform() {
        Dimension size = this.getSize();
        int newX = random.nextInt(size.width);
        
        DoodlePlatform newPlatform = new DoodlePlatform(newX, 0);
        
        dPlatforms.add(newPlatform);
        
        framesSinceLastPlatform = 0;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        moveObjects();
        handleCollisions();
        cleanUp();
        repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();

        if (keyCode == KeyEvent.VK_LEFT) {
            dGuy.moveLeft();
        } else if (keyCode == KeyEvent.VK_RIGHT) {
            dGuy.moveRight();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();

        if (keyCode == KeyEvent.VK_LEFT || keyCode == KeyEvent.VK_RIGHT) {
            dGuy.stopHorizontal();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

}
