/*
 * File: Shuffler.java
 * Author: Brady Steed
 * Purpose: Holds 3D vertex info.
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
    
    public void translateX(double x) {
        this.x += x;
    }

    public void translateY(double y) {
        this.y += y;
    }

    public void translateZ(double z) {
        this.z += z;
    }

    @Override
    public String toString() {
        return "" + x + " " + y;
    }
    
    
}//end Point
