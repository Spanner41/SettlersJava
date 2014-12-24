/////////////////////////////////////////////
// File: Point.java
// Authors: Brady Steed and Michael Eaton
// Purpose: Holds 3D vertex info.
// TODO:
//   Possibly change to 2D.

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
