package game;

/////////////////////////////////////////////
// File: Edge.java
// Authors: Brady Steed and Michael Eaton
// Purpose: Edges keep track of roads.  Attached to a corner at either end.

public class Edge {
    private Corner left;
    private Corner right;
    private boolean road;
    private int playerID;

    public boolean hasRoad() {
        return road;
    }

    public int getPlayerID() {
        return playerID;
    }

    public boolean setRoad(int playerID) {
        if(!canPlaceRoad(playerID)) return false;
        road = true;
        this.playerID = playerID;
        return true;
    }
    
    public Edge(){
        road = false;
        playerID = -1;
    }//end Edge
    
    public void addCorner(Corner corner) {
        if(left == null)
            left = corner;
        else if(right == null)
            right = corner;
    }//end addCorner

    public boolean canPlaceRoad(int playerID) {
        if(road) return false;
        if(left.getPlayerID() == playerID || right.getPlayerID() == playerID)
            return true;
        
        if(left.getPlayerID() == -1)
            for(Edge edge: left.getEdges()){
                if(edge.getPlayerID() == playerID)
                    return true;
            }
        
        if(right.getPlayerID() == -1)
            for(Edge edge: right.getEdges()){
                if(edge.getPlayerID() == playerID)
                    return true;
            }
        
        return false;
    }
}
