/*
 * File: Edge.java
 * Author: Brady Steed
 * Purpose: Edges keep track of roads.  Attached to a corner at either end.
 *     Tracks roads
 *
 * Copyright (C) 2015 Brady Steed
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */

package game;

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
