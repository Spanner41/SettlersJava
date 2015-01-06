/*
 * File: VictoryPointEvent.java
 * Author: Brady Steed
 * Purpose: Encapsulates information about victory point changes for players
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

public class VictoryPointEvent extends Event {
    protected static final int ID_FIRST = 14;
    protected static final int ID_LAST = 16;
    
    public static final int LONGEST_ROAD = 14;
    public static final int LARGEST_ARMY = 15;
    public static final int GET_POINTS = 16;
    
    private final int points;
    
    public VictoryPointEvent(int id, int source) {
        super(id, source);
        if(id == LONGEST_ROAD || id == LARGEST_ARMY) points = 2;
        else points = 1;
    }

    public int getPoints() {
        return points;
    }
}
