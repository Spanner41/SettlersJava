/*
 * File: Player.java
 * Author: Brady Steed
 * Purpose: Abstract class that is controlled by PlayerManager.
 *    Holds game data for players.  All events are handled by PlayerStrategy.
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

import game.Edge;
import game.Board;
import game.Corner;
import game.DevelopmentDeck;
import game.Tile;
import java.util.Random;

public class Player {

    public static final int ORE = 0;
    public static final int SHEEP = 1;
    public static final int BRICK = 2;
    public static final int WHEAT = 3;
    public static final int LUMBER = 4;

    public static final int ROAD = 5;
    public static final int SETTLEMENT = 6;
    public static final int CITY = 7;
    public static final int DEV_CARD = 8;

    private final int playerID;
    boolean threePort;
    private short[] resources = new short[5];
    private boolean[] ports = new boolean[5];
    private short[] devCards = new short[5];
    private int knights;
    private int roads;
    private int victoryPoints;
    PlayerStrategy strategy;

    public Player(int id, PlayerStrategy strategy) {
        playerID = id;
        this.strategy = strategy;
        threePort = false;
        knights = 0;
        roads = 0;
        victoryPoints = 0;

        for (int i = 0; i < resources.length; i++) {
            resources[i] = 0;
            ports[i] = false;
        }//end for

        for (int i = 0; i < devCards.length; i++) {
            devCards[i] = 0;
        }//end for
    }//end Player

    public void giveResources(int[] quantities) {
        for (int i = 0; i < quantities.length; i++) {
            resources[i] += quantities[i];
        }//end for
    }//end giveResources

    public void giveResources(int type, int quantity) {
        resources[type] += quantity;
    }//end giveResources

    public void discardResources(int[] quantities) {
        int total = 0;
        for (int i = 0; i < quantities.length; i++) {
            resources[i] -= quantities[i];
            if (resources[i] < 0) {
                total -= resources[i];
                resources[i] = 0;
            }//end if
        }//end for
        if (total != 0) {
            strategy.promptDiscard(total);
        }//end if
    }//end discardResources

    public int stealResource() {
        Random rand = new Random();
        int target = rand.nextInt(resources[0] + resources[1] + resources[2] + resources[3] + resources[4]);
        for (int i = 0; i < resources.length; i++) {
            target -= resources[i];
            if (target <= 0) {
                resources[i]--;
                return i;
            }//end if
        }//end for
        return -1;
    }//end stealResource

    public int getVictoryPoints() {
        return victoryPoints;
    }
    
    public int stealResources(int type) {
        int temp = resources[type];
        resources[type] = 0;
        return temp;
    }//end stealResource

    public boolean canBuild(int type) {
        switch (type) {
            case SETTLEMENT:
                return (resources[SHEEP] > 0 && resources[BRICK] > 0 && resources[WHEAT] > 0 && resources[LUMBER] > 0);
            case CITY:
                return (resources[WHEAT] >= 2 && resources[ORE] >= 3);
            case ROAD:
                return (resources[BRICK] > 0 && resources[LUMBER] > 0);
            case DEV_CARD:
                return (resources[SHEEP] > 0 && resources[WHEAT] > 0 && resources[ORE] > 0);
            default:
                return false;
        }//end switch
    }//end canBuild

    public int getPlayerID() {
        return playerID;
    }

    public int getKnights() {
        return knights;
    }

    public boolean build(int type) {
        if (!canBuild(type)) {
            return false;
        }
        switch (type) {
            case SETTLEMENT: {
                Corner selected = strategy.promptSettlementPlace();
                if (selected.getBuilding() == Corner.EMPTY
                        || (selected.getBuilding() == Corner.BLOCKED && selected.getBuilding() == playerID)) {
                    if (Board.getInstance().placeSettlement(playerID, selected)) {
                        resources[SHEEP] -= 1;
                        resources[BRICK] -= 1;
                        resources[WHEAT] -= 1;
                        resources[LUMBER] -= 1;
                    }
                }
            }
            break;
            case CITY: {
                Corner selected = strategy.promptCityPlace();
                if (selected.getBuilding() == Corner.SETTLEMENT && selected.getPlayerID() == playerID) {
                    if (Board.getInstance().placeSettlement(playerID, selected)) {
                        resources[WHEAT] -= 2;
                        resources[ORE] -= 3;
                    }//end if
                }//end if
            }
            break;
            case ROAD: {
                Edge selected = strategy.promptRoadPlace(1)[0];
                if (!selected.hasRoad() && selected.getPlayerID() == playerID) {
                    if (Board.getInstance().placeRoad(playerID, selected)) {
                        resources[BRICK] -= 1;
                        resources[LUMBER] -= 1;
                    }//end if
                }//end if
            }
            break;
            case DEV_CARD:
                devCards[DevelopmentDeck.getInstance().randomElement()]++;
                resources[SHEEP] -= 1;
                resources[WHEAT] -= 1;
                resources[ORE] -= 1;
                break;
        }//end switch

        return true;
    }//end canBuild

    public boolean canTradeBank(int have) {
        int rate = 4;
        if (threePort) {
            rate = 3;
        }
        if (ports[have]) {
            rate = 2;
        }
        return resources[have] >= rate;
    }//end canTradeBank

    public boolean tradeBank(int have, int need) {
        int rate = 4;
        if (threePort) {
            rate = 3;
        }
        if (ports[have]) {
            rate = 2;
        }
        if (resources[have] >= rate) {
            resources[have] -= rate;
            resources[need]++;
            return true;
        } else {
            return false;
        }//end if/else
    }//end tradeBank

    public boolean tradePlayer(int sourceID, int destinationID, int[] have, int[] want) {
        for (int i = 0; i < have.length; i++) {
            if (have[i] > resources[i]) {
                return false;
            }
        }//end for

        return PlayerManager.getInstance(destinationID).strategy.promptTrade(sourceID, have, want);
    }//end tradePlayer

    public void useDevCard(int type) {
        if (devCards[type] < 1) {
            return;
        }

        switch (type) {
            case DevelopmentDeck.MONOPOLY:
                int resource = strategy.promptResourceTypeSelect();
                giveResources(resource, PlayerManager.stealResources(resource));
                break;
            case DevelopmentDeck.ROAD_BUILDING:
                strategy.promptRoadPlace(2);
                roads++;
                break;
            case DevelopmentDeck.KNIGHT:
                strategy.promptRobberPlace();
                knights++;
                break;
            case DevelopmentDeck.VICTORY_POINT:
                victoryPoints++;
                break;
            case DevelopmentDeck.YEAR_OF_PLENTY:
                giveResources(strategy.promptResourceSelect(2));
        }//end switch
        devCards[type]--;
    }//end useDevCard

    public boolean promptTrade(int sourceID, int[] have, int[] want){
        return strategy.promptTrade(sourceID, have, want);
    }

    public int[] promptResourceSelect(int quantity){
        return strategy.promptResourceSelect(quantity);
    }

    public int promptResourceTypeSelect(){
        return strategy.promptResourceTypeSelect();
    }

    public int promptPlayerSelect(int[] options){
        return strategy.promptPlayerSelect(options);
    }

    public Edge[] promptRoadPlace(int quantity){
        return strategy.promptRoadPlace(quantity);
    }

    public Corner promptSettlementPlace(){
        return strategy.promptSettlementPlace();
    }

    public Corner promptCityPlace(){
        return strategy.promptCityPlace();
    }

    public int promptDiscard(int quantity){
        return strategy.promptDiscard(quantity);
    }

    public Tile promptRobberPlace(){
        return strategy.promptRobberPlace();
    }

    public void notifyDiceRoll(int value) {
        strategy.notifyDiceRoll(value);
    }
}//end class Player
