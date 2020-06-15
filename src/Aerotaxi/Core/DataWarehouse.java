package Aerotaxi.Core;

import Aerotaxi.Core.Airplanes.*;
import java.util.List;
import java.util.stream.Collectors;
import static Aerotaxi.Utilities.JsonSaveLoad.fromJsonToList;


public class DataWarehouse {

    private static final List<User> userList = fromJsonToList("src/JsonFiles/Users.json");
    private static final List<Aircraft> aircraftList = fromJsonToList("src/JsonFiles/Airplanes.json");
    private static final List<FlightTicket> flightsList = fromJsonToList("src/JsonFiles/FlightTickets.json");


    public void hardcodeo(){ /// For testing purposes
        /*  /// testeo aviones
        Gold avioncito = new Gold(215,370,30,790, PropulsionType.MOTOR_REACCION,2,true);
        Silver avioncito2 = new Silver(200,367,25,650,PropulsionType.MOTOR_PISTONES,2);
        aircraftList.add(avioncito);
        aircraftList.add(avioncito2);
        fromListToJson("src/JsonFiles/Airplanes.json",aircraftList);
         */
        /* /// testeo ticket
        LocalDate today = LocalDate.parse("2020-08-10");
        FlightTicket A = new FlightTicket(userList.get(0),aircraftList.get(0),today,City.BUENOS_AIRES,City.CORDOBA,2000);
        flightsList.add(A);
        fromListToJson("src/JsonFiles/FlightTickets.json",flightsList);

         */
    }


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
