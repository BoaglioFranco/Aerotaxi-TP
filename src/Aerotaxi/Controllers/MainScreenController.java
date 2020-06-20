package Aerotaxi.Controllers;


import com.jfoenix.controls.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import java.io.IOException;


public class MainScreenController {


    @FXML
    private JFXButton flightButton;

    @FXML
    private JFXDrawer drawer;

    @FXML
    private BorderPane mainPane;


    @FXML
    void orderFlightScreen(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/Resources/flightSearch.fxml"));
        drawer.setSidePane(pane);
        if(drawer.isOpened()){
            drawer.close();
        }
        else
            drawer.open();

    }
    /*
        f object = new f();
        Pane view = object.paneview("/Resources/flightSearch.fxml");
        mainPane.setCenter(view);

     */
}





