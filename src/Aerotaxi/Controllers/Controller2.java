package Aerotaxi.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller2 {

    //TODO: mejorar este metodo para mas claridad .
    public void changeScreen(ActionEvent actionEvent) throws IOException {
        Parent root2 = FXMLLoader.load(getClass().getResource("/Resoruces/sample.fxml"));
        Scene scene2 = new Scene(root2);
        Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        window.setScene(scene2);
        window.show();
    }
}
