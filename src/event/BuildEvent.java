/*
 * File: BuildEvent.java
 * Author: Brady Steed
 * Purpose: Encapsulates information about build events for players.
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

import game.Corner;
import game.Edge;

public class BuildEvent extends Event {
    private static final int ID_FIRST = 8;
    private static final int ID_LAST = 11;
    
    public static final int CITY = 8;
    public static final int SETTLEMENT = 9;
    public static final int ROAD = 10;
    public static final int DEVCARD = 11;
    
    private final Corner corner;
    private final Edge edge;
    
    public BuildEvent(int id, int source, Corner corner) {
        super(id, source);
        this.corner = corner;
        edge = null;
    }
    
    public BuildEvent(int id, int source, Edge edge) {
        super(id, source);
        this.edge = edge;
        corner = null;
    }
    
    public BuildEvent(int id, int source) {
        super(id, source);
        edge = null;
        corner = null;
    }

    public Corner getCorner() {
        return corner;
    }

    public Edge getEdge() {
        return edge;
    }

}
