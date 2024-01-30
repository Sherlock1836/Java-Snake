import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.LinkedList;
public class Agent {
    private Robot bot;
    private Snake snek;
    private Fruit fruit;
    
    private String curDirection;
    private LinkedList<int[]> snekBody;
    private int headX;
    private int headY;
    private int fruitX;
    private int fruitY;

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
        snekBody = snek.getSnakeBody();
    }
    public void play() {
        updateState();
        PathFinder.setGameMatrix(snekBody);
        PathFinder.setStart(headX, headY);
        PathFinder.setGoal(fruitX, fruitY);
        //main bot logic here
        
        clickKey();
    }

    public void clickKey() {
        String newDirection = null; //add way to get new direction
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
