/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author brady_000
 */
public class Point {
    double x,y,z;
    
    public Point(double x, double y, double z){
        this.x = x;
        this.y = y;
        this.z = z;
    }//end Point

    @Override
    public String toString() {
        return "" + x + " " + y;
    }
    
    
}//end Point
