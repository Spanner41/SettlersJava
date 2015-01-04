package game;

/////////////////////////////////////////////
// File: Shuffler.java
// Authors: Brady Steed and Michael Eaton
// Purpose: An ArrayList that can remove and return a random element

import java.util.ArrayList;
import java.util.Random;

public class Shuffler<E> extends ArrayList<E>{
    Random rand;
        
    public Shuffler(){
        super();
        rand = new Random();
    }//end constructor
        
    public E randomElement(){
        if(this.size()==0)
            throw new IllegalStateException("Shuffler is empty.");
        return remove(rand.nextInt(this.size()));
    }//end randomElement
        
    /*public static void main(String []args){
        Shuffler<Integer> test = new Shuffler();
        for(int i = 1; i < 7; i++)
            test.add(i);
        
        try {
            while(true) {
                System.out.println(test.randomElement());
            }//end while
        } catch (IllegalStateException e) {
            System.out.println("The shuffler is empty.");
        } catch (Exception e) {
            System.out.println(e.toString());
        }//end try/catch
    }//end psvm*/
}//end Shuffler