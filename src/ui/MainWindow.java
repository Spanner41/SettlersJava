package ui;

/////////////////////////////////////////////
// File: MainWindow.java
// Authors: Brady Steed and Michael Eaton
// Purpose: Initializes and displays GUI

// JavaFX axis reminder: Z forward, Y down, and X right
// (Z) x-> (X)
//     I
// (Y) V

import player.Player;
import player.PlayerManager;
import game.Board;
import game.DevelopmentDeck;
import javafx.scene.paint.Color;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Camera;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.PointLight;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.SubScene;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.MouseEvent;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;

public class MainWindow extends Application {

    double oldPositionX = 0.0;
    double oldPositionY = 0.0;
    double angle = 0.0;
    //parent for rotating camera around center axis
    Group cameraTheta = new Group();
    //parent to rotate around "horizon" axis
    Group cameraPhi = new Group();

    public MainWindow() {
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Settlers of Catan");
        Group root = new Group();
        GameGroup game = new GameGroup();
        Group cameraGroup = new Group();
        Group uiGroup = new Group();

        game.addTiles();
        
        SubScene uiScene = drawUI(uiGroup);
        SubScene cameraScene = setupCamera(cameraGroup);
        root.getChildren().addAll(cameraScene, uiScene);
        
        cameraGroup.getChildren().add(game);
        
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public SubScene drawUI(Group uiGroup){
        SubScene subscene = new SubScene(uiGroup, 500, 300, true, SceneAntialiasing.BALANCED);
        
        for (int i = 0; i < DevelopmentDeck.images.length; i++) {
            Canvas canvas = DevelopmentDeck.images[i];
            double scaleFactor = 50/canvas.getHeight();
            canvas.setScaleX(scaleFactor);
            canvas.setScaleY(scaleFactor);
            uiGroup.getChildren().add(canvas);
            canvas.relocate((canvas.getWidth()*scaleFactor) * (i - .5) , (canvas.getHeight()*scaleFactor) * -.5);
        }
        
        return subscene;
    }
    
    public SubScene setupCamera(Group cameraGroup){
        Camera camera = new PerspectiveCamera(true);
        PointLight light = new PointLight(Color.WHITE);
        light.setTranslateY(-20);
        
        camera.setNearClip(0);
        camera.setFarClip(17);
        cameraPhi.getChildren().add(camera);
        cameraTheta.getChildren().add(cameraPhi);
        cameraPhi.setRotationAxis(Rotate.X_AXIS);
        
        cameraGroup.getChildren().addAll(cameraTheta, light);
        camera.getTransforms().add(new Translate(0, 0, -20));
        
        
        SubScene subscene = new SubScene(cameraGroup,500,500);
        subscene.setFill(Color.BLACK);
        subscene.setCamera(camera);
        
        subscene.addEventHandler(MouseEvent.MOUSE_DRAGGED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                double currentPositionX = event.getX();
            double currentPositionY = event.getY();
            double dx = currentPositionX - oldPositionX;
            double dy = oldPositionY - currentPositionY;
            
            cameraTheta.getTransforms().add(new Rotate(dx/3, Rotate.Y_AXIS));
            cameraPhi.getTransforms().add(new Rotate(dy/3, Rotate.X_AXIS));
            
            angle += dy/3;
            if(angle > -5){
                cameraPhi.getTransforms().add(new Rotate(-5-angle, Rotate.X_AXIS));
                angle = -5;
            }
            if(angle < -90){
                cameraPhi.getTransforms().add(new Rotate(-90-angle, Rotate.X_AXIS));
                angle = -90;
            }
            
            oldPositionX = currentPositionX;
            oldPositionY = currentPositionY;
            }
        });
        
        subscene.addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                oldPositionX = event.getX();
            oldPositionY = event.getY();
            }
        });
        
        return subscene;
    }

    public static void main(String[] args) {
        Player test = PlayerManager.createInstance("Human");
        DevelopmentDeck.loadImages("/resources/images/developmentCards.png");
        test.giveResources(new int[]{0, 1, 2, 3, 4});
        Board.getInstance();
        Board.placeTiles();
        launch(args);
    }
}
