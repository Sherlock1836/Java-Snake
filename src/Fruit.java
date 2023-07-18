import java.util.Random;
import java.util.Arrays;

public class Fruit {
    private Snake snek;
    private int[] fruit = new int[2];
    private Random rand = new Random();
    private boolean isEaten = true;

    public Fruit(Snake snek) {
        this.snek = snek;
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
    
    public void generateFruit() {
        int x = genRandCoord(App.getXmin(), App.getXmax());
        int y = genRandCoord(App.getYmin(), App.getYmax());
        while(snek.bodyColision(0, new int[] {x, y})) {
            x = genRandCoord(App.getXmin(), App.getXmax());
            y = genRandCoord(App.getYmin(), App.getYmax());
        } 
        fruit[0] = x;
        fruit[1] = y;
        isEaten = false;
    }

    public int genRandCoord(int min, int max) {
        return App.getSnakeThiccness() * rand.nextInt(min / App.getSnakeThiccness(), max / App.getSnakeThiccness() + 1);
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
