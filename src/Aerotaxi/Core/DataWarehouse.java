package Aerotaxi.Core;

import Aerotaxi.Airplanes.Aircraft;
import Aerotaxi.Utilities.CustomDeserializer;
import Aerotaxi.Utilities.CustomSerializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.lang.reflect.Type;
import java.util.stream.Collectors;

public class DataWarehouse {
    private static List<User> userList = new ArrayList<>();
    private static List<Aircraft> aircraftList = new ArrayList<>();
    //private static List<> flights = new ArrayList<>();
    private static User loggedUser;


    static { //Inicializador estatico

        ///TODO : POR MOTIVOS DE PRUEBA
        try{
            userList.add(new User("franco", "12345", "franco", "b", 69, "69420420"));
            userList.add(new User("facu69", "12345", "facu", "b", 69, "69699996"));
            userList.add(new Admin("FACU-ADMIN","BOCA-YO-TE-AMO"));

            GsonBuilder gb = new GsonBuilder();
            List<User> al = new ArrayList<User>();
            List<Aircraft> air = new ArrayList<Aircraft>();

            // User - admin
            gb.registerTypeAdapter(al.getClass(), new CustomDeserializer<User>());
            gb.registerTypeAdapter(al.getClass(), new CustomSerializer<User>());

            // aviones
            gb.registerTypeAdapter(air.getClass(), new CustomDeserializer<Aircraft>());
            gb.registerTypeAdapter(air.getClass(), new CustomSerializer<Aircraft>());
            Gson gson = gb.create();

            String json = gson.toJson(userList);
            FileWriter file = null;

            file = new FileWriter("src/JsonFiles/Clients.json");
            file.write(json);

            file.flush();
            file.close();
            ////////////////////////////////////////////////////////////////////////////////////////////////////////

            ////////////////////////////////////////////////////////////////////////////////////////////////////////
              /// Carga de Json a listas

            List<User> provisoria = gson.fromJson(json, al.getClass());




        }


        catch (Exception ex) {
           ex.printStackTrace();
       }
    }


    /// metodo para escribir el Json luego de creado un nuevo usuario o modificacion de uno.
    public static void fromUserlistTojson(){
        try{
            //userList.add(new User("franco", "12345", "franco", "b", 69, "69420420"));
            //userList.add(new User("facu69", "12345", "facu", "b", 69, "69699996"));
            //userList.add(new Admin("FACU-ADMIN","BOCA-YO-TE-AMO"));

            GsonBuilder gb = new GsonBuilder();
            List<User> al = new ArrayList<User>();
            List<Aircraft> air = new ArrayList<Aircraft>();

            // User - admin
            gb.registerTypeAdapter(al.getClass(), new CustomDeserializer<User>());
            gb.registerTypeAdapter(al.getClass(), new CustomSerializer<User>());

            // aviones
            gb.registerTypeAdapter(air.getClass(), new CustomDeserializer<Aircraft>());
            gb.registerTypeAdapter(air.getClass(), new CustomSerializer<Aircraft>());
            Gson gson = gb.create();

            String json = gson.toJson(userList);
            FileWriter file = null;

            file = new FileWriter("src/JsonFiles/Clients.json");
            file.write(json);

            file.flush();
            file.close();

        }
        catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public static boolean validateUser( String username, String password){ //metodo para validar el login de un usuario

        boolean found;
        try {
            loggedUser = userList.stream().filter(c -> c.getUsername().equals(username) && c.getPassword().equals(password))
                    .collect(Collectors.toList()).get(0); //Busca un user en la lista con respecto a lo que indico el usuario
                    found = true;
        }catch (Exception e){
            found = false;
        }
        return found;
    }


    public static boolean isUsernameTaken(String username){ //para registrarse

        return userList.stream().anyMatch(c -> username.equals(c.getUsername()));
    }


    public static void addAndLogInUser(User user){
        userList.add(user);
        loggedUser = user;
    }



}
