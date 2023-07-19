import javax.swing.JComponent;
import java.awt.*;

public class GameScreen extends JComponent{
    private Snake snek;
    private Fruit fruit;

    public GameScreen(Snake snek, Fruit fruit) {
        this.snek = snek;
        this.fruit = fruit;
    }

    public void paintComponent(Graphics g) {
        Graphics2D g2D = (Graphics2D) g;
        drawBackground(g2D);
        drawVoid(g2D);
        drawFruit(g2D);
        drawSnek(g2D);
        if(App.isGameOver()) {
            drawDedSnekBlock(g2D);
            drawCenterText(g2D, "Game Over", Color.BLUE);
        }
        if(App.isGamePaused() && !App.isGameOver())
            drawCenterText(g2D, "Game Paused", Color.GRAY);
    }

    public void drawBackground(Graphics2D g2D) {
        g2D.setColor(new Color(12, 21, 105));
        g2D.fillRect(0, 0, getWidth(), getHeight()); //this rectangle is for the blue padding
    }

    public void drawVoid(Graphics2D g2D) {
        g2D.setColor(Color.BLACK);                   
        g2D.fillRect(App.getSnakeThiccness() * App.getPadding(), App.getSnakeThiccness() * App.getPadding(), 
                    getWidth() - App.getSnakeThiccness() * App.getPadding() * 2, 
                    getHeight() - App.getSnakeThiccness() * App.getPadding() * 2); //this rectangle is for the void (playable area)
    }

    public void drawFruit(Graphics2D g2D) {
        if(!fruit.isEaten()) {
            Rectangle rect = new Rectangle(fruit.getFruit()[0], fruit.getFruit()[1], App.getSnakeThiccness(), App.getSnakeThiccness());
            g2D.setColor(Color.RED);
            g2D.fill(rect); 
            g2D.setColor(Color.black);
            g2D.draw(rect);
        }
    }

    public void drawSnek(Graphics2D g2D) {
        for(int[] segment : snek.getSnakeBody()) {
            Rectangle rect = new Rectangle(segment[0], segment[1], App.getSnakeThiccness(), App.getSnakeThiccness());
            g2D.setColor(Color.GREEN);
            g2D.fill(rect);
            g2D.setColor(Color.DARK_GRAY);
            g2D.draw(rect);
        }
    }

    public void drawDedSnekBlock(Graphics2D g2D) {
        g2D.setColor(Color.LIGHT_GRAY);
        Rectangle rect = new Rectangle(snek.getSnakeBody().getFirst()[0], snek.getSnakeBody().getFirst()[1], App.getSnakeThiccness(), App.getSnakeThiccness());
        g2D.fill(rect);
        g2D.setColor(Color.DARK_GRAY);
        g2D.draw(rect);
    }
    
    public void drawCenterText(Graphics2D g2D, String txt, Color color) {
        g2D.setFont(new Font("Arial Black", Font.PLAIN, 32));
        FontMetrics fm = g2D.getFontMetrics();
        int txtWidth = fm.stringWidth(txt);
        int txtHeight = fm.getHeight();
        int centerX = (getWidth() - txtWidth) / 2;
        int centerY = (getHeight() - txtHeight) / 2;

        g2D.setColor(Color.BLACK);
        int padding = 10; 
        int backgroundX = centerX - padding;
        int backgroundY = centerY - txtHeight;
        int backgroundWidth = txtWidth + 2 * padding;
        int backgroundHeight = txtHeight + 2 * padding;
        g2D.fillRect(backgroundX, backgroundY, backgroundWidth, backgroundHeight);

        g2D.setColor(color);
        g2D.drawString(txt, centerX, centerY);
    }

    public void updateScreen() {
        repaint();
    }
}
