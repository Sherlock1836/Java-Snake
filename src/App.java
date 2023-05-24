import javax.swing.*;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Arrays;

public class App {
    private static final int SCREEN_WIDTH = 800;    //the screen refers to the JComponent that the game is drawn on
    private static final int SCREEN_HEIGHT = 600;
    private static final int DELAY = 75;            //for timer (in ms)
    private static final int GROW_RATE = 4;         //variable for how many blocks snake grows by when fruit is eaten
    private static boolean gamePaused = false;      
    public static void main(String[] args) {
        JFrame frame = new JFrame("Snake by Sherlock._.");
        //game objects
        Snake snek = new Snake(SCREEN_WIDTH, SCREEN_HEIGHT);
        Fruit fruit = new Fruit(snek, SCREEN_WIDTH, SCREEN_HEIGHT);
        //Set up JComponent to draw on
        Screen gameScreen = new Screen(snek, fruit);
        gameScreen.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        
        class TimerListener implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                if(!snek.isDed()) {
                    if(fruit.isEaten())
                        fruit.generateFruit();
                    if(Arrays.equals(snek.getSnakeBody().getFirst(), fruit.getFruit())){
                        fruit.setEaten(true);
                        snek.grow(GROW_RATE);
                    }
                    snek.move();
                    snek.didHeDie();
                    gameScreen.updateScreen();
                }
            }
        }
        Timer ticker = new Timer(DELAY, new TimerListener());

        class KeyListenerBoi implements KeyListener{
            @Override
            public void keyTyped(KeyEvent e) {
                // TODO Auto-generated method stub
            }
            @Override
            public void keyPressed(KeyEvent e) {
                // TODO Auto-generated method stub
                if(!gamePaused && snek.hasMoved()) {
                    if(e.getKeyCode() == KeyEvent.VK_UP && snek.getDirection() != "down")
                        snek.setDirection("up");
                    if(e.getKeyCode() == KeyEvent.VK_DOWN && snek.getDirection() != "up")
                        snek.setDirection("down");
                    if(e.getKeyCode() == KeyEvent.VK_LEFT && snek.getDirection() != "right")
                        snek.setDirection("left");
                    if(e.getKeyCode() == KeyEvent.VK_RIGHT && snek.getDirection() != "left")
                       snek.setDirection("right");
                    snek.setMoved(false);
                }
                if(e.getKeyCode() == KeyEvent.VK_SPACE) {
                    if(gamePaused == true){
                        ticker.start();
                        gamePaused = false;
                    }
                    else{
                        ticker.stop();
                        gamePaused = true;
                    }
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {
                // TODO Auto-generated method stub
            }
        }

        frame.add(gameScreen);
        frame.addKeyListener(new KeyListenerBoi());
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        ticker.start();
    }
}
