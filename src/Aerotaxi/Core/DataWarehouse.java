package Aerotaxi.Core;

import Aerotaxi.Core.Airplanes.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import static Aerotaxi.Utilities.JsonSaveLoad.fromJsonToList;


public class DataWarehouse {

    private static List<User> userList = fromJsonToList("src/JsonFiles/Users.json");
    private static List<Aircraft> aircraftList = fromJsonToList("src/JsonFiles/Airplanes.json");
    private static List<FlightTicket> flightList = fromJsonToList("src/JsonFiles/FlightTickets.json");

    private static User loggedUser;
    private static LocalDate  currentDate = LocalDate.now();

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

    public static List<Aircraft> availablePlanes(LocalDate date){
        List<FlightTicket> flightsThatDate = new ArrayList<>();
        flightList.stream().filter(x -> x.getDate().equals(date)).forEach(flightsThatDate::add); //Hago una lista con todos los vuelos de la fecha por parametro

        List<Aircraft> freePlanes = new ArrayList<>(aircraftList); //Asumo que todos los aviones estan disponibles
        flightsThatDate.forEach(x -> freePlanes.remove(x.getPlane())); //saco todos los aviones que estan en la lista de vuelos del dia


        return freePlanes;
    }


    public static List<FlightTicket> getUserFlights(){
        return flightList.stream().filter(x -> x.getClient().equals(loggedUser)).collect(Collectors.toList());
    }

    public static boolean cancelFlight(FlightTicket flight){
        if(!flight.getDate().isAfter(currentDate)) return false;
        User client = userList.stream().filter(x -> x.equals(flight.getClient())).findFirst().get();
        client.setTotalSpent(client.getTotalSpent() - flight.getCost()); //getting the user from the main list and subtracting the flight's cost

        flightList.remove(flight);
        return true;

    }


    public static void createFlight(User client, Aircraft plane, LocalDate date, City origin, City Destination){

    }//TODO: this



}
