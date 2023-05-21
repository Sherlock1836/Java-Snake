import java.util.Arrays;
import java.util.LinkedList;
import java.util.ListIterator;

public class Snake {
    private LinkedList<int []> snakeBody = new LinkedList<int[]>();
    private int snakeThickness = 20;
    private String direction = null;
    private boolean moved = false;     //This is used to fix a bug in the input handler that allowed direction to change multiple times before snake had moved which could lead to wierd deaths
    private boolean snekDed = false;
    private int xBound;
    private int yBound;


    public Snake(int screenWidth, int screenHeight) {
        xBound = screenWidth;
        yBound = screenHeight;
        snakeBody.addFirst(new int [] {xBound / 2, yBound / 2});
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
    public boolean isDed() {
        return snekDed;
    }
    public void setSnekDed(boolean snekDed) {
        this.snekDed = snekDed;
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
    public void didHeDie() {
        if(snakeBody.getFirst()[0] >= xBound || snakeBody.getFirst()[1] >= yBound)
            snekDed = true;
        if(snakeBody.getFirst()[0] <= 0 || snakeBody.getFirst()[1] <= 0)
            snekDed = true;
        if(bodyColision(1, snakeBody.getFirst()))
            snekDed = true;
    }
}
