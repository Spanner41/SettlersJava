/*
 * File: Corner.java
 * Author: Brady Steed
 * Purpose: Corners serve as vertexes for Tiles and Edges.
 *     Tracks cities and settlements
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

import player.PlayerManager;

public class Corner {
    //TODO: consult rules and fix logic...
    public static final int EMPTY = -1;
    public static final int BLOCKED = 0;
    public static final int SETTLEMENT = 1;
    public static final int CITY = 2;
    
    public static final int NO_PORT = -1;
    public static final int PORT = 0;
    public static final int PORT_LUMBER = 1;
    public static final int PORT_WHEAT = 2;
    public static final int PORT_BRICK = 3;
    public static final int PORT_SHEEP = 4;
    public static final int PORT_ORE = 5;
    
    private Tile[] tiles = new Tile[3];
    private  int tileCount = 0;
    private Edge[] edges = new Edge[3];
    private  int edgeCount = 0;
    
    private Point vertex;
    private int playerID; //starts at -1
    private int building; //starts at -1, road blocked: 0, settlement: 1, city:2
    private int port;

    public boolean setSettlement(int playerID) {
        if(this.building != EMPTY || this.playerID != -1) return false;
        building = SETTLEMENT;
        this.playerID = playerID;
        return true;
    }
    
    public boolean setCity(int playerID){
        if(this.building != SETTLEMENT || this.playerID != playerID) return false;
        this.building = CITY;
        return true;
    }

    public int getBuilding() {
        return building;
    }

    public int getPlayerID() {
        return playerID;
    }

    public Corner(){
        playerID = -1;
        building = EMPTY;
        port = NO_PORT;
        vertex = null;
    }
    
    public void addTile(Tile tile){
        tiles[tileCount] = tile;
        tileCount++;
        tile.addCorner(this);
    }

    public Tile[] getTiles() {
        return tiles;
    }
    
    public void addEdge(Edge edge){
        edges[edgeCount] = edge;
        edgeCount++;
        edge.addCorner(this);
    }

    public Edge[] getEdges() {
        return edges;
    }
    
    void produce(int type) {
        if(building > 0)
            PlayerManager.getInstance(playerID).giveResources(type, building);
    }
    
    void addPort(int port){
        if(this.port == NO_PORT) this.port = port;
    }
    
    public Point getCenter(){
        return vertex;
    }

    void setCenter(Point vertex) {
        this.vertex = vertex;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public void setVertex(Point vertex) {
        this.vertex = vertex;
    }

    public Point getVertex() {
        return vertex;
    }
}
