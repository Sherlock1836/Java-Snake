import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

//path finder uses a breadth first search because I'm too stupid to implement A* at the moment.
public class PathFinder {
    private static MatrixNode start;
    private static MatrixNode goal;
    private static MatrixNode[][] gameMatrix = new MatrixNode[App.getBlocksAcross()][App.getBlocksDown()];
    private static Queue<MatrixNode> frontier;
    private static HashMap<MatrixNode, MatrixNode> cameFrom;
    private static LinkedList<MatrixNode> path;
    private static int t = App.getSnakeThiccness();
    private static int p = App.getPadding();
    
    public static void setStart(int x, int y) {
        start = gameMatrix[x / t - p][y / t - p];
    }

    public static void setGoal(int x, int y) {
        goal = gameMatrix[x / t - p][y / t - p];
    }

    public static void reset() {
        start = null;
        goal = null;
        gameMatrix = null;
        frontier.clear();
        cameFrom.clear();
        path.clear();
    }

    public static void setGameMatrix(LinkedList<int[]> snekBody) {
        for(int x = App.getXmin(); x <= App.getXmax(); x += t) {
            for(int y = App.getYmin(); y <= App.getYmax(); y += t) {
                gameMatrix[x / t - p][y / t - p] = new MatrixNode(x, y, false);
            }
        }
        for(int[] segment : snekBody) {
            gameMatrix[segment[0] / t - p][segment[1] / t - p] = new MatrixNode(segment[0], segment[1], true);
        }
    }

    public static LinkedList<MatrixNode> runPathFinder(){
        frontier = new LinkedList<MatrixNode>();
        cameFrom = new HashMap<MatrixNode, MatrixNode>();
        path = new LinkedList<MatrixNode>();
        MatrixNode current = new MatrixNode(-1, -1, false);

        //create paths using breadth first search
        frontier.add(start);
        cameFrom.put(start, null);
        while(!frontier.isEmpty()){
            current = frontier.poll();

            if(current.equals(goal))
                break;

            for(MatrixNode next : getNeighbors(current)){
                if(!cameFrom.containsKey(next)){
                    frontier.add(next);
                    cameFrom.put(next, current);
                }
            }
        }
        //create path to goal
        current = goal;
        try{
            while(!current.equals(start)){
                path.add(current);
                current = cameFrom.get(current);
            }
        } catch(NullPointerException e) {
            System.out.println("No path found");
        }
        return path;
    }

    private static ArrayList<MatrixNode> getNeighbors(MatrixNode node) {
        ArrayList<MatrixNode> neighbors = new ArrayList<MatrixNode>();
        if(node.y - t >= App.getYmin() && node.x - t >= App.getXmin()) {
            if(!gameMatrix[node.x / t - p][node.y / t - p - 1].isWall)
                neighbors.add(gameMatrix[node.x / t - p][node.y / t - p - 1]);
            if(!gameMatrix[node.x / t - p - 1][node.y / t - p].isWall)
                neighbors.add(gameMatrix[node.x / t - p - 1][node.y / t - p]);
        }   
        if(node.y + t <= App.getYmax() && node.x + t <= App.getXmax()) {
            if(!gameMatrix[node.x / t - p][node.y / t - p + 1].isWall)
                neighbors.add(gameMatrix[node.x / t - p][node.y / t - p + 1]);
            if(!gameMatrix[node.x / t - p + 1][node.y / t - p].isWall)
                neighbors.add(gameMatrix[node.x / t - p + 1][node.y / t - p]);
        }
        return neighbors;
    }
}