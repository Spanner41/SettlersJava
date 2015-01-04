package game;

/////////////////////////////////////////////

import player.PlayerManager;

// File: Corner.java
// Authors: Brady Steed and Michael Eaton
// Purpose: Corners serve as vertexes for Tiles and Edges.
//  Tracks cities and settlements

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
