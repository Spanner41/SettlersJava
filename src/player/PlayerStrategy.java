package player;


import game.Edge;
import game.Corner;
import game.Tile;

/////////////////////////////////////////////
// File: PlayerStrategy.java
// Authors: Brady Steed
// Purpose: Abstract clas that provides strategy interface
//   for players to make decisions.

public abstract class PlayerStrategy {
    abstract boolean promptTrade(int sourceID, int[] have, int[] want);

    abstract int[] promptResourceSelect(int quantity);

    abstract int promptResourceTypeSelect();

    abstract int promptPlayerSelect(int[] options);

    abstract Edge[] promptRoadPlace(int quantity);

    abstract Corner promptSettlementPlace();

    abstract Corner promptCityPlace();

    abstract int promptDiscard(int quantity);

    abstract Tile promptRobberPlace();

    abstract void showBoardState();
}
