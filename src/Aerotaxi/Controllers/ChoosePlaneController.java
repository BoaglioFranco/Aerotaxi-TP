package Aerotaxi.Controllers;

import Aerotaxi.Core.Airplanes.Aircraft;
import Aerotaxi.Core.City;
import Aerotaxi.Core.DataWarehouse;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

import java.time.LocalDate;

public class ChoosePlaneController {

    @FXML
    private JFXListView<Aircraft> Selector;

    @FXML
    private JFXButton confirmFlight;

    @FXML
    void test(MouseEvent event) {
       // Selector.getItems().addAll(DataWarehouse.getAvailablePlanes())
    }

    public void myFunction(int passengers, City origin, City destination, LocalDate date){

    }

}
