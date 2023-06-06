import java.util.Random;
import java.util.Arrays;

public class Fruit {
    private Snake snek;
    private int[] fruit = new int[2];
    private int fruitThickness;
    private Random rand = new Random();
    private int xBound;
    private int yBound;
    private boolean isEaten = true;

    public Fruit(Snake snek, int screenWidth, int screenHeight) {
        this.snek = snek;
        fruitThickness = snek.getSnakeThickness();
        xBound = screenWidth;
        yBound = screenHeight;
    }
    
    public void setEaten(boolean isEaten) {
        this.isEaten = isEaten;
    }
    public boolean isEaten() {
        return isEaten;
    }
    public int[] getFruit() {
        return fruit;
    }
    public int getFruitThickness() {
        return fruitThickness;
    }
    
    public void generateFruit() {
        int x = genRandCoord(xBound);
        int y = genRandCoord(yBound);
        while(snek.bodyColision(0, new int[] {x, y})) {
            x = genRandCoord(xBound);
            y = genRandCoord(yBound);
        } 
        fruit[0] = x;
        fruit[1] = y;
        isEaten = false;
    }
    public int genRandCoord(int bound) {
        return fruitThickness * rand.nextInt(0, (bound - fruitThickness) / fruitThickness + 1);
    }
    public void updateFruit() {
        if(isEaten())
            generateFruit();
        if(Arrays.equals(snek.getSnakeBody().getFirst(), fruit)){
            setEaten(true);
            snek.grow(App.getGrowRate());
        }
    }
}
