import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
public class Agent {
    private Robot bot;
    private Snake snek;
    private Fruit fruit;
    private String newDirection;
    public Agent(Snake snek, Fruit fruit) {
        this.snek = snek;
        this.fruit = fruit;
        try{
            bot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    public void play() {
        if(snek.getDirection() == "right"){
            if(snek.getSnekHead()[0] == App.getXmax() - App.getSnakeThiccness())
                newDirection = "down";
            if(fruit.getFruit()[0] <= snek.getSnekHead()[0])
                newDirection = "down";
        }
        if(snek.getDirection() == "down"){
            if(snek.getSnekHead()[1] == App.getYmax() - App.getSnakeThiccness())
                newDirection = "left";
            if(fruit.getFruit()[1] <= snek.getSnekHead()[1])
                newDirection = "left";
        }
        if(snek.getDirection() == "left"){
            if(snek.getSnekHead()[0] == App.getXmin() + App.getSnakeThiccness())
                newDirection = "up";
            if(fruit.getFruit()[0] >= snek.getSnekHead()[0])
                newDirection = "up";
        }
        if(snek.getDirection() == "up"){
            if(snek.getSnekHead()[1] == App.getYmin() + App.getSnakeThiccness())
                newDirection = "right";
            if(fruit.getFruit()[1] >= snek.getSnekHead()[1])
                newDirection = "right";
        }
        
        if(snek.getDirection() == null)
            newDirection = "right";
        clickKey();
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
