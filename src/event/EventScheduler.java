/*
 * File: EventScheduler.java
 * Author: Brady Steed
 * Purpose: Schedules events and notifies players as they occur.
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

import java.util.concurrent.LinkedBlockingQueue;
import player.Player;
import player.PlayerManager;

/**
 *
 * @author brady_000
 */
public class EventScheduler extends LinkedBlockingQueue<Event>{
    
    //This is the main event loop for game progression
    public void run(){
        //each time the queue emptys, it becomes the next player's turn.
        //TODO: Think about keeping a log of moves for statistics/review.
        while(true){
            Event e = poll();
            
            switch(e.getID()){
                case DiceEvent.ROLL: 
                    for(Player player : PlayerManager.getPlayers()) {
                        player.notifyDiceRoll( ((DiceEvent)e).getValue() );
                    }//end for
                break;
            }
        }
    }
}
