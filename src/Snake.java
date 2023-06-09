import java.util.Arrays;
import java.util.LinkedList;
import java.util.ListIterator;

public class Snake {
    private LinkedList<int []> snakeBody = new LinkedList<int[]>();
    private int snakeThickness = 20;
    private String direction;
    private boolean moved;     //This is used to fix a bug in the input handler that allowed direction to change multiple times before snake had moved which could lead to wierd deaths
    private int xBound;
    private int yBound;


    public Snake(int screenWidth, int screenHeight) {
        xBound = screenWidth;
        yBound = screenHeight;
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
    private int numPartsToGrow = 0;

    public void setDirection(String direction) {
        this.direction = direction;
    }
    public int getSnakeThickness() {
        return snakeThickness;
    }
    public LinkedList<int[]> getSnakeBody() {
        return snakeBody;
    }

    public void move() {
        if(direction == "up")
            snakeBody.addFirst(new int [] {snakeBody.getFirst()[0], snakeBody.getFirst()[1] - snakeThickness});
        if(direction == "down")
            snakeBody.addFirst(new int [] {snakeBody.getFirst()[0], snakeBody.getFirst()[1] + snakeThickness});
        if(direction == "left")
            snakeBody.addFirst(new int [] {snakeBody.getFirst()[0] - snakeThickness, snakeBody.getFirst()[1]});
        if(direction == "right")
            snakeBody.addFirst(new int [] {snakeBody.getFirst()[0] + snakeThickness, snakeBody.getFirst()[1]});
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
        if(snakeBody.getFirst()[0] >= xBound || snakeBody.getFirst()[1] >= yBound)
            return true;
        if(snakeBody.getFirst()[0] < 0 || snakeBody.getFirst()[1] < 0)    //doesn't use "or equal to" because the snake is still within bounds at (0, 0)
            return true;
        if(bodyColision(1, snakeBody.getFirst()))
            return true;
        return false;
    }
    public void resetSnek() {
        direction = null;
        moved = false;
        snakeBody.clear();
        snakeBody.addFirst(new int [] {xBound / 2, yBound / 2});
    }
}
