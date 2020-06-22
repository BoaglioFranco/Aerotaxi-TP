package Aerotaxi.Controllers;

import Aerotaxi.Core.DataWarehouse;
import Aerotaxi.Core.FlightTicket;
import Aerotaxi.Core.User;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

import java.net.URL;
import java.util.ResourceBundle;

public class AdminController implements Initializable {

    @FXML
    private TabPane tabPane;

    @FXML
    private Tab tabUser;

    @FXML
    private JFXTreeTableView<ItemUser> userTableView;

    @FXML
    private JFXTreeTableView<ItemFlights> flightsTableView;

    private ObservableList<ItemUser> obsUserList;

    private ObservableList<ItemFlights> obsFlightsList;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        JFXTreeTableColumn<ItemUser, String> colUsername = new JFXTreeTableColumn<ItemUser, String>("Username");
        JFXTreeTableColumn<ItemUser, String> colName = new JFXTreeTableColumn<ItemUser, String>("Nombre");
        JFXTreeTableColumn<ItemUser, String> colApellido = new JFXTreeTableColumn<ItemUser, String>("Apellido");
        JFXTreeTableColumn<ItemUser, String> colDni = new JFXTreeTableColumn<ItemUser, String>("Apellido");
        JFXTreeTableColumn<ItemUser, String> colBestCat = new JFXTreeTableColumn<ItemUser, String>("Mejor categoria utilizada");
        JFXTreeTableColumn<ItemUser, Integer> colTotal = new JFXTreeTableColumn<ItemUser, Integer>("Total gastado");

    }

    void loadTableItems(){
        obsUserList = FXCollections.observableArrayList();
        obsFlightsList = FXCollections.observableArrayList();

        for (FlightTicket e: DataWarehouse.getFlightList())
            obsFlightsList.add(new ItemFlights(e));


        for(User e : DataWarehouse.getUserList());




    }
}


class ItemUser extends RecursiveTreeObject<ItemUser> {

    private StringProperty username;
    private StringProperty name;
    private StringProperty lastname;
    private StringProperty dni;
    private StringProperty besCat;
    private IntegerProperty totalSpend;

    private User itemUser;

    ItemUser(User us){

        username = new SimpleStringProperty(us.getUsername());
        name = new SimpleStringProperty(us.getName());
        lastname = new SimpleStringProperty(us.getSurname());
        dni = new SimpleStringProperty(us.getDni());
        besCat = new SimpleStringProperty(us.getBestClass());
        totalSpend = new SimpleIntegerProperty(us.getTotalSpent());

        itemUser = us;

    }

    public String getUsername() {
        return username.get();
    }

    public StringProperty usernameProperty() {
        return username;
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public String getLastname() {
        return lastname.get();
    }

    public StringProperty lastnameProperty() {
        return lastname;
    }

    public String getDni() {
        return dni.get();
    }

    public StringProperty dniProperty() {
        return dni;
    }

    public String getBesCat() {
        return besCat.get();
    }

    public StringProperty besCatProperty() {
        return besCat;
    }

    public int getTotalSpend() {
        return totalSpend.get();
    }

    public IntegerProperty totalSpendProperty() {
        return totalSpend;
    }

    public User getItemUser() {
        return itemUser;
    }
}

class ItemFlights extends RecursiveTreeObject<ItemFlights> {

    private StringProperty destino;
    private StringProperty origen;
    private StringProperty date;

    private FlightTicket ticket;

    ItemFlights(FlightTicket A){
        destino = new SimpleStringProperty(A.getDestination().getName());
        origen = new SimpleStringProperty(A.getOrigin().getName());
        date =  new SimpleStringProperty(A.getDate().toString());
        ticket = A;
    }

    public String getDestino() {
        return destino.get();
    }

    public StringProperty destinoProperty() {
        return destino;
    }

    public String getOrigen() {
        return origen.get();
    }

    public StringProperty origenProperty() {
        return origen;
    }

    public String getDate() {
        return date.get();
    }

    public StringProperty dateProperty() {
        return date;
    }

    public FlightTicket getTicket() {
        return ticket;
    }
}

