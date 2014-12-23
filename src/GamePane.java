import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author brady_000
 */
public class GamePane extends Pane {

    private Canvas canvas = new Canvas();

    public GamePane() {
        getChildren().add(canvas);
    }

    @Override
    protected void layoutChildren() {
        final int top = (int)snappedTopInset();
        final int right = (int)snappedRightInset();
        final int bottom = (int)snappedBottomInset();
        final int left = (int)snappedLeftInset();
        final int w = (int)getWidth() - left - right;
        final int h = (int)getHeight() - top - bottom;
        canvas.setLayoutX(left);
        canvas.setLayoutY(top);
        if (w != canvas.getWidth() || h != canvas.getHeight()) {
            canvas.setWidth(w);
            canvas.setHeight(h);
            GraphicsContext gc = canvas.getGraphicsContext2D();
            gc.clearRect(0, 0, w, h);
            
            drawShapes(gc);
        }//end if
    }//end layoutChildren
    
    private void drawShapes(GraphicsContext gc){
        Board board = Board.getInstance();
        gc.setFill(Color.WHEAT);
        
        
        for (Corner corner : board.corners) {
            if(corner.vertex != null)
                gc.fillOval(corner.vertex.x * (canvas.getWidth()-10) / board.width, corner.vertex.y * (canvas.getHeight()-10) / board.height, 10, 10);
        }
    }
}//end GamePane
