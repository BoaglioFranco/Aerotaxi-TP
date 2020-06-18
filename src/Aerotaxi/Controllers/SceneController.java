package Aerotaxi.Controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

import java.net.URL;

public class SceneController {
    private Pane view;

    public Pane setScreen(String fileName){
       try {
           URL fileUrl = MainScreenController.class.getResource("/Resources/"+fileName+".fxml");
           System.out.println(fileUrl);
           if(fileUrl == null){
              throw new java.io.FileNotFoundException("FXML file no't found");
           }

           view = new FXMLLoader().load(fileUrl);

       }catch (Exception e){
           System.out.println("Error in Fxml loader");
       }

       return view;
    }
}
