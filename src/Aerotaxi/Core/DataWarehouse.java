package Aerotaxi.Core;

import Aerotaxi.Airplanes.Aircraft;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static Aerotaxi.Utilities.test.fromJsonToList;


public class DataWarehouse {

    private static List<User> userList = fromJsonToList("src/JsonFiles/Users.json");
    private static List<Aircraft> aircraftList = fromJsonToList("src/JsonFiles/Airplanes.json");
    private static List<FlightTicket> flightsList = fromJsonToList("src/JsonFiles/FlightTickets.json");

    private static User loggedUser;

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
