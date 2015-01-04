package player;

/////////////////////////////////////////////
// File: NetworkStrategy.java
// Authors: Brady Steed
// Purpose: Implements abstract class, PlayerStrategy.java
//   Communicates with players over a network any time the game-state changes.

import game.Edge;
import game.Corner;
import game.Tile;
import java.net.Socket;

public class NetworkStrategy extends PlayerStrategy {

    Socket socket;
    
    @Override
    boolean promptTrade(int sourceID, int[] have, int[] want) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    int[] promptResourceSelect(int quantity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    int promptResourceTypeSelect() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    int promptPlayerSelect(int[] options) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    Edge[] promptRoadPlace(int quantity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    Corner promptSettlementPlace() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    Corner promptCityPlace() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    int promptDiscard(int quantity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    Tile promptRobberPlace() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    void showBoardState() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
