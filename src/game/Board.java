package game;

/////////////////////////////////////////////
import java.util.LinkedList;
import javafx.scene.shape.MeshView;

// File: Board.java
// Authors: Brady Steed and Michael Eaton
// Purpose: Singleton class that represents a Catan board.
//   Keeps track of board state.
//TODO: Add structures for expansions
public class Board {

    private static Board board;
    static final int CORNER_NUM = 6;

    public static Board getInstance() {
        if (board == null) {
            board = new Board();
        }
        if (!board.complete) {
            build();
        }
        return board;
    }

    public static void build() {
        //Eventually, choose this from a menu.
        final String path = "/resources/standardBoard.config";
        InputFileParser fileParser = new InputFileParser(path);

        int cornerCount = fileParser.next();
        int edgeCount = fileParser.next();
        int tileCount = fileParser.next();
        int chitCount = fileParser.next();
        int portCount = fileParser.next();

        board.corners = new Corner[cornerCount];
        board.edges = new Edge[edgeCount];
        board.tiles = new Tile[tileCount];
        board.chits = new Chit[chitCount];

        //create corners
        for (int i = 0; i < board.corners.length; i++) {
            board.corners[i] = new Corner();
        }

        //create and insert edges
        for (int i = 0; i < board.edges.length; i++) {
            int firstCorner = fileParser.next();
            int secondCorner = fileParser.next();
            board.edges[i] = new Edge();
            board.corners[firstCorner].addEdge(board.edges[i]);
            board.corners[secondCorner].addEdge(board.edges[i]);
        }

        //Create and shuffle tiles and chits(circular number tokens)
        Shuffler<Tile> tileStack = new Shuffler();
        for (int i = 0; i < Tile.TILE_TYPES; i++) {
            int count = fileParser.next();
            for (int j = 0; j < count; j++) {
                tileStack.add(new Tile(i));
            }//end for
        }//end for

        //Insert tiles and chits
        for (int i = 0; i < board.tiles.length; i++) {
            board.tiles[i] = tileStack.randomElement();
            board.tiles[i].setID(i);

            for (int j = 0; j < Board.CORNER_NUM; j++) {
                int corner = fileParser.next();
                board.corners[corner].addTile(board.tiles[i]);
            }//end for
        }//end for

        Shuffler<Chit> chitList = new Shuffler();
        for (int i = 0; i < chitCount; i++) {
            board.chits[i] = new Chit(fileParser.next());
            chitList.add(board.chits[i]);
        }//end for
        for (Tile t : board.tiles) {
            if (t.type != Tile.DESERT) {
                if (board.shuffleChits) {
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
            if (board.shufflePorts) {
                portType = portList.randomElement();
            } else {
                portType = portList.remove(0);
            }//end if/else
            board.corners[fileParser.next()].setPort(portType);
            board.corners[fileParser.next()].setPort(portType);
        }//end for

        board.complete = true;
    }//end build

    public static void placeTiles() {
        Tile[] tiles = board.tiles;
        LinkedList<Tile> tileQueue = new LinkedList();
        Tile tile;
        Corner c1, c2;
        double minX = 0;
        double minZ = 0;
        double maxX = 0;
        double maxZ = 1;
        boolean[] queued = new boolean[tiles.length];

        for (int i = 0; i < queued.length; i++) {
            queued[i] = false;
        }

        tiles[0].corners[0].setVertex(new Point(0, 0, 0));
        tiles[0].corners[1].setVertex(new Point(0, 0, 1));
        tiles[0].setRotation(0);
        tileQueue.add(tiles[0]);
        queued[0] = true;

        while (!tileQueue.isEmpty()) {
            tile = tileQueue.pollFirst();
            MeshView mesh = Tile.createMesh();
            tile.setMesh(mesh);

            for (int i = 0; i < tile.corners.length * 2; i++) {
                c1 = tile.corners[i % tile.corners.length];
                c2 = tile.corners[(i + 1) % tile.corners.length];

                if (c1.getVertex() != null && c2.getVertex() != null) {
                    if (tile.getCenter() == null) {
                        tile.setCenter(Point.completeTriangle(c1.getVertex(), c2.getVertex()));
                    }//end if
                    if (tile.corners[(i + 2) % tile.corners.length].getVertex() == null) {
                        tile.corners[(i + 2) % tile.corners.length].setVertex(Point.completeTriangle(tile.getCenter(), c2.getVertex()));
                    }//end if
                }//end if
            }//end for

            for (Corner corner : tile.corners) {
                for (Tile next : corner.getTiles()) {
                    if (next != null && !queued[next.id]) {
                        tileQueue.add(next);
                        queued[next.id] = true;
                    }//end if
                }//end for
            }//end for
        }//end while

        for (Corner corner : board.corners) {
            minX = Math.min(corner.getCenter().getX(), minX);
            minZ = Math.min(corner.getCenter().getZ(), minZ);
            maxX = Math.max(corner.getCenter().getX(), maxX);
            maxZ = Math.max(corner.getCenter().getZ(), maxZ);
        }//end for

        for (Corner corner : board.corners) {
            corner.getCenter().setX(corner.getCenter().getX() - (minX + maxX) / 2);
            corner.getCenter().setZ(corner.getCenter().getZ() - (minX + maxX) / 2);
        }//end for

        for (Tile current : board.tiles) {
            current.getCenter().setX(current.getCenter().getX() - (minX + maxX) / 2);
            current.getCenter().setZ(current.getCenter().getZ() - (minX + maxX) / 2);
        }
        board.width = maxX;
        board.height = maxZ;
    }//end

    public static Tile[] getTiles() {
        return board.tiles;
    }

    private Tile[] tiles;
    private Corner[] corners;
    private Edge[] edges;
    private Chit[] chits;
    public boolean shuffleChits;
    public boolean shufflePorts;
    public boolean complete;
    public double width;
    public double height;

    private Board() {
        shuffleChits = false;
        shufflePorts = false;
        complete = false;
        width = 0;
        height = 0;
    }

    public void activateChits(int value) {
        for (Chit c : chits) {
            if (c.value == value) {
                c.activate();
            }
        }
    }//end activateChits

    //TODO: implement
    public boolean placeRoad(int playerID, Edge location) {
        return location.setRoad(playerID);
    }//end placeRoad

    //TODO: implement
    public boolean placeSettlement(int playerID, Corner location) {
        return false;
    }

    //TODO: implement
    public boolean placeCity(int playerID, Corner location) {
        return false;
    }
}