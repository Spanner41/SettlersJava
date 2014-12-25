/////////////////////////////////////////////
// File: GamePane.java
// Authors: Brady Steed and Michael Eaton
// Purpose: Pane for drawing the game

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.MeshView;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;

public class GameGroup extends Group {

    public GameGroup() {
    }

    public void addCorners() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void addTiles() {
        for (Tile tile : Board.getInstance().tiles) {
            MeshView mesh = tile.getMesh();
            PhongMaterial material = new PhongMaterial(Color.WHITE);
            material.setDiffuseMap(new Image(GameGroup.class.getResourceAsStream(tile.getResourceString())));
            mesh.setMaterial(material);
            mesh.getTransforms().add(new Translate(tile.center.x, tile.center.y, tile.center.z));
            getChildren().add(mesh);
        }
    }

    public void addEdges() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}//end GamePane
