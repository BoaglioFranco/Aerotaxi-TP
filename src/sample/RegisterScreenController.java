package sample;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RequiredFieldValidator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RegisterScreenController implements Initializable {

    @FXML
    private JFXButton confirmButton;

    @FXML
    private JFXTextField userField;

    @FXML
    private JFXTextField nameField;

    @FXML
    private JFXTextField surnameField;

    @FXML
    private JFXTextField ageField;

    @FXML
    private JFXTextField passField;

    @FXML
    private JFXTextField dniField;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) { //codigo que se ejecuta cuando se inicializa la scene
        RequiredFieldValidator validator = new RequiredFieldValidator();
        validator.setMessage("Campo Obligatorio");

        nameField.getValidators().add(validator);
        nameField.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if(!newValue) nameField.validate();});

        surnameField.getValidators().add(validator);
        surnameField.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if(!newValue) surnameField.validate();});

        ageField.getValidators().add(validator);
        ageField.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if(!newValue) ageField.validate();});

        passField.getValidators().add(validator);
        passField.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if(!newValue) passField.validate();});

        dniField.getValidators().add(validator);
        dniField.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if(!newValue) dniField.validate();});
    }

    @FXML
    void registerUser(ActionEvent event) {
        System.out.println("register");
    }


    ///TODO : MEJORAR ESTE CODIGO A ALMO MAS LEGIBLE
    public void gotoPrincipalScreen(ActionEvent actionEvent) throws IOException {

        Parent root2 = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Scene scene2 = new Scene(root2);
        Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        window.setScene(scene2);
        window.setTitle("Aerotaxi");
        window.show();
    }
}
