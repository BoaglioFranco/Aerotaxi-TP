package Aerotaxi.Core;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public enum City {
    BUENOS_AIRES("Buenos Aires"), MONTEVIDEO("Montevideo"), CORDOBA("Cordoba"), SANTIAGO("Santiago");

    private static Map<Set<City>, Integer> distanceMap;
    private String name= "";

    City(String name){
        this.name = name;
    }

    static{                 //Static initializer para cargar las distancias entre las distintas ciudades
        distanceMap = new HashMap<>();
        distanceMap.put(Set.of(City.BUENOS_AIRES, City.CORDOBA), 695);
        distanceMap.put(Set.of(City.BUENOS_AIRES, City.SANTIAGO), 1400);
        distanceMap.put(Set.of(City.BUENOS_AIRES, City.MONTEVIDEO), 950);
        distanceMap.put(Set.of(City.CORDOBA, City.MONTEVIDEO), 1190);
        distanceMap.put(Set.of(City.CORDOBA, City.SANTIAGO), 1050);
        distanceMap.put(Set.of(City.MONTEVIDEO, City.SANTIAGO), 2100);
    }


    public static int getDistance(City origin, City destination){
        if(origin == destination) return 0;
        Integer kms = distanceMap.get(Set.of(origin, destination));
        return kms == null ? -1 : kms;
    }

    public String getName(){
        return name;
    }

}
