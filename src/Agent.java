import java.awt.AWTException;
import java.awt.Robot;
import java.awt.RenderingHints.Key;
import java.awt.event.KeyEvent;
public class Agent  implements Runnable{
    private Robot bot;
    private Snake snek;
    private Fruit fruit;
    private boolean running = true;
    public Agent(Snake snek, Fruit fruit) {
        this.snek = snek;
        this.fruit = fruit;
        try{
            bot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }
    
    
    public void run() {
        while(running && !App.isGameOver() && !App.isGamePaused()){
            play();
        }
        try{
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void play() {
        if(snek.getDirection() == "right"){
            bot.keyPress(KeyEvent.VK_DOWN);
            bot.keyRelease(KeyEvent.VK_DOWN);
        }
        if(snek.getDirection() == "left"){

        }
        if(snek.getDirection() == "up"){

        }
        if(snek.getDirection() == "down"){

        }
    }
}
