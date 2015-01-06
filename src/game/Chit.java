/*
 * File: Chit.java
 * Author: Brady Steed
 * Purpose: Notifies Tiles of when to produce resources
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

package game;

public class Chit {
    private int value;
    private int dots;
    private Tile tile;
    
    public Chit(int value){
        if (value < 2 || value > 12 || value == 7){
            System.out.println("Error: value=" + value);
            this.value = 1;
        }
        this.value = value;
        if(value > 7) dots = 13 - value;
        else dots = value - 1;
    }

    void register(Tile tile) {
        this.tile = tile;
    }//end register
    
    void activate(){
        tile.produce();
    }//end activate
    
    public int getDots(){
        return dots;
    }//end getDots
    
    public int getValue(){
        return value;
    }//end getValue
}//end Chit

