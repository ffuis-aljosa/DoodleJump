package doodlejump;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Action;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class DoodleFrame extends JFrame {
    private final int FRAME_WIDTH = 400;
    private final int FRAME_HEIGHT = 600;
    
    private DoodlePanel dPanel;
    
    public DoodleFrame() {
        setTitle("Doodle Jump");
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        
        dPanel = new DoodlePanel();
        add(dPanel);
        
        DoodleGuy.loadImage();
        DoodlePlatform.loadImage();
        
        setJMenuBar(initMenu());
        
        setVisible(true);
        
    }
    
    private JMenuBar initMenu() {
        JMenuBar menuBar = new JMenuBar();
        
        JMenu gameMenu = new JMenu("Game");
        
        JMenuItem startGameItem = new JMenuItem("Start game");
        startGameItem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                dPanel.startGame();
            }
            
        });
        
        gameMenu.add(startGameItem);
        
        menuBar.add(gameMenu);
        
        return menuBar;
    }
}
