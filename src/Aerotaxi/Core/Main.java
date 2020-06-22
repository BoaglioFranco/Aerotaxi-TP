package Aerotaxi.Core;

import Aerotaxi.Controllers.AlertController;
import Aerotaxi.Controllers.MainScreenController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.nio.file.Paths;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        primaryStage.setOnCloseRequest(e ->closeProgram());
        //music();
        Parent root = FXMLLoader.load(getClass().getResource("/Resources/sample.fxml"));
        primaryStage.setTitle("Aerotaxi - Fly with us");
        Scene scene = new Scene(root, 800, 600);
        //scene.getStylesheets().add("sample/sample.css");
        primaryStage.getIcons().add(new Image("/Resources/Images/Avion.png"));
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
        primaryStage.setOnCloseRequest(e ->closeProgram());


    }

    public void closeProgram(){
        DataWarehouse.saveFiles();
    }

    MediaPlayer mediaPlayer;
    public void music(){
        Media h = new Media(Paths.get("src/Resources/music.mp3").toUri().toString());
        mediaPlayer = new MediaPlayer(h);
        mediaPlayer.play();
    }

    public void closeProgram(){
        DataWarehouse.saveFiles();
    }

    public static void main(String[] args) {
        launch(args);

    }

}
