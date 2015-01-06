/*
 * File: PlayerManager.java
 * Author: Brady Steed
 * Purpose: Player factory and keeps a list of players.
 *
 * Copyright (C) 2015 Brady Steed
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */

package player;

import java.util.ArrayList;

public class PlayerManager {

    private static ArrayList<Player> players = new ArrayList();

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

    public static ArrayList<Player> getPlayers() {
        return players;
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
