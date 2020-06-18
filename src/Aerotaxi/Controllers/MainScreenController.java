package Aerotaxi.Controllers;


import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class MainScreenController {


    @FXML
    private JFXButton flightButton;

    @FXML
    private JFXButton atras;

    @FXML
    private JFXDrawer drawer;

    @FXML
    void orderFlight(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/Resources/Screen1.fxml"));
        drawer.setSidePane(pane);
        if(drawer.isOpened()){
            drawer.close();
        }
        else
            drawer.open();

    }



}
