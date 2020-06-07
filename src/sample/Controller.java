package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import java.io.IOException;

public class Controller {

    public void toRegisterScreen(ActionEvent actionEvent) throws IOException{
        Parent root3 = FXMLLoader.load(getClass().getResource("registerScreen.fxml"));
        Scene scene2 = new Scene(root3);
        Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        window.setScene(scene2);
        window.setTitle("Registrarse");
        window.show();
    }

    public void changeScreen(ActionEvent actionEvent) {
    }
}
