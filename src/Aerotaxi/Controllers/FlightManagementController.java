package Aerotaxi.Controllers;

import Aerotaxi.Core.DataWarehouse;
import Aerotaxi.Core.FlightTicket;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class FlightManagementController implements Initializable {

    @FXML
    private JFXTreeTableView<ItemsTableView> treeTable;

    private  ObservableList<ItemsTableView> obs;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        loadData();

        JFXTreeTableColumn<ItemsTableView, String> colDestino = new JFXTreeTableColumn<ItemsTableView, String>("Destino");
        colDestino.setPrefWidth(100);
        colDestino.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<ItemsTableView, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<ItemsTableView, String> itemsTableViewStringCellDataFeatures) {
                return itemsTableViewStringCellDataFeatures.getValue().getValue().destino;
            }
        });
        JFXTreeTableColumn<ItemsTableView, String> colFlightday = new JFXTreeTableColumn<ItemsTableView, String>("Dia de vuelo");
        colFlightday.setPrefWidth(150);
        colFlightday.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<ItemsTableView, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<ItemsTableView, String> itemsTableViewStringCellDataFeatures) {
                return itemsTableViewStringCellDataFeatures.getValue().getValue().date;
            }
        });

        JFXTreeTableColumn<ItemsTableView, String> colOrigen = new JFXTreeTableColumn<ItemsTableView, String>("Origen");
        colOrigen.setPrefWidth(100);
        colOrigen.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<ItemsTableView, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<ItemsTableView, String> itemsTableViewStringCellDataFeatures) {
                return itemsTableViewStringCellDataFeatures.getValue().getValue().origen;
            }
        });

        final TreeItem treeRoot = new RecursiveTreeItem<ItemsTableView>(obs, RecursiveTreeObject::getChildren);
        treeTable.getColumns().addAll(colDestino,colOrigen,colFlightday);
        treeTable.setRoot(treeRoot);
        treeTable.setShowRoot(false);
    }


    public void loadData(){

        obs = FXCollections.observableArrayList();

        for (FlightTicket e: DataWarehouse.getUserFlights()
        ) {
            obs.add(new ItemsTableView(e));
        }

    }

    @FXML
    void cancelFlight(ActionEvent event) {

    }

    class ItemsTableView extends RecursiveTreeObject<ItemsTableView> {

        private StringProperty destino;
        private StringProperty origen;
        private StringProperty date;

        private FlightTicket ticket;

        ItemsTableView(FlightTicket A){
            destino = new SimpleStringProperty(A.getDestination().getName());
            origen = new SimpleStringProperty(A.getOrigin().getName());
            date =  new SimpleStringProperty(A.getDate().toString());
            ticket = A;
        }



    }

}
