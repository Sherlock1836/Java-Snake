import java.util.Arrays;
import java.util.LinkedList;
import java.util.ListIterator;

public class Snake {
    private LinkedList<int []> snakeBody = new LinkedList<int[]>();
    private String direction;
    private boolean moved;     //This is used to fix a bug in the input handler that allowed direction to change multiple times before snake had moved which could lead to weird deaths
    private int numPartsToGrow = 0;

    public Snake() {
        resetSnek();
    }
    public boolean hasMoved() {
        return moved;
    }
    public void setMoved(boolean moved) {
        this.moved = moved;
    }
    public String getDirection() {
        return direction;
    }
    public void setDirection(String direction) {
        this.direction = direction;
    }
    public LinkedList<int[]> getSnakeBody() {
        return snakeBody;
    }

    public void move() {
        if(direction == "up")
            snakeBody.addFirst(new int [] {snakeBody.getFirst()[0], snakeBody.getFirst()[1] - App.getSnakeThiccness()});
        if(direction == "down")
            snakeBody.addFirst(new int [] {snakeBody.getFirst()[0], snakeBody.getFirst()[1] + App.getSnakeThiccness()});
        if(direction == "left")
            snakeBody.addFirst(new int [] {snakeBody.getFirst()[0] - App.getSnakeThiccness(), snakeBody.getFirst()[1]});
        if(direction == "right")
            snakeBody.addFirst(new int [] {snakeBody.getFirst()[0] + App.getSnakeThiccness(), snakeBody.getFirst()[1]});
        if(direction != null && numPartsToGrow == 0)
            snakeBody.removeLast();
        if(numPartsToGrow != 0)
           --numPartsToGrow;
        moved = true;
    }
    
    public void grow(int growFactor) {
        numPartsToGrow += growFactor;
    }

    public boolean bodyColision(int startElement, int[] searchCoords) {
        ListIterator<int[]> litIt = snakeBody.listIterator(startElement);
        while(litIt.hasNext()) {
            if(Arrays.equals(searchCoords, litIt.next()))
                return true;
        }
        return false;
    }

    public boolean didHeDie() {
        if(snakeBody.getFirst()[0] > App.getXmax() || snakeBody.getFirst()[1] > App.getYmax())
            return true;
        if(snakeBody.getFirst()[0] < App.getXmin() || snakeBody.getFirst()[1] < App.getYmin())    //doesn't use "or equal to" because the snake is still within bounds at (0, 0)
            return true;
        if(bodyColision(1, snakeBody.getFirst()))
            return true;
        return false;
    }

    public void resetSnek() {
        direction = null;
        moved = false;
        snakeBody.clear();
        snakeBody.addFirst(new int [] {App.getXmax() / App.getSnakeThiccness() / 2 * App.getSnakeThiccness(), App.getYmax() / App.getSnakeThiccness() / 2 * App.getSnakeThiccness()});  
    }
}
