import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JComponent;

public class ScorePanel extends JComponent{
    public ScorePanel() {
        setBackground(Color.DARK_GRAY);
    }

    public void paintComponent(Graphics g) {
        Graphics2D g2D = (Graphics2D) g;
        g2D.setColor(getBackground());
        g2D.fillRect(0, 0, getWidth(), getHeight());

    }
    public void drawScore(Graphics2D g2D) {
        
    }
    public void updatePanel() {
        repaint();
    }
}
