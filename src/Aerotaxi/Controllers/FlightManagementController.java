package Aerotaxi.Controllers;

import Aerotaxi.Core.DataWarehouse;
import Aerotaxi.Core.FlightTicket;
import com.jfoenix.controls.JFXButton;
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
import javafx.scene.control.*;
import javafx.util.Callback;
import java.net.URL;
import java.util.ResourceBundle;

public class FlightManagementController implements Initializable {

    @FXML
    private JFXTreeTableView<ItemsTableView> treeTable;

    @FXML
    private JFXButton cancelButton;

    private  ObservableList<ItemsTableView> obs;

    static int cantItems;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        loadData();

        treeTable.setPlaceholder(new Label ("No tiene ningun vuelo pendiente"));

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

        cantItems = treeTable.getCurrentItemsCount();

        tableElementsValidation();


    }


    public void loadData(){

        obs = FXCollections.observableArrayList();

        for (FlightTicket e: DataWarehouse.getUserFlights())
            if(!e.isDone()) {
            obs.add(new ItemsTableView(e));
        }
    }

    @FXML
    void cancelFlight(ActionEvent event) {


        if(AlertController.display("Cancelar vuelo","Esta seguro que desea cancelar su vuelo?", 300,200,
                "Cancelar vuelo", "Cerra ventana")){

            TreeItem c = (TreeItem)treeTable.getSelectionModel().getSelectedItem();
            DataWarehouse.cancelFlight(treeTable.getSelectionModel().getSelectedItem().getValue().ticket);
            c.getParent().getChildren().remove(c);
            cantItems -= 1;
            tableElementsValidation();

            }

    }

    void tableElementsValidation(){ /// metodo para evitar que el usuario presione el boton de cancelar vuelos sin vuelos existentes

        if(cantItems == 0)
            cancelButton.setDisable(true);
        else{
            treeTable.getSelectionModel().selectFirst();
            cancelButton.setDisable(false);
        }
    }

    class ItemsTableView extends RecursiveTreeObject<ItemsTableView> {

        private StringProperty destino;
        private StringProperty origen;
        private StringProperty date;

        private FlightTicket ticket;

        ItemsTableView(){}

        ItemsTableView(FlightTicket A){
            destino = new SimpleStringProperty(A.getDestination().getName());
            origen = new SimpleStringProperty(A.getOrigin().getName());
            date =  new SimpleStringProperty(A.getDate().toString());
            ticket = A;
        }



    }

}
