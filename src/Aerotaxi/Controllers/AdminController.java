package Aerotaxi.Controllers;

import Aerotaxi.Core.DataWarehouse;
import Aerotaxi.Core.FlightTicket;
import Aerotaxi.Core.User;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Predicate;

public class AdminController implements Initializable {



    @FXML
    private JFXTreeTableView<ItemUser> userTableView;

    @FXML
    private JFXTreeTableView<ItemFlights> flightsTableView;

    @FXML
    private DatePicker datePicker;

    @FXML
    private JFXTextField textField;

    private ObservableList<ItemUser> obsUserList;

    private ObservableList<ItemFlights> obsFlightsList;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        loadTableItems(); // Carga de las obsList con los datos.

        flightsTableView.setPlaceholder(new Label ("No hay ningun vuelo cargado al sistema"));
        userTableView.setPlaceholder(new Label ("No hay ningun usuario cargado al sistema"));
        //// Columnas Usuarios
        JFXTreeTableColumn<ItemUser, String> colUsername = new JFXTreeTableColumn<ItemUser, String>("Username");
        colUsername.setPrefWidth(150);
        colUsername.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<ItemUser, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<ItemUser, String> itemUserStringCellDataFeatures) {
                return itemUserStringCellDataFeatures.getValue().getValue().username;
            }
        });

        JFXTreeTableColumn<ItemUser, String> colName = new JFXTreeTableColumn<ItemUser, String>("Nombre");
        colName.setPrefWidth(120);
        colName.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<ItemUser, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<ItemUser, String> itemUserStringCellDataFeatures) {
                return itemUserStringCellDataFeatures.getValue().getValue().name;
            }
        });

        JFXTreeTableColumn<ItemUser, String> colLastname = new JFXTreeTableColumn<ItemUser, String>("Apellido");
        colLastname.setPrefWidth(120);
        colLastname.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<ItemUser, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<ItemUser, String> itemUserStringCellDataFeatures) {
                return itemUserStringCellDataFeatures.getValue().getValue().lastname;
            }
        });

        JFXTreeTableColumn<ItemUser, String> colDni = new JFXTreeTableColumn<ItemUser, String>("DNI");
        colDni.setPrefWidth(100);
        colDni.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<ItemUser, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<ItemUser, String> itemUserStringCellDataFeatures) {
                return itemUserStringCellDataFeatures.getValue().getValue().dni;
            }
        });

        JFXTreeTableColumn<ItemUser, String> colBestCat = new JFXTreeTableColumn<ItemUser, String>("Mejor categoria utilizada");
        colBestCat.setPrefWidth(150);
        colBestCat.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<ItemUser, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<ItemUser, String> itemUserStringCellDataFeatures) {
                return itemUserStringCellDataFeatures.getValue().getValue().besCat;
            }
        });

        JFXTreeTableColumn<ItemUser, Integer> colTotal = new JFXTreeTableColumn<ItemUser, Integer>("Total gastado");
        colTotal.setPrefWidth(145);
        colTotal.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<ItemUser, Integer>, ObservableValue<Integer>>() {
            @Override
            public ObservableValue<Integer> call(TreeTableColumn.CellDataFeatures<ItemUser, Integer> itemUserIntegerCellDataFeatures) {
                return itemUserIntegerCellDataFeatures.getValue().getValue().totalSpend.asObject();
            }
        });

        ///// Columnas vuelos.

        JFXTreeTableColumn<ItemFlights, String> colDate = new JFXTreeTableColumn<ItemFlights, String>("Fecha");
        colDate.setPrefWidth(100);
        colDate.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<ItemFlights, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<ItemFlights, String> itemFlightsStringCellDataFeatures) {
                return itemFlightsStringCellDataFeatures.getValue().getValue().date;
            }
        });

        JFXTreeTableColumn<ItemFlights, String> colDestino = new JFXTreeTableColumn<ItemFlights, String>("Destino");
        colDestino.setPrefWidth(140);
        colDestino.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<ItemFlights, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<ItemFlights, String> itemFlightsStringCellDataFeatures) {
                return itemFlightsStringCellDataFeatures.getValue().getValue().destino;
            }
        });

        JFXTreeTableColumn<ItemFlights, String> colOrigin = new JFXTreeTableColumn<ItemFlights, String>("Origin");
        colOrigin.setPrefWidth(140);
        colOrigin.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<ItemFlights, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<ItemFlights, String> itemFlightsStringCellDataFeatures) {
                return itemFlightsStringCellDataFeatures.getValue().getValue().origen;
            }
        });

        JFXTreeTableColumn<ItemFlights, String> colUser = new JFXTreeTableColumn<ItemFlights, String>("User");
        colUser.setPrefWidth(150);
        colUser.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<ItemFlights, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<ItemFlights, String> itemFlightsStringCellDataFeatures) {
                return itemFlightsStringCellDataFeatures.getValue().getValue().user;
            }
        });

        JFXTreeTableColumn<ItemFlights, Integer> colPass = new JFXTreeTableColumn<ItemFlights,Integer>("Pasajeros");
        colPass.setPrefWidth(100);
        colPass.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<ItemFlights, Integer>, ObservableValue<Integer>>() {
            @Override
            public ObservableValue<Integer> call(TreeTableColumn.CellDataFeatures<ItemFlights, Integer> itemFlightsIntegerCellDataFeatures) {
                return itemFlightsIntegerCellDataFeatures.getValue().getValue().passagers.asObject();
            }
        });


        JFXTreeTableColumn<ItemFlights, Integer> colflightcost = new JFXTreeTableColumn<ItemFlights,Integer>("Costo del vuelo");
        colflightcost.setPrefWidth(150);
        colflightcost.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<ItemFlights, Integer>, ObservableValue<Integer>>() {
            @Override
            public ObservableValue<Integer> call(TreeTableColumn.CellDataFeatures<ItemFlights, Integer> itemFlightsIntegerCellDataFeatures) {
                return itemFlightsIntegerCellDataFeatures.getValue().getValue().flightCost.asObject();
            }
        });



        final TreeItem userTreeRoot = new RecursiveTreeItem<ItemUser>(obsUserList, RecursiveTreeObject::getChildren);
        final TreeItem flightsTreeRoot = new RecursiveTreeItem<ItemFlights>(obsFlightsList, RecursiveTreeObject::getChildren);

        userTableView.getColumns().addAll(colUsername,colName,colLastname,colDni,colBestCat,colTotal);
        flightsTableView.getColumns().addAll(colDate,colDestino,colOrigin,colUser,colPass,colflightcost);

        userTableView.setShowRoot(false);
        flightsTableView.setShowRoot(false);

        userTableView.setRoot(userTreeRoot);
        flightsTableView.setRoot(flightsTreeRoot);

        textField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                flightsTableView.setPredicate(new Predicate<TreeItem<ItemFlights>>() {
                    @Override
                    public boolean test(TreeItem<ItemFlights> itemFlightsTreeItem) {
                        boolean flag = itemFlightsTreeItem.getValue().date.getValue().contains(t1);
                        return flag;
                    }
                });
            }
        });

    }

    void loadTableItems(){  /// Cargamos las lisatas obs con todos los datos de nuestros items.
        obsUserList = FXCollections.observableArrayList();
        obsFlightsList = FXCollections.observableArrayList();

        for (FlightTicket e: DataWarehouse.getFlightList())
            obsFlightsList.add(new ItemFlights(e));

        for (User a: DataWarehouse.getUserList())
            obsUserList.add(new ItemUser(a));

    }

    @FXML
    public void searchDateFlights(ActionEvent actionEvent) {
        textField.setText(datePicker.getValue().toString());
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

     /// Clases contenedoras para poder mostrarla en la tree table view , modelan a las clases user y flight ticket
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

    }


    class ItemFlights extends RecursiveTreeObject<ItemFlights> {

        private StringProperty destino;
        private StringProperty origen;
        private StringProperty date;
        private StringProperty user;
        private IntegerProperty flightCost;
        private IntegerProperty passagers;


        private FlightTicket ticket;

        ItemFlights(FlightTicket A){
            date =  new SimpleStringProperty(A.getDate().toString());
            user = new SimpleStringProperty(A.getClient().getUsername());
            destino = new SimpleStringProperty(A.getDestination().getName());
            origen = new SimpleStringProperty(A.getOrigin().getName());
            flightCost = new SimpleIntegerProperty(A.getCost());
            passagers = new SimpleIntegerProperty(A.getPassengers());

            ticket = A;
        }

    }

}




