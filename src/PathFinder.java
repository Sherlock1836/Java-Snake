import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

//path finder uses a breadth first search because I'm too stupid to implement A* at the moment.
public class PathFinder {
    private static MatrixNode start;
    private static MatrixNode goal;
    private static MatrixNode[][] gameMatrix = new MatrixNode[App.getBlocksAcross()][App.getBlocksDown()];
    private Queue<MatrixNode> frontier;
    private HashMap<MatrixNode, MatrixNode> cameFrom;
    
    public static void setStart(int x, int y) {
        start = new MatrixNode(x, y, false);
    }

    public static void setGoal(int x, int y) {
        goal = new MatrixNode(x, y, false);
    }

    public static void setGameMatrix(LinkedList<int[]> snekBody) {
        for(int x = App.getXmin(); x <= App.getXmax(); x += App.getSnakeThiccness()) {
            for(int y = App.getYmin(); x <= App.getYmax(); x += App.getSnakeThiccness()) {
                gameMatrix[x][y] = new MatrixNode(x, y, false);
            }
        }
        for(int[] segment : snekBody) {
            gameMatrix[segment[0]][segment[1]] = new MatrixNode(segment[0], segment[1], true);
        }
    }

    public void runPathFinder(){
        frontier = new LinkedList<MatrixNode>();
        cameFrom = new HashMap<MatrixNode, MatrixNode>();
        MatrixNode current;

        frontier.add(start);
        cameFrom.put(start, null);
        while(!frontier.isEmpty()){
            current = frontier.poll();
            for(MatrixNode next : getNeighbors(current)){
                if(!cameFrom.containsKey(next)){
                    frontier.add(next);
                    cameFrom.put(next, current);
                }
            }
        }
    }

    private ArrayList<MatrixNode> getNeighbors(MatrixNode node) {
        ArrayList<MatrixNode> neighbors = new ArrayList<MatrixNode>();
        if(node.y - 1 >= App.getYmin() && !gameMatrix[node.x][node.y - 1].isWall)
            neighbors.add(gameMatrix[node.x][node.y - 1]);
        if(node.x - 1 >= App.getXmin() && !gameMatrix[node.x - 1][node.y].isWall)
            neighbors.add(gameMatrix[node.x - 1][node.y]);
        if(node.y + 1 <= App.getYmax() && !gameMatrix[node.x][node.y + 1].isWall)
            neighbors.add(gameMatrix[node.x][node.y + 1]);
        if(node.x + 1 >= App.getXmax() && !gameMatrix[node.x + 1][node.y].isWall)
            neighbors.add(gameMatrix[node.x + 1][node.y]);
        return neighbors;
    }
}