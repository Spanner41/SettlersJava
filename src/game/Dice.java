/*
 * File: Dice.java
 * Author: Brady Steed
 * Purpose: Gives random number from 2-12 with the same odds as rolling 2 dice.
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

/////////////////////////////////////////////
// File: Dice.java
// Author: Brady Steed 
// Purpose: 

import java.util.Random;

public class Dice {
    private static final Random rand = new Random();
    
    static int roll(){
        return 2 + rand.nextInt(6) + rand.nextInt(6);
    }//end roll
}//end Dice
