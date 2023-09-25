import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.LinkedList;
import java.util.Queue;
public class Agent {
    private Robot bot;
    private Snake snek;
    private Fruit fruit;
    private Queue<String> newDirections = new LinkedList<String>();  //I'm going to try to use a queue to store directions for mechs
    private String newDirection = null;  //this is the action with action space being left, right, up, down
    //environment variables (yes some already exist elsewhere but for this class it's easier to think ab for me if they all grouped together and have short name =))
    private final int X_MAX = App.getXmax();
    private final int X_MIN = App.getXmin();
    private final int Y_MIN = App.getYmin();
    private final int Y_MAX = App.getYmax();
    private int fruitX;
    private int fruitY;
    private int headX;
    private int headY;
    private String curDirection;
    private LinkedList<int[]> snekBody;

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
        //main bot logic here
        if(curDirection == null)
            curDirection = "right";
        int[] nextCoords = lookAhead(curDirection, headX, headY);
        if(checkImminentCollision(nextCoords[0], nextCoords[1])) {
            switch(curDirection) {
                case "right": newDirection = "up";
                case "left": newDirection = "down";
                case "up": newDirection = "left";
                case "down": newDirection = "right";
            }
            int [] nexterCoords = lookAhead(newDirection, nextCoords[0], nextCoords[1]);
            if(checkImminentCollision(nexterCoords[0], nexterCoords[1])){
                String newerDirection = null; 
                switch(newDirection) {
                    case "right": newerDirection = "left";
                    case "left": newerDirection = "right";
                    case "up": newerDirection = "down";
                    case "down": newerDirection = "up";
                }
                newDirections.add(newerDirection);
            } else {
                newDirections.add(newDirection);
            }
            
        } else if(Math.abs(fruitX - headX) > Math.abs(fruitY - headY) && newDirections.isEmpty()){
            if (fruitX > headX){
                if(curDirection != "left") {
                    ninetyTurn("right"); //r
                } else {
                    eightyTurn();  //l to r
                }
            } else {
                if(curDirection != "right") {
                    ninetyTurn("left"); //l
                } else {
                    eightyTurn(); //r to l
                }
            } 
        } else {
            if (fruitY > headY){
                if(curDirection != "up") {
                    ninetyTurn("down"); //r
                } else {
                    eightyTurn();  //l to r
                }
            } else {
                if(curDirection != "down") {
                    ninetyTurn("up"); //r
                } else {
                    eightyTurn();  //l to r
                }
            }
        }
        clickKey();
    }
    public void ninetyTurn(String dir) {
        //90 deg turn   **** NEEDS WORKK
        newDirections.add(dir);
    }
    public void eightyTurn() {
        //180 deg turn

    }
    public void coilSnek() {
        //need to figure out how to code this mech
        //this references the way you keep the snake bunched up to keep from having the body all over the place where its easy to hit
    }
    public int[] lookAhead(String direction, int x, int y) {
        int nextX = x; //filled to get rid of squiggle this initialization does nothing
        int nextY = y;
        switch(direction){
            case "right": nextX = x + App.getSnakeThiccness();
            case "left": nextX = y - App.getSnakeThiccness();
            case "up": nextY = x - App.getSnakeThiccness();
            case "down": nextY = y + App.getSnakeThiccness();
        }
        return new int[] {nextX, nextY};
    }
    public boolean checkImminentCollision(int x, int y) {
        //This is basically Snake class's did he die method 
        if(x > X_MAX || y > Y_MAX)
            return true;
        if(x < X_MIN || y < Y_MIN)    
            return true;
        if(snek.bodyColision(1, new int[] {x, y}))
            return true;
        return false;
    }

    public void clickKey() {
        newDirection = newDirections.poll();
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
