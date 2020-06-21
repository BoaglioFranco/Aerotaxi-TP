package Aerotaxi.Controllers;

import Aerotaxi.Core.DataWarehouse;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;


public class confirmFlightController implements Initializable {

    @FXML
    private JFXButton confirmButton;

    @FXML
    private Label confirmationLabel;

    @FXML
    private Label originLabel;

    @FXML
    private Label destinationLabel;

    @FXML
    private Label dateLabel;

    @FXML
    private Label passengersLabel;

    @FXML
    private Label classLabel;

    @FXML
    private Label costLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        originLabel.setText(StaticController.userSelectedFlight.getOrigin().getName());
        destinationLabel.setText(StaticController.userSelectedFlight.getDestination().getName());
        dateLabel.setText(StaticController.userSelectedFlight.getDate().toString());
        passengersLabel.setText(String.valueOf(StaticController.userSelectedFlight.getPassengers()));
        classLabel.setText(StaticController.userSelectedFlight.getPlane().getType());
        costLabel.setText(String.valueOf(StaticController.userSelectedFlight.getCost()));
    }

    @FXML
    void ConfirmOrder(ActionEvent event) {
        confirmButton.setDisable(true);
        confirmationLabel.setText("Su vuelo ha sido registrado, gracias por elegirnos.");
        DataWarehouse.addFlight(StaticController.userSelectedFlight);
    }

}