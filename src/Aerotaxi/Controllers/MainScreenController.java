package Aerotaxi.Controllers;


import Aerotaxi.Core.DataWarehouse;
import com.jfoenix.controls.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class MainScreenController implements Initializable {


    @FXML
    private Label welcomeLabel;

    @FXML
    private JFXButton flightButton;

    @FXML
    private JFXButton userInfoButton;

    @FXML
    private JFXDrawer flightDrawer;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        welcomeLabel.setText("Bienvenido, " + DataWarehouse.getLoggedUser().getName());
    }

    @FXML
    void logout(ActionEvent event) throws IOException {
        DataWarehouse.logout();
        Parent root = FXMLLoader.load(getClass().getResource("/Resources/login.fxml"));
        Scene scene = new Scene(root, 800, 600);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.setTitle("Aerotaxi - Fly with us");
        window.show();
    }


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





