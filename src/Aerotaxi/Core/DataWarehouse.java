package Aerotaxi.Core;

import Aerotaxi.Core.Airplanes.Aircraft;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import static Aerotaxi.Utilities.JsonSaveLoad.fromJsonToList;
import static Aerotaxi.Utilities.JsonSaveLoad.fromListToJson;


public class DataWarehouse {

    private static final List<User> userList = fromJsonToList("src/JsonFiles/Users.json");
    private static final List<Aircraft> aircraftList = fromJsonToList("src/JsonFiles/Airplanes.json");
    private static final List<FlightTicket> flightList = fromJsonToList("src/JsonFiles/FlightTickets.json");

    private static User loggedUser;
    private static final LocalDate  currentDate = LocalDate.now();

    static { //marca los vuelos como hechos o no segun con la fecha del sistema al momento de abrir el programa
        for(FlightTicket flight : flightList){
            flight.setDone(flight.getDate().isBefore(currentDate));
        }
    }


    public static User getLoggedUser(){
        return loggedUser;
    }

    public static void logout(){ loggedUser = null;}

    public static LocalDate getCurrentDate(){
        return currentDate;
    }

    public static List<User> getUserList() {
        return userList;
    }

    public static List<FlightTicket> getFlightList() {
        return flightList;
    }

    public static void saveFiles(){
        fromListToJson("src/JsonFiles/Users.json", userList);
        fromListToJson("src/JsonFiles/Airplanes.json", aircraftList);
        fromListToJson("src/JsonFiles/FlightTickets.json", flightList);
    }

    public static boolean validateUser( String username, String password){ //login validation

        Optional<User> optionalUser = userList.stream().filter(c -> c.getUsername().equals(username) && c.getPassword().equals(password))
                .findFirst(); //searching for the user...

        optionalUser.ifPresent(user -> loggedUser = user);//If there is such user, it marks them as logged in.

        return optionalUser.isPresent();
    }


    public static boolean isUsernameTaken(String username){ //para registrarse

        return userList.stream().anyMatch(c -> username.equals(c.getUsername()));
    }


    public static void addAndLogInUser(User user){//registration
        userList.add(user);
        loggedUser = user;
    }


    public static List<Aircraft> getAvailablePlanes(LocalDate date, int passengers){
        List<Aircraft> busyPlanes = new ArrayList<>();
        flightList.stream().filter(x -> x.getDate().isEqual(date)).forEach(x -> busyPlanes.add(x.getPlane())); //Hago una lista con todos los vuelos de la fecha por parametro

        List<Aircraft> freePlanes = new ArrayList<>(aircraftList); //agrego todos los aviones a la lista
        freePlanes.removeAll(busyPlanes);//remuevo todos los aviones que no estan ocupados

        freePlanes = freePlanes.stream().filter(x -> x.getCapacity() >= passengers).collect(Collectors.toList()); //filtro todos los aviones de menor capacidad

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



    private static final Map<String, Integer> planeClassHierarchy = new HashMap<>();
    static{
        planeClassHierarchy.put("None", 0);
        planeClassHierarchy.put("Bronze", 1);
        planeClassHierarchy.put("Silver", 2);
        planeClassHierarchy.put("Gold", 3);
    }

    private static void updateBestClass(User user, String newFlightType){
        if(planeClassHierarchy.get(newFlightType) > planeClassHierarchy.get(user.getBestClass()))
            user.setBestClass(newFlightType);
    }


}
