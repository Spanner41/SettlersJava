package game;


import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;

/////////////////////////////////////////////
// File: DevelopmentDeck.java
// Authors: Brady Steed and Michael Eaton
// Purpose: Gives development cards to players in random order.
// TODO:
//   Configure for expansions.  Need to add more card types and put deck ammounts in config file.

public class DevelopmentDeck extends Shuffler<Integer> {
    private static DevelopmentDeck devDeck;
    public final static int KNIGHT = 0;
    public final static int VICTORY_POINT = 1;
    public final static int MONOPOLY = 2;
    public final static int ROAD_BUILDING = 3;
    public final static int YEAR_OF_PLENTY = 4;

    //Deck contains 14 soldiers, 5 victory points, 2 monopolies, etc.
    public final static int[] startingCards = {14, 5, 2, 2, 2};
    public static Canvas[] images = new Canvas[5];
    
    public static DevelopmentDeck getInstance(){
        if(devDeck == null) devDeck = new DevelopmentDeck();
        return devDeck;
    }
    
    private DevelopmentDeck() {
        super();

        for (int i = 0; i < startingCards.length; i++) {
            for (int j = 0; j < startingCards[i]; j++) {
                add(i); //initialize deck based on game rules
            }//end for
        }//end for
    }

    public static String cardToString(int x) {
        switch (x) {
            case KNIGHT:
                return "Soldier";
            case VICTORY_POINT:
                return "Victory Point";
            case MONOPOLY:
                return "Monopoly";
            case ROAD_BUILDING:
                return "Road Building";
            case YEAR_OF_PLENTY:
                return "Year of Plenty";
            default:
                return "None";
        }
    }//end cardToString

    @Override
    public String toString() {
        if (this.size() != 1) return "" + this.size() + " cards left.";
        else return "" + this.size() + " card left.";
    }

    public static void loadImages(String path){
        Image image = new Image(DevelopmentDeck.class.getResourceAsStream(path));
        double width = image.getWidth()/5;
        double height = image.getHeight();
        //once for each type of development card
        for (int i = 0; i < images.length; i++) {
            images[i] = new Canvas(width, height);
            images[i].getGraphicsContext2D().drawImage(image, i * width, 0, width, height, 0, 0, width, height);
        }
    }
}
