package Aerotaxi.Core;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.nio.file.Paths;

public class Main extends Application {

    MediaPlayer mediaPlayer;

    @Override
    public void start(Stage primaryStage) throws Exception{
        music();
        Parent root = FXMLLoader.load(getClass().getResource("/Resources/sample.fxml"));
        primaryStage.setTitle("Aerotaxi - Fly with us");
        Scene scene = new Scene(root, 800, 600);
        //scene.getStylesheets().add("sample/sample.css");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);

    }

    public void music() {
        String s = "/Resources/music.mp3";
        Media h = new Media(Paths.get(s).toUri().toString());
        mediaPlayer = new MediaPlayer(h);
        mediaPlayer.play();

    }

}
