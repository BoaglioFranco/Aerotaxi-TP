package Aerotaxi.Controllers;


import com.jfoenix.controls.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;


public class MainScreenController {


    @FXML
    private JFXButton flightButton;

    @FXML
    private JFXButton userInfoButton;

    @FXML
    private JFXDrawer flightDrawer;




    @FXML
    void orderFlightScreen(ActionEvent event) throws IOException {

        AnchorPane pane = FXMLLoader.load(getClass().getResource("/Resources/flightSearch.fxml"));
        flightDrawer.setSidePane(pane);

        if(flightDrawer.isOpened()){
            flightDrawer.close();

        }
        else
            flightDrawer.open();

    }

    @FXML
    void toFlightManagement(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/Resources/flightManagement.fxml"));
        flightDrawer.setSidePane(pane);

        if(flightDrawer.isOpened()){
            flightDrawer.close();
        }
        else
        {
            flightDrawer.open();
        }

    }


}





