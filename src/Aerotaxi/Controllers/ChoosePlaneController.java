package Aerotaxi.Controllers;

import Aerotaxi.Core.Airplanes.*;
import Aerotaxi.Core.City;
import Aerotaxi.Core.DataWarehouse;
import Aerotaxi.Core.FlightTicket;
import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class ChoosePlaneController implements Initializable {

    @FXML
    private JFXButton confirmFlight;

    @FXML
    private TableView<TableItem> tableView;

    @FXML
    private TableColumn<TableItem, String> colAvion;




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }



    public void loadData(int passengers, LocalDate date, City origin, City destination){
        List<Aircraft> availablePlanes = DataWarehouse.getAvailablePlanes(date,passengers);
        ObservableList<TableItem> dataTable = FXCollections.observableArrayList();

        colAvion.setCellValueFactory(new PropertyValueFactory<>("clase"));

        for (Aircraft e: availablePlanes
             ) {
            dataTable.add(new TableItem(new FlightTicket(
                    DataWarehouse.getLoggedUser(),
                    e,
                    date,
                    passengers,
                    origin,
                    destination
            )));
        }


        //tableView.getColumns().add(colAvion);

        tableView.setItems(dataTable);



    }

    class TableItem {

        private String clase;
        private int precio;
        private String catering;
        private String wifi;


        private FlightTicket flight;

        TableItem(FlightTicket flight){
            this.flight = flight;
            clase = flight.getPlane().getType();
            precio = flight.getCost();
            setCatering();
            setWifi();
        }

        public void setCatering(){
            if((flight.getPlane() instanceof Silver)){
                catering = ((Silver) flight.getPlane()).catering();
            }else if(flight.getPlane() instanceof Gold){
                catering =((Gold) flight.getPlane()).catering();
            }else{
                catering = "nop";
            }
        }
        public void setWifi(){
            if((flight.getPlane() instanceof Gold)){
                wifi = ((Gold) flight.getPlane()).hasWifi() ? "Si" : "No";
            } else{
                wifi = "nop";
            }
        }

        public String getClase() {
            return clase;
        }

        public void setClase(String clase) {
            this.clase = clase;
        }

        public int getPrecio() {
            return precio;
        }

        public void setPrecio(int precio) {
            this.precio = precio;
        }

        public String getCatering() {
            return catering;
        }


        public String getWifi() {
            return wifi;
        }


        public FlightTicket getFlight() {
            return flight;
        }

        public void setFlight(FlightTicket flight) {
            this.flight = flight;
        }
    }


    @FXML
    void test(MouseEvent event) {
       // Selector.getItems().addAll(DataWarehouse.getAvailablePlanes())
    }




}
