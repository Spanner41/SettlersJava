/*
 * File: ResourceEvent.java
 * Author: Brady Steed
 * Purpose: Encapsulates information about resource events for players
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

public class ResourceEvent extends Event {
    private static final int ID_FIRST = 1;
    private static final int ID_LAST = 4;
    
    public static final int GET = 1;
    public static final int DISCARD = 2;
    public static final int STEAL = 3;
    public static final int TRADE = 4;
    
    private final int[] recieved;
    private final int[] lost;
    private final int target;

    public ResourceEvent(int type, int source, int[] recieved) {
        super(type, source);
        this.recieved = recieved;
        target = -1;
        this.lost = null;
    }
    
    public ResourceEvent(int type, int source, int[] recieved, int target, int[] lost) {
        super(type, source);
        this.recieved = recieved;
        this.target = target;
        this.lost = lost;
    }

    public int[] getRecieved() {
        return recieved;
    }
    
    public int[] getLost() {
        return lost;
    }
    
    public int getTarget(){
        return target;
    }
}
