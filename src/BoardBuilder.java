import java.util.LinkedList;

public class BoardBuilder {

    static void placeTiles() {
        Board board = Board.getInstance();
        Tile [] tiles = board.tiles;
        LinkedList<Tile> tileQueue = new LinkedList();
        Point center = null;
        Tile tile;
        Corner c1, c2;
        double minX = 0;
        double minY = 0;
        double maxX = 0;
        double maxY = 0;
        boolean[] queued = new boolean[tiles.length];
        
        for (int i = 0; i < queued.length; i++) {
            queued[i] = false;
        }

        tiles[0].corners[0].vertex = new Point(0, 0, 0);
        tiles[0].corners[1].vertex = new Point(0, 1, 0);
        tileQueue.add(tiles[0]);
        queued[0] = true;

        while (!tileQueue.isEmpty()) {
            tile = tileQueue.pollFirst();

            for (int i = 0; i < tile.corners.length * 2; i++) {
                c1 = tile.corners[i % tile.corners.length];
                c2 = tile.corners[(i + 1) % tile.corners.length];

                if (c1.vertex != null && c2.vertex != null) {
                    if (center == null) {
                        center = completeTriangle(c1.vertex, c2.vertex);
                    }//end if
                    if (tile.corners[(i + 2) % tile.corners.length].vertex == null) {
                        tile.corners[(i + 2) % tile.corners.length].vertex = completeTriangle(center, c2.vertex);
                        minX = Math.min(tile.corners[(i + 2) % tile.corners.length].vertex.x, minX);
                        minY = Math.min(tile.corners[(i + 2) % tile.corners.length].vertex.y, minY);
                    }//end if
                }//end if
            }//end for
            
            center = null;
            
            for (Corner corner : tile.corners) {
                for (Tile next : corner.tiles) {
                    if(next != null && !queued[next.id]){
                        tileQueue.add(next);
                        queued[next.id] = true;
                    }//end if
                }//end for
            }//end for
        }//end while
        for (int i = 0; i < board.corners.length; i++) {
            if(board.corners[i].vertex == null)
                System.out.println(i);
            
        }
        for (Corner corner : board.corners) {
            if(corner.vertex == null){
                System.out.println(corner);
                continue;
            }
            corner.vertex.x -= minX;
            corner.vertex.y -= minY;
            
            if(maxX < corner.vertex.x) maxX = corner.vertex.x;
            if(maxY < corner.vertex.y) maxY = corner.vertex.y;
        }//end for
        
        board.width = maxX;
        board.height = maxY;
    }//end

    public static double distance(Point a, Point b){
        double dx = a.x-b.x;
        double dy = a.y-b.y;
        return Math.sqrt((dx*dx) + (dy*dy));
    }//end distance
    
    //Returns the third point in an equalateral triangle.  Assumes clockwise winding.
    static Point completeTriangle(Point a, Point b) {
        Point midpoint = new Point((a.x + b.x) / 2, (a.y + b.y) / 2, 0);
        double length = distance(a, b);
        double height = Math.sqrt(3) / 2 * length;
        Point vector = new Point((a.y - b.y)/length, (b.x - a.x)/length, 0); //unit vector

        return new Point(midpoint.x + (vector.x * height), midpoint.y + (vector.y * height), 0);
    }//end findCenter
}
