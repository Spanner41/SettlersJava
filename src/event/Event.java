/*
 * File: Event.java
 * Author: Brady Steed
 * Purpose: Abstract class for event data structures.
 *    Describes any changes in game state.
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

public abstract class Event {
    private static final int ID_FIRST = 0;
    private static final int ID_LAST = 0;
    
    private final int source;
    protected final int type;

    public Event(int id, int source){
        this.source = source;
        this.type = id;
        if(this.type < ID_FIRST || this.type > ID_LAST)
            System.out.println("Event ID out of range.");
    }
    
    public int getSource() {
        return source;
    }

    public int getID() {
        return type;
    }
}
