import javafx.scene.paint.Color;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.canvas.Canvas;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author brady_000
 */
public class MainWindow extends Application{

    public MainWindow(){
    }

    @Override
    public void start(Stage primaryStage){
        primaryStage.setTitle("Settlers of Catan");
        
        Scene scene = new Scene(new GamePane(), Color.BLACK);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public Canvas createCanvas(){
        return new Canvas();
    }
    
    public static void main(String[] args){
        Board.getInstance();
        BoardBuilder.placeTiles();
        launch(args);
    }
}
