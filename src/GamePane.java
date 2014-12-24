/////////////////////////////////////////////
// File: GamePane.java
// Authors: Brady Steed and Michael Eaton
// Purpose: Pane for drawing the game

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class GamePane extends Pane {

    private Canvas canvas = new Canvas();

    public GamePane() {
        getChildren().add(canvas);
    }

    @Override
    protected void layoutChildren() {
        final int top = (int) snappedTopInset();
        final int right = (int) snappedRightInset();
        final int bottom = (int) snappedBottomInset();
        final int left = (int) snappedLeftInset();
        final int w = (int) getWidth() - left - right;
        final int h = (int) getHeight() - top - bottom;
        canvas.setLayoutX(left);
        canvas.setLayoutY(top);
        if (w != canvas.getWidth() || h != canvas.getHeight()) {
            canvas.setWidth(w);
            canvas.setHeight(h);
            GraphicsContext gc = canvas.getGraphicsContext2D();
            gc.clearRect(0, 0, w, h);

            drawTiles(gc);
            //drawEdges(gc);
            drawCorners(gc);
        }//end if
    }//end layoutChildren

    private void drawCorners(GraphicsContext gc) {
        Board board = Board.getInstance();
        gc.setFill(Color.WHEAT);

        for (Corner corner : board.corners) {
            if (corner.vertex != null) {
                gc.fillOval(corner.vertex.x * (canvas.getWidth() - 10) / board.width, corner.vertex.y * (canvas.getHeight() - 10) / board.height, 10, 10);
            }

        }
    }

    private void drawTiles(GraphicsContext gc) {
        Board board = Board.getInstance();
        double[] xValues = new double[6];
        double[] yValues = new double[6];

        for (Tile tile : board.tiles) {
            gc.setFill(tile.getColor());
            for (int i = 0; i < tile.corners.length; i++) {
                xValues[i] = (tile.corners[i].vertex.x * (canvas.getWidth() - 10) / board.width) + 5;
                yValues[i] = (tile.corners[i].vertex.y * (canvas.getHeight() - 10) / board.height) + 5;
            }
            gc.fillPolygon(xValues, yValues, xValues.length);
        }

    }

    private void drawEdges(GraphicsContext gc) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}//end GamePane
