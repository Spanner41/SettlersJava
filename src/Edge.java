/**
 *
 * @author brady_000
 */
public class Edge {
    Corner left;
    Corner right;
    boolean hasRoad;
    int playerID;
    
    public Edge(){
        hasRoad = false;
        playerID = -1;
    }//end Edge
    
    void addCorner(Corner corner) {
        if(left == null)
            left = corner;
        else if(right == null)
            right = corner;
    }//end addCorner
}
