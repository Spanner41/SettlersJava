import java.util.Random;

public abstract class Player {

    static final int ORE = 0;
    static final int SHEEP = 1;
    static final int BRICK = 2;
    static final int WHEAT = 3;
    static final int LUMBER = 4;

    static final int ROAD = 5;
    static final int SETTLEMENT = 6;
    static final int CITY = 7;
    static final int DEV_CARD = 8;

    int playerID;
    boolean threePort;
    int[] resources = new int[5];
    boolean[] ports = new boolean[5];
    int[] devCards = new int[5];
    int knights;
    int victoryPoints;

    public Player(int id) {
        playerID = id;
        threePort = false;
        knights = 0;
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
            promptDiscard(total);
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

    public boolean build(int type) {
        if (!canBuild(type)) {
            return false;
        }
        switch (type) {
            case SETTLEMENT: {
                Corner selected = promptSettlementPlace();
                if (selected.building == Corner.EMPTY
                        || (selected.building == Corner.BLOCKED && selected.playerID == playerID)) {
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
                Corner selected = promptCityPlace();
                if (selected.building == Corner.SETTLEMENT && selected.playerID == playerID) {
                    if (Board.getInstance().placeSettlement(playerID, selected)) {
                        resources[WHEAT] -= 2;
                        resources[ORE] -= 3;
                    }//end if
                }//end if
            }
            break;
            case ROAD: {
                Edge selected = promptRoadPlace(1)[0];
                if (!selected.hasRoad && selected.playerID == playerID) {
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

        return PlayerManager.getInstance(destinationID).promptTrade(sourceID, have, want);
    }//end tradePlayer

    public void useDevCard(int type) {
        if (devCards[type] < 1) {
            return;
        }

        switch (type) {
            case DevelopmentDeck.MONOPOLY:
                int resource = promptResourceTypeSelect();
                giveResources(resource, PlayerManager.stealResources(resource));
                break;
            case DevelopmentDeck.ROAD_BUILDING:
                promptRoadPlace(2);
                break;
            case DevelopmentDeck.SOLDIER:
                promptRobberPlace();
                break;
            case DevelopmentDeck.VICTORY_POINT:
                victoryPoints++;
                break;
            case DevelopmentDeck.YEAR_OF_PLENTY:
                giveResources(promptResourceSelect(2));
        }//end switch
        devCards[type]--;
    }//end useDevCard

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
}//end class Player
