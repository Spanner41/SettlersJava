/*
 * File: Shuffler.java
 * Author: Brady Steed
 * Purpose: An ArrayList that can remove and return a random element.
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

import java.util.ArrayList;
import java.util.Random;

public class Shuffler<E> extends ArrayList<E>{
    private static Random rand = new Random();
        
    public Shuffler(){
        super();
    }//end constructor
        
    public E randomElement(){
        if(this.size()==0)
            throw new IllegalStateException("Shuffler is empty.");
        return remove(rand.nextInt(this.size()));
    }//end randomElement
}//end Shuffler