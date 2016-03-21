package doodlejump;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.Timer;

public class DoodlePanel extends JPanel implements ActionListener {

    private final Timer timer;
    private final int FRAMES_PER_SECOND = 30;
    
    private DoodleGuy dGuy;
    
    public DoodlePanel() {
        setBackground(Color.WHITE);
        
        timer = new Timer(1000 / FRAMES_PER_SECOND, this);
        timer.start();
    }
    
    public void startGame() {
        Dimension size = this.getSize();
        
        dGuy = new DoodleGuy(size.width, size.height);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        
        Graphics2D g2D = (Graphics2D) g;
        
        dGuy.draw(g2D);
    }

    private void moveObjects() {
        dGuy.move();
    }
    
    private void handleCollisions() {
        Dimension size = this.getSize();
        
        if (dGuy.isAtBottom(size.height))
            dGuy.bounce();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        moveObjects();
        handleCollisions();
        repaint();
    }
    
}
