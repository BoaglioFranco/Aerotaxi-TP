package Aerotaxi.Core;

import Aerotaxi.Core.Airplanes.Aircraft;

import java.time.LocalDate;
import java.util.Objects;

public class FlightTicket {

    private User client;
    private Aircraft plane;
    private LocalDate date;
    private int passengers;
    private City origin;
    private City destination;
    private int cost;
    private final String isA = "FlightTicket";

    boolean isDone;

    public FlightTicket(User client, Aircraft plane, LocalDate date,int passengers, City origin, City destination) {
        this.client = client;
        this.plane = plane;
        this.date = date;
        this.passengers = passengers;
        this.origin = origin;
        this.destination = destination;

        setCost();
        isDone = false;
    }


    public User getClient() {
        return client;
    }


    public Aircraft getPlane() {
        return plane;
    }


    public LocalDate getDate() {
        return date;
    }


    public int getCost(){
        return cost;
    }

    private void setCost(){
        cost = City.getDistance(origin, destination) * plane.getCostPerKm() + passengers * 3500 + plane.getClassFee();
    }

    public int getPassengers() {
        return passengers;
    }

    public City getOrigin() {
        return origin;
    }

    public City getDestination() {
        return destination;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean bool){
        isDone = bool;
    }


    @Override   
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FlightTicket that = (FlightTicket) o;
        return cost == that.cost &&
                Objects.equals(client, that.client) &&
                Objects.equals(plane, that.plane) &&
                Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(client, plane, date, cost);
    }
}
