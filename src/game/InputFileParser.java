package game;

/////////////////////////////////////////////
// File: InputFileParser.java
// Authors: Brady Steed and Michael Eaton
// Purpose: Parses a file.  Can only read integers.
//  Counts "//" until the end of line as comments.

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
