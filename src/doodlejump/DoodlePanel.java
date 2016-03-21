package doodlejump;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;
import javax.swing.Timer;

public class DoodlePanel extends JPanel implements ActionListener, KeyListener {

    private final Timer timer;
    private final int FRAMES_PER_SECOND = 30;

    private DoodleGuy dGuy;

    public DoodlePanel() {
        setBackground(Color.WHITE);

        timer = new Timer(1000 / FRAMES_PER_SECOND, this);
        timer.start();
        
        setFocusable(true);
        addKeyListener(this);
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

        if (dGuy.isAtBottom(size.height)) {
            dGuy.bounce();
        }
        
        if (dGuy.isMaxLeft())
            dGuy.teleportRight(size.width);
        else if (dGuy.isMaxRight(size.width))
            dGuy.teleportLeft();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        moveObjects();
        handleCollisions();
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
    public void keyTyped(KeyEvent e) {}

}
