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
import javafx.scene.control.Label;
import javafx.stage.Stage;

import javax.xml.crypto.Data;
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

    @FXML
    private Label errorLabel;

    User us = new User();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) { //codigo que se ejecuta cuando se inicializa la scene
        RequiredFieldValidator validator = new RequiredFieldValidator();
        validator.setMessage("Campo Obligatorio");

        nameField.getValidators().add(validator);
        nameField.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if(!newValue) nameField.validate(); });

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
        User us = new User();
        if(DataWarehouse.isUsernameTaken(userField.getText())){
            errorLabel.setText("El nombre de usuario no esta disponible");
        } else{
            if(validateInput()){
                System.out.println("Registracion validarda");
                DataWarehouse.addAndLogInUser(us);

                //TODO: Cambiar de ventana
            }
        }
    }


    ///TODO : MEJORAR ESTE CODIGO A ALMO MAS LEGIBLE
    public void gotoPrincipalScreen(ActionEvent actionEvent) throws IOException { //codigo para cambiar la escena a la de login

        Parent root2 = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Scene scene2 = new Scene(root2);
        Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        window.setScene(scene2);
        window.setTitle("Aerotaxi");
        window.show();
    }



    private boolean validateInput(){ //valida el input del usuario
        boolean result = false;


        if(!us.setUsername(userField.getText())){
            errorLabel.setText("Nombre de usuario invalido");
        } else if(!us.setPassword(passField.getText())){ //Valido 1 por uno los campos que ingreso el usuario
            errorLabel.setText("Contraseña invalida");   //no se si es el codigo mas lindo que hay
        } else if(!us.setName(nameField.getText())){
            errorLabel.setText("Nombre Invalido");
        } else if(!us.setSurname(surnameField.getText())){
            errorLabel.setText("Apellido Invalido");
        } else if(!us.setDni(dniField.getText())){
            errorLabel.setText("Dni invalido");
        } else{
            try{
                Integer.parseInt(ageField.getText());
            } catch (Exception e){
                errorLabel.setText("Edad invalida");
                return false;
            }
            result = true;
        }
        return  result;
    }
}