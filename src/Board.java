/////////////////////////////////////////////
// File: Board.java
// Authors: Brady Steed and Michael Eaton
// Purpose: Singleton class that represents a Catan board.
//   Keeps track of board state.

//TODO: Add structures for expansions
public class Board {

    private static Board board;
    static final int CORNER_NUM = 6;

    Tile[] tiles;
    Corner[] corners;
    Edge[] edges;
    Chit[] chits;
    boolean shuffleChits;
    boolean shufflePorts;
    boolean complete;
    double width;
    double height;

    public static Board getInstance() {
        if (board == null) {
            board = new Board();
        }
        if (!board.complete) {
            BoardBuilder.build(board);
        }
        return board;
    }

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
}
