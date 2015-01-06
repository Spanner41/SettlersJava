/*
 * File: Tile.java
 * Author: Brady Steed
 * Purpose: Tile object has refrences to its corners and keeps track of paint related stuff
 * TODO: Add texture/3D model stuff
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

import javafx.scene.paint.Color;
import javafx.scene.shape.DrawMode;
import javafx.scene.shape.MeshView;
import javafx.scene.shape.TriangleMesh;
import javafx.scene.transform.Rotate;

public class Tile {
    public static final int TILE_TYPES = 7;
    public static final int MOUNTAIN = 0;
    public static final int PASTURE = 1;
    public static final int HILL = 2;
    public static final int FIELD = 3;
    public static final int FOREST = 4;
    public static final int DESERT = 5;
    public static final int SEA = 6;
    
    private int id;
    private Chit chit;
    private int type;
    private Point center = null;
    private Corner[] corners;
    private int cornerCount = 0;
    private MeshView mesh = null;
    private float rotation;

    public void setRotation(float rotation) {
        this.rotation = rotation;
        if(mesh!= null){
            mesh.setRotationAxis(Rotate.Y_AXIS);
            mesh.setRotate(rotation);
        }
    }

    public int getType() {
        return type;
    }

    public Chit getChit() {
        return chit;
    }

    public void setChit(Chit chit) {
        this.chit = chit;
    }

    public int getID() {
        return id;
    }

    public Corner getCorner(int x) {
        return corners[x];
    }

    public Corner[] getCorners() {
        return corners;
    }

    public float getRotation() {
        return rotation;
    }

    public MeshView getMesh() {
        return mesh;
    }

    public void setMesh(MeshView mesh) {
        this.mesh = mesh;
        mesh.setDrawMode(DrawMode.FILL);
        mesh.setRotate(rotation);
    }
    
    public Tile(int type){
        if(type < 0 || type > 6) System.out.println("Invalid tile type: " + type);
        this.type = type;
        corners = new Corner[6];
    }//end Tile
    
    void addChit(Chit chit){
        this.chit = chit;
        chit.register(this);
    }//end addChit
    
    @Override
    public String toString(){
        switch(this.type){
            case DESERT: return "desert";
            case MOUNTAIN: return "mountain";
            case PASTURE: return "pasture";
            case HILL: return "hill";
            case FIELD: return "field";
            case FOREST: return "forest";
            case SEA: return "sea";
            default: return "none";
        }
    }
    
    public Color getColor(){
        switch(this.type){
            case DESERT: return Color.ANTIQUEWHITE;
            case MOUNTAIN: return Color.DARKGREY;
            case PASTURE: return Color.CHARTREUSE;
            case HILL: return Color.MAROON;
            case FIELD: return Color.GOLDENROD;
            case FOREST: return Color.DARKGREEN;
            case SEA: return Color.LIGHTBLUE;
            default: return Color.LIGHTBLUE;
        }
    }
    
    public String getResourceString(){
        switch(this.type){
            case DESERT: return "/resources/images/desert.png";
            case MOUNTAIN: return "/resources/images/mountain.png";
            case PASTURE: return "/resources/images/pasture.png";
            case HILL: return "/resources/images/hills.png";
            case FIELD: return "/resources/images/plane.png";
            case FOREST: return "/resources/images/forest.png";
            case SEA: return "/resources/images/sea.png";
            default: return null;
        }
    }
    
    void produce(){
        for(Corner c: corners){
            if(c==null) continue;
            c.produce(type);
        }
    }//end produce

    void addCorner(Corner c) {
        corners[cornerCount] = c;
        cornerCount++;
    }

    void setID(int i) {
        id = i;
    }
    
    public Point getCenter(){
        return center;
    }

    void setCenter(Point center) {
        this.center = center;
    }
    
    public static MeshView createMesh() {
    float[] points = {
        -0.866f, 0f, 0.5f,
        0f, 0f, 1f,
        0.866f, 0f, 0.5f,
        0.866f, 0f, -0.5f,
        0f, 0f, -1f,
        -0.866f, 0f, -0.5f,
        0f, 0f, 0f
    };
    float[] texCoords = {
        0f, 0.25f,
        0.5f, 0f,
        1f, 0.25f,
        1f, 0.75f,
        0.5f, 1f,
        0f, 0.75f,
        0.5f, 0.5f
    };
    int[] faces = {
        6, 6, 1, 1, 0, 0,
        6, 6, 2, 2, 1, 1,
        6, 6, 3, 3, 2, 2,
        6, 6, 4, 4, 3, 3,
        6, 6, 5, 5, 4, 4,
        6, 6, 0, 0, 5, 5,
    };

    TriangleMesh mesh = new TriangleMesh();
    mesh.getPoints().setAll(points);
    mesh.getTexCoords().setAll(texCoords);
    mesh.getFaces().setAll(faces);

    return new MeshView(mesh);
  }
}
