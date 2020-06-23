package Aerotaxi.Controllers;

import Aerotaxi.Core.Airplanes.Aircraft;
import Aerotaxi.Core.City;
import Aerotaxi.Core.DataWarehouse;
import com.jfoenix.controls.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class SearchFlightController implements Initializable {


    @FXML
    private AnchorPane pane;

    @FXML
    private JFXTextField passengers;

    @FXML
    private JFXComboBox<String> origin;

    @FXML
    private JFXComboBox<String> destination;

    @FXML
    private JFXDatePicker departure;

    @FXML
    private Label errorLabel;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){

        origin.setPromptText(" Ciudad de origen");
        destination.setPromptText("  Ciudad destino");
        origin.getItems().addAll(City.BUENOS_AIRES.getName(),City.CORDOBA.getName(),City.MONTEVIDEO.getName(),City.SANTIAGO.getName());
        destination.getItems().addAll(City.BUENOS_AIRES.getName(),City.CORDOBA.getName(),City.MONTEVIDEO.getName(),City.SANTIAGO.getName());
    }

    public boolean validateUserTicket(){
        String validateInput = passengers.getText();
        boolean validPassengers = validateInput.matches("^\\d{1,2}$");
        boolean valid = false;

        if (validPassengers){

            int pass = Integer.parseInt(validateInput);

            if(origin.getValue() == null || destination.getValue() == null){

                if (origin.getValue() == null){ errorLabel.setText("Por favor ingrese una ciudad de origen."); }
                else if(destination.getValue() == null){ errorLabel.setText("Por favor ingrese una ciudad de destino.");}
                valid = false;
            }
            else if(origin.getValue().equals(destination.getValue())){
                errorLabel.setText("El origen y el destino son el mismo");
                valid = false;
            }
            else if(departure.getValue() == null || departure.getValue().isBefore(DataWarehouse.getCurrentDate())){
                errorLabel.setText("Fecha de salida incorrecta");
                valid = false;
            }else if(departure.getValue().isAfter(DataWarehouse.getCurrentDate().plusYears(2))){
                errorLabel.setText("No se puede pedir vuelos con mas de 2 años de anticipacion");
                valid = false;
            }else {
                errorLabel.setText("");
                valid = true;
            }

        }
        else { errorLabel.setText("Ingrese un numero de pasajeros"); }

        return valid;
    }

    public void searchFlight(ActionEvent actionEvent) throws IOException {


        boolean isValid = validateUserTicket();


        if(isValid){

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Resources/choosePlane.fxml"));
            int pass = Integer.parseInt(passengers.getText());

            StaticController.loadData(pass,departure.getValue(),City.of(origin.getValue()),City.of(destination.getValue()));

            AnchorPane pane2 = FXMLLoader.load(getClass().getResource("/Resources/choosePlane.fxml"));
            pane.getChildren().setAll(pane2);



        }

    }
}


