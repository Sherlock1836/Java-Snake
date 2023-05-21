import java.util.Random;

public class Fruit {
    private Snake snek;
    private int[] fruit = new int[2];
    private int fruitThickness;
    private Random rand = new Random();
    private int xBound;
    private int yBound;
    private int x;
    private int y;
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
        x = genRandCoord(xBound);
        y = genRandCoord(yBound);
        while(snek.bodyColision(0, new int[] {x, y})) {
            x = genRandCoord(xBound);
            y = genRandCoord(yBound);
        }
        fruit[0] = x;
        fruit[1] = y;
        isEaten = false;
    }
    public int genRandCoord(int bound) {
        return fruitThickness * rand.nextInt(fruitThickness, bound / fruitThickness + 1) - fruitThickness;
    }
}
