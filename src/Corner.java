/**
 *
 * @author brady_000
 */
public class Corner {
    //TODO: consult rules and fix logic...
    static final int EMPTY = -1;
    static final int BLOCKED = 0;
    static final int SETTLEMENT = 1;
    static final int CITY = 2;
    
    static final int NO_PORT = -1;
    static final int PORT = 0;
    static final int PORT_LUMBER = 1;
    static final int PORT_WHEAT = 2;
    static final int PORT_BRICK = 3;
    static final int PORT_SHEEP = 4;
    static final int PORT_ORE = 5;
    
    Tile[] tiles = new Tile[3];
    private int tileCount = 0;
    Edge[] edges = new Edge[3];
    private int edgeCount = 0;
    
    Point vertex;
    int playerID; //starts at -1
    int building; //starts at -1, road blocked: 0, settlement: 1, city:2
    int port;

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
    
    public void addEdge(Edge edge){
        edges[edgeCount] = edge;
        edgeCount++;
        edge.addCorner(this);
    }
    
    void produce(int type) {
        if(building > 0)
            PlayerManager.getInstance(playerID).giveResources(type, building);
    }
    
    void addPort(int port){
        if(this.port == NO_PORT) this.port = port;
    }
}
