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
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class DataWarehouse {
    private static List<User> clientList = new LinkedList<>();
    private static User loggedUser;


    static { //Inicializador estatico

        try{
        /// Reader para ubicar el path de mi archivo JSON.
        Reader reader = Files.newBufferedReader(Paths.get("src/sample/Clients.JSON"));
        /// Esto es para obtener el tipo exacto de la Linkedlist ya que puede ser generico.
        Type userListType = new TypeToken<LinkedList<User>>(){}.getType();

        //clientList = new Gson().fromJson(reader,userListType);
            clientList.add(new User("franco", "12345", "franco", "b", 69, "69420420"));
            clientList.add(new User("facu69", "12345", "facu", "b", 69, "69699996"));
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
                    new TypeToken<LinkedList<User>>() {}.getType());

            file.write(element);

            file.flush();
            file.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }

    }

    public static boolean validateUser( String username, String password){ //metodo para validar el login de un usuario

        boolean found;
        try {
            loggedUser = clientList.stream().filter(c -> c.getUsername().equals(username) && c.getPassword().equals(password))
                    .collect(Collectors.toList()).get(0); //Busca un user en la lista con respecto a lo que indico el usuario
                    found = true;
        }catch (Exception e){
            found = false;
        }
        return found;
    }


    public static boolean isUsernameTaken(String username){ //para registrarse

        return clientList.stream().anyMatch(c -> username.equals(c.getUsername()));
    }



}
