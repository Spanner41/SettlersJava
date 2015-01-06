/*
 * File: PlayerStrategy.java
 * Author: Brady Steed
 * Purpose: Abstract class that provides strategy interface
 *    for players to make decisions.
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
import game.Corner;
import game.Tile;

public interface PlayerStrategy {
    abstract boolean promptTrade(int sourceID, int[] have, int[] want);

    abstract int[] promptResourceSelect(int quantity);

    abstract int promptResourceTypeSelect();

    abstract int promptPlayerSelect(int[] options);

    abstract Edge[] promptRoadPlace(int quantity);

    abstract Corner promptSettlementPlace();

    abstract Corner promptCityPlace();

    abstract int promptDiscard(int quantity);

    abstract Tile promptRobberPlace();
    
    abstract void notifyDiceRoll(int value);
    
    abstract void notifyResourceGet(int value);
    
    abstract void notifyResourceDiscard(int value);

    abstract void showBoardState();
}
