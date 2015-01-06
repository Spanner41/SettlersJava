/*
 * File: TradeEvent.java
 * Author: Brady Steed
 * Purpose: Encapsulates information about trade events for players
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

public class TradeEvent extends Event {
    protected static final int ID_FIRST = 12;
    protected static final int ID_LAST = 13;
    
    public static final int PROPOSE = 12;
    public static final int RESPOND = 13;
    
    public static final int REFUSE = 0;
    public static final int ACCEPT = 1;
    
    private final int target;
    private final int status;
    private final int[] have;
    private final int[] want;

    public TradeEvent(int id, int source, int[] have, int target, int[] want) {
        super(id, source);
        this.target = target;
        this.status = 1;
        this.have = have;
        this.want = want;
    }

    public TradeEvent(int id, int source, int[] have, int target, int[] want, int status) {
        super(id, source);
        this.target = target;
        this.status = status;
        this.have = have;
        this.want = want;
    }

    public int[] getWant() {
        return want;
    }

    public int[] getHave() {
        return have;
    }

    public int getStatus() {
        return status;
    }

    public int getTarget() {
        return target;
    }

}
