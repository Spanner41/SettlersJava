/////////////////////////////////////////////
// File: Tile.java
// Authors: Brady Steed and Michael Eaton
// Purpose: Tile object has refrences to its corners and keeps track of paint related stuff
// TODO:
//   Add texture/3D model stuff

import javafx.scene.paint.Color;

public class Tile {
    static final int TILE_TYPES = 7;
    static final int MOUNTAIN = 0;
    static final int PASTURE = 1;
    static final int HILL = 2;
    static final int FIELD = 3;
    static final int FOREST = 4;
    static final int DESERT = 5;
    static final int SEA = 6;
    
    int id;
    Chit chit;
    int type;
    Corner[] corners;
    int cornerCount = 0;
    
    public Tile(int type){
        if(type < -1 || type > 4) System.out.println("Invalid tile type: " + type);
        this.type = type;
        corners = new Corner[6];
    }//end Tile
    
    void addChit(Chit chit){
        this.chit = chit;
        chit.register(this);
    }//end addChit
    
    @Override
    public String toString(){
        switch(this.type){
            case DESERT: return "desert";
            case MOUNTAIN: return "mountain";
            case PASTURE: return "pasture";
            case HILL: return "hill";
            case FIELD: return "field";
            case FOREST: return "forest";
            default: return "none";
        }
    }
    
    public Color getColor(){
        switch(this.type){
            case DESERT: return Color.ANTIQUEWHITE;
            case MOUNTAIN: return Color.DARKGREY;
            case PASTURE: return Color.CHARTREUSE;
            case HILL: return Color.MAROON;
            case FIELD: return Color.GOLDENROD;
            case FOREST: return Color.DARKGREEN;
            case SEA: return Color.LIGHTBLUE;
            default: return Color.LIGHTBLUE;
        }
    }
    
    //TODO: write this function.
    void produce(){
        /*
        String str;
        switch(this.type){
            case SEA: str = "nothing"; break;
            case DESERT: str = "nothing"; break;
            case MOUNTAIN: str = "ore"; break;
            case PASTURE: str = "sheep"; break;
            case HILL: str = "brick"; break;
            case FIELD: str = "wheat"; break;
            case FOREST: str = "lumber"; break;
            default: str = "nothing";
        }
        
        System.out.println(chit.value + " was rolled. " + str + "was produced.");
        */
        
        for(Corner c: corners){
            if(c==null) continue;
            c.produce(type);
        }
    }//end produce

    void addCorner(Corner c) {
        corners[cornerCount] = c;
        cornerCount++;
    }

    void setID(int i) {
        id = i;
    }
}
