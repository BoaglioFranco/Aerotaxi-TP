package Aerotaxi.Core;

import Aerotaxi.Core.Airplanes.Aircraft;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import static Aerotaxi.Utilities.JsonSaveLoad.fromJsonToList;
import static Aerotaxi.Utilities.JsonSaveLoad.fromListToJson;


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

    public static void saveFiles(){
        fromListToJson("src/JsonFiles/Users.json", userList);
        fromListToJson("src/JsonFiles/Airplanes.json", aircraftList);
        fromListToJson("src/JsonFiles/FlightTickets.json", flightList);

    }

    public static User getLoggedUser() {
        return loggedUser;
    }

    public static LocalDate getCurrentDate() {
        return currentDate;
    }

    public static boolean isUsernameTaken(String username){ //para registrarse

        return userList.stream().anyMatch(c -> username.equals(c.getUsername()));
    }


    public static void addAndLogInUser(User user){
        userList.add(user);
        loggedUser = user;
    }

    public static List<Aircraft> getAvailablePlanes(LocalDate date, int passengers){
        List<FlightTicket> flightsThatDate = new ArrayList<>();
        flightList.stream().filter(x -> x.getDate().equals(date)).forEach(flightsThatDate::add); //Hago una lista con todos los vuelos de la fecha por parametro

        List<Aircraft> freePlanes = new ArrayList<>(aircraftList); //Asumo que todos los aviones estan disponibles
        flightsThatDate.forEach(x -> freePlanes.remove(x.getPlane())); //saco todos los aviones que estan en la lista de vuelos del dia

        freePlanes.stream().filter(x -> x.getCapacity() >= passengers).forEach(freePlanes::remove); //filtro todos los aviones de menor capacidad

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


    public static void addFlight(FlightTicket flight){
        User client = userList.stream().filter(x -> x.equals(flight.getClient())).findFirst().get(); //In case the reference isnt the same as in UserList, shouldnt happen
                                                                                                     //when we actually call it but im adding it in case we need it
        client.setTotalSpent(client.getTotalSpent() + flight.getCost());//total spent
        updateBestClass(client, flight.getPlane().getType());//best type

        flightList.add(flight);
    }

    private static void updateBestClass(User user, String type){// TODO: este codigo va a ser una mierda
        if(user.getBestClass().equals("None")){
            user.setBestClass(type);
        }
    }


}
