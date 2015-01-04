package player;

/////////////////////////////////////////////
// File: PlayerManager.java
// Authors: Brady Steed and Michael Eaton
// Purpose: Player factory and keeps a list of players.

import java.util.ArrayList;

public class PlayerManager {

    final private static ArrayList<Player> players = new ArrayList();

    public static Player createInstance(String type) {
        if (type.equalsIgnoreCase("HUMAN")) {
            players.add(new Player(players.size(), new HumanStrategy()));
        } else if (type.equalsIgnoreCase("COMPUTER")) {
            players.add(new Player(players.size(), new ComputerStrategy()));
        } else if (type.equalsIgnoreCase("NETWORK")) {
            players.add(new Player(players.size(), new NetworkStrategy()));
        } else {
            return null;
        }
        return players.get(players.size() - 1);
    }//end createInstance

    public static Player getInstance(int playerID) {
        return players.get(playerID);
    }//end getInstance

    //for monopoly card
    public static int stealResources(int type) {

        int total = 0;
        for (Player p : players) {
            total += p.stealResources(type);
        }
        return total;
    }//end stealResources

}
