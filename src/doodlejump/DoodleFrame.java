package doodlejump;

import javax.swing.JFrame;

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
        
        setVisible(true);
        
        dPanel.startGame();
    }
}
