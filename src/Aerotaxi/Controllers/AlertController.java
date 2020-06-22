package Aerotaxi.Controllers;

import com.jfoenix.controls.JFXButton;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;

import java.util.concurrent.atomic.AtomicBoolean;

public class AlertController {

    public static boolean display(String title, String message,int width,int height,String buttonConfirm,String buttonClose) {

        Stage window = new Stage();

        AtomicBoolean result = new AtomicBoolean(false);

        //Block events to other windows
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(width);
        window.setMinHeight(height);

        Label label = new Label();
        label.setText(message);
        JFXButton closeButton = new JFXButton(buttonClose);
        closeButton.setOnAction(e -> window.close());

        JFXButton confirmButton = new JFXButton(buttonConfirm);
        confirmButton.setOnAction(e ->{
            window.close();
            result.set(true);
        });

        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, confirmButton,closeButton);
        layout.setAlignment(Pos.CENTER);


        //Display window and wait for it to be closed before returning
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();

        return result.get();
    }

}