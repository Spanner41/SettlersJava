package game;

/////////////////////////////////////////////
// File: Dice.java
// Authors: Brady Steed and Michael Eaton
// Purpose: Gives random number from 2-12 with the same odds as rolling 2 dice.

import java.util.Random;

public class Dice {
    private static final Random rand = new Random();
    
    static int roll(){
        return 2 + rand.nextInt(6) + rand.nextInt(6);
    }
    
    /*public static void main(String[] args) {
        int[] test = {0,0,0,0,0,0,0,0,0,0,0};
        int runs = 100000;
        //for(int i = 0; i < 50; i++) System.out.println(roll());
        
        for(int i = 0; i < runs; i++) test[roll()-2]++;
        for(int i = 0; i < test.length; i++)
            System.out.println("Probability of "+ (i+2) + " is: " + ((float)test[i] / runs));
    }//end psvm*/
}
