import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class App {
    private static final int DELAY = 70;            //for timer (in ms, original about 70, lower it for a faster game), this controls speed of game
    private static final int GROW_RATE = 4;         //variable for how many blocks snake grows by when fruit is eaten (4 originally)
    private static boolean gamePaused = false;
    private static boolean gameOver = false;
    private static int score;                       //score is just the length of the snek
    private static final int SNAKE_THICCNESS = 20;  //This is the length of the side of the snake square in pixles (originally 20, untested with different values)...snake_thiccness constitutes the length and width of a snake block
    private static final int BLOCKS_ACROSS = 37;    //Blocks across and down refers to size of the void (area where snake can move) in snake blocks (37, 27 originally )
    private static final int BLOCKS_DOWN = 27;      //These values should be changeable to allow for different game sizes
    private static final int PADDING = 1;           //This is the size of the padding (blue area around the void) in snake blocks
    private static final int SCREEN_WIDTH = SNAKE_THICCNESS * (BLOCKS_ACROSS + 2 * PADDING);    //the screen refers to the JComponent that the gameScreen is drawn on
    private static final int SCREEN_HEIGHT = SNAKE_THICCNESS * (BLOCKS_DOWN + 2 * PADDING);     //the plus 2 allows for the padding around the void
    private static final int xMin = SNAKE_THICCNESS * PADDING;                                        //These mins and maxs are used for the boundarys of fruit and snake
    private static final int xMax = SCREEN_WIDTH - (SNAKE_THICCNESS * PADDING) - SNAKE_THICCNESS;
    private static final int yMin = SNAKE_THICCNESS * PADDING;
    private static final int yMax = SCREEN_HEIGHT - (SNAKE_THICCNESS * PADDING) - SNAKE_THICCNESS;
    public static int getPadding() {
        return PADDING;
    }
    public static int getXmin() {
        return xMin;
    }
    public static int getXmax() {
        return xMax;
    }
    public static int getYmin() {
        return yMin;
    }
    public static int getYmax() {
        return yMax;
    }
    public static int getSnakeThiccness() {
        return SNAKE_THICCNESS;
    }   
    public static int getScore() {
        return score;
    }
    public static boolean isGameOver() {
        return gameOver;
    }
    public static boolean isGamePaused() {
        return gamePaused;
    }
    public static int getGrowRate() {
        return GROW_RATE;
    }
    public static void main(String[] args) {
        JFrame frame = new JFrame("Snake by Sherl0ck._.");
        //game objects
        Snake snek = new Snake();
        Fruit fruit = new Fruit(snek);
        //Set up JComponent to draw on
        GameScreen gameScreen = new GameScreen(snek, fruit);
        gameScreen.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        ScorePanel scorePanel = new ScorePanel();
        scorePanel.setPreferredSize(new Dimension(SCREEN_WIDTH, PADDING * 2 * SNAKE_THICCNESS));
        
        class TimerListener implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                if(!gameOver && !gamePaused) {
                    fruit.updateFruit();
                    score = snek.getSnakeBody().size();
                    snek.move();
                    gameOver = snek.didHeDie();
                }
                gameScreen.updateScreen();
                scorePanel.updatePanel();
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
                    if(!gameOver){
                        if(gamePaused == true)                //pause game if space pressed while active
                            gamePaused = false;
                        else
                            gamePaused = true;
                    } else {
                        gameOver = false;                     //Restart the game when space pressed upon death
                        snek.resetSnek();
                        fruit.generateFruit();
                    }

                }
                if(e.getKeyCode() == KeyEvent.VK_G)                    //FOR TESTING REMOVE LATERR!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                    fruit.generateFruit();
            }
            @Override
            public void keyReleased(KeyEvent e) {
                // TODO Auto-generated method stub
            }
        }

        frame.add(gameScreen, BorderLayout.CENTER);
        frame.add(scorePanel, BorderLayout.SOUTH);
        frame.addKeyListener(new KeyListenerBoi());
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        ticker.start();
    }
}
