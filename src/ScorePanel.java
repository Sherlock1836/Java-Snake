import java.awt.Color;
import java.awt.*;

import javax.swing.JComponent;

public class ScorePanel extends JComponent{
    public ScorePanel() {
        setBackground(new Color(12, 21, 105));
    }

    public void paintComponent(Graphics g) {
        Graphics2D g2D = (Graphics2D) g;
        g2D.setColor(getBackground());
        g2D.fillRect(0, 0, getWidth(), getHeight());
        drawScore(g2D);
        drawBestScore(g2D);
    }

    public void drawScore(Graphics2D g2D) {
        String txt = "Score: " + App.getScore() + "  ";
        g2D.setColor(Color.BLACK);
        g2D.setFont(new Font("Arial Black", Font.PLAIN, 24));
        FontMetrics fm = g2D.getFontMetrics();
        int txtWidth = fm.stringWidth(txt);
        int txtHeight = fm.getHeight();
        g2D.drawString(txt, getWidth() - txtWidth, txtHeight - 16);    //text height registers higher than it actually looks hence a buffer here
    }

    public void drawBestScore(Graphics2D g2D) {
        String txt = "Best: " + App.getBestScore();
        g2D.setColor(Color.BLACK);
        g2D.setFont(new Font("Arial Black", Font.PLAIN, 24));
        FontMetrics fm = g2D.getFontMetrics();
        int txtHeight = fm.getHeight();
        g2D.drawString(txt, 20, txtHeight - 16);
    }

    public void updatePanel() {
        repaint();
    }
}
