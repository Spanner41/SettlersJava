/////////////////////////////////////////////
// File: PlayerManager.java
// Authors: Brady Steed and Michael Eaton
// Purpose: Player factory and keeps a list of players.

import java.util.ArrayList;

public class PlayerManager {

    final private static ArrayList<Player> players = new ArrayList();

    static Player createInstance(String type) {
        if (type.equalsIgnoreCase("HUMAN")) {
            players.add(new HumanPlayer(players.size()));
        } else if (type.equalsIgnoreCase("COMPUTER")) {
            players.add(new ComputerPlayer(players.size()));
        } else if (type.equalsIgnoreCase("NETWORK")) {
            players.add(new NetworkPlayer(players.size()));
        } else {
            return null;
        }
        return players.get(players.size() - 1);
    }//end createInstance

    static Player getInstance(int playerID) {
        return players.get(playerID);
    }//end getInstance

    //for monopoly card
    static int stealResources(int type) {

        int total = 0;
        for (Player p : players) {
            total += p.resources[type];
            p.resources[type] = 0;
        }
        return total;
    }//end stealResources

}
