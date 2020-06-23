package Aerotaxi.Controllers;

import Aerotaxi.Core.Airplanes.*;
import Aerotaxi.Core.City;
import Aerotaxi.Core.DataWarehouse;
import Aerotaxi.Core.FlightTicket;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class ChoosePlaneController implements Initializable {


    @FXML
    private AnchorPane daddyPane;

    @FXML
    private JFXButton confirmButton;

    @FXML
    private JFXTreeTableView<TableItem> treeView;

    private ObservableList<TableItem> dataTable;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        treeView.setPlaceholder(new Label("No tenemos ningun avion de esa capacidad disponible en esta fecha"));

        loadData(StaticController.passengers,StaticController.date,StaticController.origin,StaticController.destination);

        JFXTreeTableColumn<TableItem, String> colClase = new JFXTreeTableColumn<TableItem, String>("Clase");
        colClase.setPrefWidth(100);
        colClase.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<TableItem, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<TableItem, String> tableItemStringCellDataFeatures) {
                return tableItemStringCellDataFeatures.getValue().getValue().clase;
            }
        });

        JFXTreeTableColumn<TableItem, Integer> colPrecio = new JFXTreeTableColumn<TableItem, Integer>("Precio");
        colPrecio.setPrefWidth(100);
        colPrecio.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<TableItem, Integer>, ObservableValue<Integer>>() {
            @Override
            public ObservableValue<Integer> call(TreeTableColumn.CellDataFeatures<TableItem, Integer> tableItemIntegerCellDataFeatures) {
                return tableItemIntegerCellDataFeatures.getValue().getValue().precio.asObject();
            }
        });

        JFXTreeTableColumn<TableItem, String> colCatering = new JFXTreeTableColumn<TableItem, String>("Catering");
        colCatering.setPrefWidth(100);
        colCatering.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<TableItem, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<TableItem, String> tableItemStringCellDataFeatures) {
                return tableItemStringCellDataFeatures.getValue().getValue().catering;
            }
        });
        JFXTreeTableColumn<TableItem, String> colWifi = new JFXTreeTableColumn<TableItem, String>("Wifi");
        colWifi.setPrefWidth(100);
        colWifi.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<TableItem, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<TableItem, String> tableItemStringCellDataFeatures) {
                return tableItemStringCellDataFeatures.getValue().getValue().wifi;
            }
        });


        final TreeItem treeRoot = new RecursiveTreeItem<TableItem>(dataTable, RecursiveTreeObject::getChildren);
        treeView.getColumns().addAll(colClase,colCatering,colWifi,colPrecio);
        treeView.setRoot(treeRoot);
        treeView.setShowRoot(false);

        treeView.getSelectionModel().selectFirst();

        if(treeView.getCurrentItemsCount() == 0){
            confirmButton.setDisable(true);
        }
    }

    public void loadData(int passengers,LocalDate date,City origin,City destination){

        List<Aircraft> availablePlanes = DataWarehouse.getAvailablePlanes(date,passengers);
        dataTable = FXCollections.observableArrayList();

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


    }

    class TableItem extends RecursiveTreeObject<TableItem> {

        private StringProperty clase;
        private IntegerProperty precio;
        private StringProperty catering;
        private StringProperty wifi;


        private FlightTicket flight;

        TableItem(FlightTicket flight){
            this.flight = flight;
            clase = new SimpleStringProperty(flight.getPlane().getType());
            precio = new SimpleIntegerProperty(flight.getCost());
            setCatering();
            setWifi();
        }

        public void setCatering(){
            if((flight.getPlane() instanceof Silver)){
                catering = new SimpleStringProperty(((Silver) flight.getPlane()).catering());
            }else if(flight.getPlane() instanceof Gold){
                catering = new SimpleStringProperty(((Gold) flight.getPlane()).catering());
            }else{
                catering = new SimpleStringProperty("Nop");
            }
        }
        public void setWifi(){
            if((flight.getPlane() instanceof Gold)){
                wifi =  new SimpleStringProperty(((Gold) flight.getPlane()).hasWifi() ? "Si" : "No");
            } else{
                wifi = new SimpleStringProperty("Nop");
            }
        }

        public String getClase() {
            return clase.get();
        }

        public StringProperty claseProperty() {
            return clase;
        }

        public int getPrecio() {
            return precio.get();
        }

        public IntegerProperty precioProperty() {
            return precio;
        }

        public String getCatering() {
            return catering.get();
        }

        public StringProperty cateringProperty() {
            return catering;
        }

        public String getWifi() {
            return wifi.get();
        }

        public StringProperty wifiProperty() {
            return wifi;
        }

        public FlightTicket getFlight() {
            return flight;
        }
    }

    @FXML
    void selectedAirplane(ActionEvent event) throws IOException {
        StaticController.userSelectedFlight = treeView.getSelectionModel().getSelectedItem().getValue().flight;

        AnchorPane confirmFlight = FXMLLoader.load(getClass().getResource("/Resources/confirmFlight.fxml"));
        daddyPane.getChildren().setAll(confirmFlight);

    }




}
