import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
public class Agent {
    private Robot bot;
    private Snake snek;
    private Fruit fruit;
    private String newDirection = null;  //this is the action with action space being left, right, up, down
    //environment variables (yes some already exist elsewhere but for this class it's easier to think ab if they all grouped together and have short name)
    private final int X_MAX = App.getXmax();
    private final int X_MIN = App.getXmin();
    private final int Y_MIN = App.getYmin();
    private final int Y_MAX = App.getYmax();
    private int fruitX;
    private int fruitY;
    private int headX;
    private int headY;
    private String curDirection;

    public Agent(Snake snek, Fruit fruit) {
        this.snek = snek;
        this.fruit = fruit;
        try{
            bot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }
    public void updateState() {
        headX = snek.getSnekHead()[0];
        headY = snek.getSnekHead()[1];
        fruitX = fruit.getFruit()[0];
        fruitY = fruit.getFruit()[1];
        curDirection = snek.getDirection();
    }
    public void play() {
        updateState();

        if(curDirection == null)
            newDirection = "right";
        clickKey();
        newDirection = null;
    }
    public void ninetyTurn() {

    }
    public void eightyTurn() {

    }
    public void clickKey() {
        if(newDirection == "up"){
            bot.keyPress(KeyEvent.VK_UP);
            bot.keyRelease(KeyEvent.VK_UP);
        }
        if(newDirection == "down"){
            bot.keyPress(KeyEvent.VK_DOWN);
            bot.keyRelease(KeyEvent.VK_DOWN);
        }
        if(newDirection == "left"){
            bot.keyPress(KeyEvent.VK_LEFT);
            bot.keyRelease(KeyEvent.VK_LEFT);
        }
        if(newDirection == "right"){
            bot.keyPress(KeyEvent.VK_RIGHT);
            bot.keyRelease(KeyEvent.VK_RIGHT);
        }
    }
}
