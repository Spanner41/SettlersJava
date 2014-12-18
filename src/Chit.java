public class Chit {
    int value;
    int dots;
    Tile tile;
    
    public Chit(int value){
        if (value < 2 || value > 12 || value == 7){
            System.out.println("Error: value=" + value);
            value = 1;
        }
        if(value > 7) dots = 13 - value;
        else dots = value - 1;
    }

    void register(Tile tile) {
        this.tile = tile;
    }//end register
    
    void activate(){
        tile.produce();
    }//end activate
    
    /*public static void main(String[] args) {
        Chit test = new Chit(3);
        System.out.println("Expected: 2; Actual: " + test.dots );
        test = new Chit(8);
        System.out.println("Expected: 5; Actual: " + test.dots );
        
        test = new Chit(1); //Should throw exception
        System.out.println("Expected: ?; Actual: " + test.dots );
    }//end psvm*/
}//end Chit

