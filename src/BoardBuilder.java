/////////////////////////////////////////////
// File: BoardBuilder.java
// Authors: Brady Steed and Michael Eaton
// Purpose: Builds and defines coordinates for Board object.

import java.util.LinkedList;
import javafx.scene.shape.MeshView;
import javafx.scene.shape.TriangleMesh;

public class BoardBuilder {

    public static void build(Board board) {
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
            board.corners[fileParser.next()].port = portType;
            board.corners[fileParser.next()].port = portType;
        }//end for
        
        board.complete = true;
    }//end build
    
    static void placeTiles() {
        Board board = Board.getInstance();
        Tile [] tiles = board.tiles;
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

        tiles[0].corners[0].vertex = new Point(0, 0, 0);
        tiles[0].corners[1].vertex = new Point(0, 0, 1);
        tiles[0].setRotation(0);
        tileQueue.add(tiles[0]);
        queued[0] = true;

        while (!tileQueue.isEmpty()) {
            tile = tileQueue.pollFirst();
            MeshView mesh = createMesh();
            tile.setMesh(mesh);

            for (int i = 0; i < tile.corners.length * 2; i++) {
                c1 = tile.corners[i % tile.corners.length];
                c2 = tile.corners[(i + 1) % tile.corners.length];

                if (c1.vertex != null && c2.vertex != null) {
                    if (tile.center == null) {
                        tile.center = completeTriangle(c1.vertex, c2.vertex);
                    }//end if
                    if (tile.corners[(i + 2) % tile.corners.length].vertex == null) {
                        tile.corners[(i + 2) % tile.corners.length].vertex = completeTriangle(tile.center, c2.vertex);
                    }//end if
                }//end if
            }//end for
            
            for (Corner corner : tile.corners) {
                for (Tile next : corner.tiles) {
                    if(next != null && !queued[next.id]){
                        tileQueue.add(next);
                        queued[next.id] = true;
                    }//end if
                }//end for
            }//end for
        }//end while
        
        for (Corner corner : board.corners) {
            minX = Math.min(corner.vertex.x, minX);
            minZ = Math.min(corner.vertex.z, minZ);
            maxX = Math.max(corner.vertex.x, maxX);
            maxZ = Math.max(corner.vertex.z, maxZ);
        }//end for
        
        for (Corner corner : board.corners) {
            corner.vertex.x -= (minX+maxX)/2;
            corner.vertex.z -= (minZ+maxZ)/2;
        }//end for
        
        for (Tile current : board.tiles) {
            current.center.x -= (minX+maxX)/2;
            current.center.z -= (minZ+maxZ)/2;
        }
        board.width = maxX;
        board.height = maxZ;
    }//end

    public static double distance(Point a, Point b){
        double dx = a.x-b.x;
        double dz = a.z-b.z;
        return Math.sqrt((dx*dx) + (dz*dz));
    }//end distance
    
    //Returns the third point in an equalateral triangle.  Assumes clockwise winding.
    static Point completeTriangle(Point a, Point b) {
        Point midpoint = new Point((a.x + b.x) / 2, 0, (a.z + b.z) / 2);
        double length = distance(a, b);
        double height = Math.sqrt(3) / 2 * length;
        Point vector = new Point((a.z - b.z)/length, 0, (b.x - a.x)/length); //unit vector

        return new Point(midpoint.x + (vector.x * height), 0, midpoint.z + (vector.z * height));
    }//end findCenter
    
    private static MeshView createMesh() {
    float[] points = {
        -0.866f, 0f, 0.5f,
        0f, 0f, 1f,
        0.866f, 0f, 0.5f,
        0.866f, 0f, -0.5f,
        0f, 0f, -1f,
        -0.866f, 0f, -0.5f,
        0f, 0f, 0f
    };
    float[] texCoords = {
        0f, 0.25f,
        0.5f, 0f,
        1f, 0.25f,
        1f, 0.75f,
        0.5f, 1f,
        0f, 0.75f,
        0.5f, 0.5f
    };
    int[] faces = {
        6, 6, 1, 1, 0, 0,
        6, 6, 2, 2, 1, 1,
        6, 6, 3, 3, 2, 2,
        6, 6, 4, 4, 3, 3,
        6, 6, 5, 5, 4, 4,
        6, 6, 0, 0, 5, 5,
    };

    TriangleMesh mesh = new TriangleMesh();
    mesh.getPoints().setAll(points);
    mesh.getTexCoords().setAll(texCoords);
    mesh.getFaces().setAll(faces);

    return new MeshView(mesh);
  }
}
