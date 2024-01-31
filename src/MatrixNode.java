public class MatrixNode {
    public int x;
    public int y;
    public boolean isWall;
    public int dir;

    public MatrixNode(int x, int y, boolean isWall){
        this.x = x;
        this.y = y;
        this.isWall = isWall;
        dir = 0;
        if(isWall)
            dir = 7;
    }
}
