/*
 * File: InputFileParser.java
 * Author: Brady Steed
 * Purpose: Parses a file.  Can only read integers.
 *     Counts "//" until the end of line as comments.
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

import java.io.InputStream;
import java.util.Scanner;

public class InputFileParser {

    private final String file;
    private final InputStream stream;
    private final Scanner scanner;

    public InputFileParser(String file) {
        this.file = file;
        stream = InputFileParser.class.getResourceAsStream(file);
        scanner = new Scanner(stream);
    }//end InputFileParser
    
    public int next(){
        String next = scanner.next();
        if(next.length() > 1 && next.charAt(0)=='/' && next.charAt(1)=='/'){
            scanner.nextLine();
            return next();
        }//end if
        return Integer.parseInt(next);
    }
    
    public void close(){
        try{
        scanner.close();
        stream.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
