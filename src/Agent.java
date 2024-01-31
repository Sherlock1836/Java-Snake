import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.LinkedList;
public class Agent {
    private Robot bot;
    private Snake snek;
    private Fruit fruit;
    
    private String newDirection;
    private LinkedList<int[]> snekBody;
    private int headX;
    private int headY;
    private int fruitX;
    private int fruitY;

    private LinkedList<MatrixNode> path;

    public Agent(Snake snek, Fruit fruit) {
        this.snek = snek;
        this.fruit = fruit;
        try{
            bot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    public void resetBot() {
        path.clear();
        PathFinder.reset();
    }

    public void updateState() {
        headX = snek.getSnekHead()[0];
        headY = snek.getSnekHead()[1];
        fruitX = fruit.getFruit()[0];
        fruitY = fruit.getFruit()[1];
        snekBody = snek.getSnakeBody();
    }

    public void play() {
        updateState();
        if(path == null || path.isEmpty()){
            getDirections();
        }
        MatrixNode nextNode = path.pollLast();
        if(!(nextNode == null)){
            if(nextNode.x > headX)
                newDirection = "right";
            if(nextNode.x < headX)
                newDirection = "left";
            if(nextNode.y > headY)
                newDirection = "down";
            if(nextNode.y < headY)
                newDirection = "up";
        }
        clickKey();
    }

    public void getDirections() {
        PathFinder.setGameMatrix(snekBody);
        PathFinder.setGoal(fruitX, fruitY);
        PathFinder.setStart(headX, headY);
        //main bot logic here
        path = PathFinder.runPathFinder();
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
