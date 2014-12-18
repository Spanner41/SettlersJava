//TODO: subclass for expansions
public class Board {

    private static Board board;
    private static final int CORNER_NUM = 6;

    Tile[] tiles;
    Corner[] corners;
    Edge[] edges;
    Chit[] chits;
    boolean shuffleChits;
    boolean shufflePorts;
    boolean complete;

    public static Board getInstance() {
        if (board == null) {
            board = new Board();
        }
        if (!board.complete) {
            board.build();
        }
        return board;
    }

    private Board() {
        shuffleChits = false;
        shufflePorts = false;
        complete = false;
    }

    public void build() {
        final String path = "/resources/expansionBoard.config";
        InputFileParser fileParser = new InputFileParser(path);

        int cornerCount = fileParser.next();
        int edgeCount = fileParser.next();
        int tileCount = fileParser.next();
        int chitCount = fileParser.next();
        int portCount = fileParser.next();

        corners = new Corner[cornerCount];
        edges = new Edge[edgeCount];
        tiles = new Tile[tileCount];
        chits = new Chit[chitCount];

        //create corners
        for (int i = 0; i < corners.length; i++) {
            corners[i] = new Corner();
        }

        //create and insert edges
        for (int i = 0; i < edges.length; i++) {
            int firstCorner = fileParser.next();
            int secondCorner = fileParser.next();
            edges[i] = new Edge();
            corners[firstCorner].addEdge(edges[i]);
            corners[secondCorner].addEdge(edges[i]);
        }

        //Create and shuffle tiles and chits(circular number tokens)
        Shuffler<Tile> tileStack = new Shuffler();
        for (int i = -1; i < Tile.TILE_TYPES - 1; i++) {
            int count = fileParser.next();
            for (int j = 0; j < count; j++) {
                tileStack.add(new Tile(i));
            }//end for
        }//end for

        System.out.println("tileTypes");
        //Insert tiles and chits
        for (int i = 0; i < tiles.length; i++) {
            tiles[i] = tileStack.randomElement();

            for (int j = 0; j < CORNER_NUM; j++) {
                int corner = fileParser.next();
                corners[corner].addTile(tiles[i]);
            }//end for
        }//end for

        Shuffler<Chit> chitList = new Shuffler();
        for (int i = 0; i < chitCount; i++) {
            chits[i] = new Chit(fileParser.next());
            chitList.add(chits[i]);
        }//end for
        for (Tile t : tiles) {
            if (t.type != Tile.DESERT) {
                if (shuffleChits) {
                    t.chit = chitList.randomElement();
                } else {
                    t.chit = chitList.remove(0);
                }//end if/else
            }//end if
        }//end for

        Shuffler<Integer> portList = new Shuffler();
        for (int i = 0; i < portCount; i++) {
            portList.add(fileParser.next());
        }//end for

        for (int i = 0; i < portCount; i++) {
            int portType;
            if (shufflePorts) {
                portType = portList.randomElement();
            } else {
                portType = portList.remove(0);
            }//end if/else
            corners[fileParser.next()].port = portType;
            corners[fileParser.next()].port = portType;
        }//end for
        
        complete = true;
    }//end build

    public void activateChits(int value) {
        for (Chit c : chits) {
            if (c.value == value) {
                c.activate();
            }
        }
    }//end activateChits

    //TODO: implement
    public boolean placeRoad(int playerID, Edge location) {
        return false;
    }//end placeRoad

    //TODO: implement
    public boolean placeSettlement(int playerID, Corner location) {
        return false;
    }

    //TODO: implement
    public boolean placeCity(int playerID, Corner location) {
        return false;
    }

    public static void main(String[] args) {
     Board board = new Board();
     board.build();
     }//end */
}