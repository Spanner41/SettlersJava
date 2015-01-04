package game;

/////////////////////////////////////////////
// File: Point.java
// Authors: Brady Steed and Michael Eaton
// Purpose: Holds 3D vertex info.
// TODO:
//   Possibly change to 2D.

public class Point {
    public static double distance(Point a, Point b){
        double dx = a.getX()-b.getX();
        double dz = a.getZ()-b.getZ();
        return Math.sqrt((dx*dx) + (dz*dz));
    }//end distance
    
    //Returns the third point in an equalateral triangle.  Assumes clockwise winding.
    public static Point completeTriangle(Point a, Point b) {
        Point midpoint = new Point((a.getX() + b.getX()) / 2, 0, (a.getZ() + b.getZ()) / 2);
        double length = distance(a, b);
        double height = Math.sqrt(3) / 2 * length;
        Point vector = new Point((a.getZ() - b.getZ())/length, 0, (b.getX() - a.getX())/length); //unit vector

        return new Point(midpoint.getX() + (vector.getX() * height), 0, midpoint.getZ() + (vector.getZ() * height));
    }//end findCenter
    
    private double x,y,z;
    
    public Point(double x, double y, double z){
        this.x = x;
        this.y = y;
        this.z = z;
    }//end Point

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setZ(double z) {
        this.z = z;
    }

    @Override
    public String toString() {
        return "" + x + " " + y;
    }
    
    
}//end Point
