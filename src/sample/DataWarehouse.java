package sample;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.lang.reflect.Type;

public class DataWarehouse {
    public static List<Clients> clientList = new LinkedList<>();;

    static {

        try{
        /// Reader para ubicar el path de mi archivo JSON.
        Reader reader = Files.newBufferedReader(Paths.get("src/sample/Clients.JSON"));
        /// Esto es para obtener el tipo exacto de la Linkedlist ya que puede ser generico.
        Type userListType = new TypeToken<LinkedList<Clients>>(){}.getType();

        clientList = new Gson().fromJson(reader,userListType);
        }
        catch (Exception ex) {
           ex.printStackTrace();
       }
    }


    /// metodo para escribir el Json luego de creado un nuevo usuario o modificacion de uno.
    public static void fromUserlistTojson(){
        try{

            FileWriter file = new FileWriter("src/sample/Clients.JSON");

            String element = new Gson().toJson(
                    clientList,
                    new TypeToken<LinkedList<Clients>>() {}.getType());

            file.write(element);

            file.flush();
            file.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }

    }



}
