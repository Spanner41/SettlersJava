/*
 * File: GameGroup.java
 * Author: Brady Steed
 * Purpose: Pane for drawing the 3D game board
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

package ui;

import game.Board;
import game.Tile;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.MeshView;
import javafx.scene.transform.Translate;

public class GameGroup extends Group {

    public GameGroup() {
    }

    public void addCorners() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void addTiles() {
        for (Tile tile : Board.getTiles()) {
            MeshView mesh = tile.getMesh();
            PhongMaterial material = new PhongMaterial(Color.WHITE);
            material.setDiffuseMap(new Image(GameGroup.class.getResourceAsStream(tile.getResourceString())));
            mesh.setMaterial(material);
            mesh.getTransforms().add(new Translate(tile.getCenter().getX(), tile.getCenter().getY(), tile.getCenter().getZ()));
            getChildren().add(mesh);
        }
    }

    public void addEdges() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}//end GamePane
