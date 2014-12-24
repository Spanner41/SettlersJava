/////////////////////////////////////////////
// File: NetworkPlayer.java
// Authors: Brady Steed and Michael Eaton
// Purpose: Sends and recieves commands via sockets.
// TODO:
//   Create protocol.  Stuff sockets.
//   Change NetworkPlayer into an AI Strategy.
//   Encryption
//   Anti-cheating

class NetworkPlayer extends Player {

    public NetworkPlayer(int id) {
        super(id);
    }

    @Override
    public boolean promptTrade(int sourceID, int[] have, int[] want) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    int promptPlayerSelect(int[] options) {
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
    Tile promptRobberPlace() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    int[] promptResourceSelect(int quantity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    Edge[] promptRoadPlace(int quantity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    int promptDiscard(int quantity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    void showBoardState() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    int promptResourceTypeSelect() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
