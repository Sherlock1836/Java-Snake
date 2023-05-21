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
        if(!fruit.isEaten())
            g2D.setColor(Color.RED);
            g2D.fillRect(fruit.getFruit()[0], fruit.getFruit()[1], fruit.getFruitThickness(), fruit.getFruitThickness());
        for(int[] segment : snek.getSnakeBody()) {
            Rectangle rect = new Rectangle(segment[0], segment[1], snek.getSnakeThickness(), snek.getSnakeThickness());
            g2D.setColor(Color.GREEN);
            g2D.fill(rect);
            g2D.setColor(Color.BLACK);
            g2D.draw(rect);
        }
        if(snek.isDed()){
            g2D.setColor(Color.LIGHT_GRAY);
            Rectangle rect = new Rectangle(snek.getSnakeBody().getFirst()[0], snek.getSnakeBody().getFirst()[1], snek.getSnakeThickness(), snek.getSnakeThickness());
            g2D.fill(rect);
            g2D.setColor(Color.BLACK);
            g2D.draw(rect);
        }
    }
    public void updateScreen() {
        repaint();
    }
}
