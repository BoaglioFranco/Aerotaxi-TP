package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import java.io.IOException;

public class Controller {

    /// cambio de screen.
    public void changeScreen(ActionEvent actionEvent) throws IOException {
        Parent root2 = FXMLLoader.load(getClass().getResource("sample2.fxml"));
        Scene scene2 = new Scene(root2);
        Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        window.setScene(scene2);
        window.show();
    }

    public void toRegisterScreen(ActionEvent actionEvent) throws IOException{
        Parent root3 = FXMLLoader.load(getClass().getResource("registerScreen.fxml"));
        Scene scene2 = new Scene(root3);
        Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        window.setScene(scene2);
        window.setTitle("DOU?");
        window.show();
    }

}
