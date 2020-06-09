package sample;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import java.io.IOException;

public class Controller {


    @FXML
    private JFXTextField usernameInput;

    @FXML
    private JFXPasswordField passwordInput;

    @FXML
    private JFXButton loginButton;

    @FXML
    private JFXButton registerButton;

    @FXML
    private Label errorLabel;

    public void toRegisterScreen(ActionEvent actionEvent) throws IOException{
        Parent root3 = FXMLLoader.load(getClass().getResource("registerScreen.fxml"));
        Scene scene2 = new Scene(root3, 800, 600);
        Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        window.setScene(scene2);
        window.setTitle("Registrarse");
        window.show();
    }

    public void verifyLogin(){


       if(DataWarehouse.validateUser(usernameInput.getText(), passwordInput.getText())){
           System.out.println("Pasar a la ventana de la app principal");
       }
       else{
           errorLabel.setText("Nombre de usuario o contraseña incorrectos");
       }
    }
}
