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
        System.out.println(next);
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