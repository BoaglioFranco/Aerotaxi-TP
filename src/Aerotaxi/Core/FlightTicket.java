package Aerotaxi.Core;

import Aerotaxi.Core.Airplanes.Aircraft;

import java.time.LocalDate;

public class FlightTicket {

    private User client;
    private Aircraft plane;
    private LocalDate date;
    private City origin;
    private City destination;
    private int cost;

    boolean isDone;
    boolean isCancelled;

    public FlightTicket(User client, Aircraft plane, LocalDate date, City origin, City destination, int cost) {
        this.client = client;
        this.plane = plane;
        this.date = date;
        this.origin = origin;
        this.destination = destination;
        this.cost = cost;

        isDone = false;
        isCancelled = false;
    }

    public FlightTicket() {
        isDone = false;
        isCancelled = false;
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
}
