import javax.swing.JComponent;
import java.awt.*;

public class Screen extends JComponent{
    private Graphics2D g2D;
    private Snake snek;
    private Fruit fruit;

    public Screen(Snake snek, Fruit fruit) {
        this.snek = snek;
        this.fruit = fruit;
        setOpaque(true);
        setBackground(Color.BLACK);
    }

    public void paintComponent(Graphics g) {
        g2D = (Graphics2D) g;
        g2D.setColor(getBackground());                    //this line and the next line are what I used to get the background to work properly
        g2D.fillRect(0, 0, getWidth(), getHeight());  //it basically fills the Screen component with black every time it's repainted (i couldn't get set background to work right)
        drawFruit(g2D);
        drawSnek(g2D);
        if(App.isGameOver()) {
            drawDedSnekBlock(g2D);
            drawCenterText(g2D, "Game Over " + App.getScore(), Color.BLUE);
        }
        if(App.isGamePaused() && !App.isGameOver())
            drawCenterText(g2D, "Game Paused", Color.GRAY);
    }

    public void drawFruit(Graphics2D g2D) {
        if(!fruit.isEaten()) {
            g2D.setColor(Color.RED);
            g2D.fillRect(fruit.getFruit()[0], fruit.getFruit()[1], fruit.getFruitThickness(), fruit.getFruitThickness());
        }
    }
    public void drawSnek(Graphics2D g2D) {
        for(int[] segment : snek.getSnakeBody()) {
            Rectangle rect = new Rectangle(segment[0], segment[1], snek.getSnakeThickness(), snek.getSnakeThickness());
            g2D.setColor(Color.GREEN);
            g2D.fill(rect);
            g2D.setColor(Color.BLACK);
            g2D.draw(rect);
        }
    }
    public void drawDedSnekBlock(Graphics2D g2D) {
        g2D.setColor(Color.LIGHT_GRAY);
        Rectangle rect = new Rectangle(snek.getSnakeBody().getFirst()[0], snek.getSnakeBody().getFirst()[1], snek.getSnakeThickness(), snek.getSnakeThickness());
        g2D.fill(rect);
        g2D.setColor(Color.BLACK);
        g2D.draw(rect);
    }
    public void drawCenterText(Graphics2D g2D, String txt, Color color) {
        g2D.setFont(new Font("Arial", Font.PLAIN, 32));
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
