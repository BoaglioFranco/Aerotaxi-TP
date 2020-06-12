package Aerotaxi.Core;

import Aerotaxi.Core.Airplanes.Aircraft;

import java.time.LocalDate;
import java.util.Objects;

public class FlightTicket {

    private User client;
    private Aircraft plane;
    private LocalDate date;
    private City origin;
    private City destination;
    private int cost;

    boolean isDone;

    public FlightTicket(User client, Aircraft plane, LocalDate date, City origin, City destination) {
        this.client = client;
        this.plane = plane;
        this.date = date;
        this.origin = origin;
        this.destination = destination;

        isDone = false;
    }

    public FlightTicket() {
        isDone = false;
    }

    public User getClient() {
        return client;
    }

    public void setClient(User client) {
        this.client = client;
    }

    public Aircraft getPlane() {
        return plane;
    }

    public void setPlane(Aircraft plane) {
        this.plane = plane;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getCost(){
        return cost;
    }

    private void setCost(){

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

    public void markAsDone(){
        isDone = true;
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
