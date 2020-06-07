package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class RegisterScreenController {

    ///TODO : MEJORAR ESTE CODIGO A ALMO MAS LEGIBLE
    public void gotoPrincipalScreen(ActionEvent actionEvent) throws IOException {

        Parent root2 = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Scene scene2 = new Scene(root2);
        Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        window.setScene(scene2);
        window.show();
    }
}
