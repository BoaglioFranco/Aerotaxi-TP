package Aerotaxi.Controllers;

import Aerotaxi.Core.City;
import Aerotaxi.Core.FlightTicket;

import java.time.LocalDate;

public class StaticController {

    public static int passengers;
    public static LocalDate date;
    public static City origin;
    public static City destination;
    public static FlightTicket userSelectedFlight; /// ChoosePlaneController to confirmFlight

    public static void loadData(int pass,LocalDate userDate,City originCity,City destinationCity){
        passengers = pass;
        date = userDate;
        origin = originCity;
        destination = destinationCity;
    }



}
