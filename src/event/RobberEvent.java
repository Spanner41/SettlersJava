/*
 * File: RobberEvent.java
 * Author: Brady Steed
 * Purpose: Encapsulates information about robber events for players.
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

package event;

import game.Tile;

public class RobberEvent extends Event {
    protected static final int ID_FIRST = 6;
    protected static final int ID_LAST = 6;
    
    public static final int PLACED = 6;
    
    private final Tile tile;
    
    public RobberEvent(int id, int source, Tile tile) {
        super(id, source);
        this.tile = tile;
    }//end RobberEvent

    public Tile getTile() {
        return tile;
    }//end RobberEvent
}//end RobberEvent
