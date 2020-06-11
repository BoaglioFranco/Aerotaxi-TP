package Aerotaxi.Core;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public enum City {
    BUENOS_AIRES, MONTEVIDEO, CORDOBA, SANTIAGO;

    private static Map<Set<City>, Integer> distanceMap;

    static{                 //Static initializer para cargar las distancias entre las distintas ciudades
        distanceMap = new HashMap<>();
        distanceMap.put(Set.of(City.BUENOS_AIRES, City.CORDOBA), Integer.valueOf(695));
        distanceMap.put(Set.of(City.BUENOS_AIRES, City.SANTIAGO), Integer.valueOf(1400));
        distanceMap.put(Set.of(City.BUENOS_AIRES, City.MONTEVIDEO), Integer.valueOf(950));
        distanceMap.put(Set.of(City.CORDOBA, City.MONTEVIDEO), Integer.valueOf(1190));
        distanceMap.put(Set.of(City.CORDOBA, City.SANTIAGO), Integer.valueOf(1050));
        distanceMap.put(Set.of(City.MONTEVIDEO, City.SANTIAGO), Integer.valueOf(2100));
    }


    public static int getDistance(City origin, City destination){
        if(origin == destination) return 0;
        Integer kms = distanceMap.get(Set.of(origin, destination));
        return kms == null ? -1 : kms;
    }

}
