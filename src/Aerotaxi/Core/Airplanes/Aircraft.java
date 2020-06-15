package Aerotaxi.Core.Airplanes;

import java.util.Objects;

public class Aircraft {

    private int fuelCapacity; //no sirve de nada
    private int costPerKm;
    private int capacity;
    private int speed;
    private PropulsionType propulsionType;
    private int classFee;
    protected String isA = "Aircraft"; //gson stuff

    public Aircraft(int fuelCapacity, int costPerKm, int capacity, int speed, PropulsionType propulsionType) {
        this.fuelCapacity = fuelCapacity;
        this.costPerKm = costPerKm;
        this.capacity = capacity;
        this.speed = speed;
        this.propulsionType = propulsionType;
    }


    protected void setClassFee(int fee){this.classFee = fee;}
    public int getClassFee(){ return classFee;}

    public int getFuelCapacity() {
        return fuelCapacity;
    }

    public void setFuelCapacity(int fuelCapacity) {
        this.fuelCapacity = fuelCapacity;
    }

    public int getCostPerKm() {
        return costPerKm;
    }

    public void setCostPerKm(int costPerKm) {
        this.costPerKm = costPerKm;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public PropulsionType getPropulsionType() {
        return propulsionType;
    }

    public void setPropulsionType(PropulsionType propulsionType) {
        this.propulsionType = propulsionType;
    }

    public String getType() {
        return isA;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Aircraft)) return false;
        Aircraft aircraft = (Aircraft) o;
        return fuelCapacity == aircraft.fuelCapacity &&
                costPerKm == aircraft.costPerKm &&
                capacity == aircraft.capacity &&
                speed == aircraft.speed &&
                propulsionType == aircraft.propulsionType &&
                Objects.equals(isA, aircraft.isA);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fuelCapacity, costPerKm, capacity, speed, propulsionType, isA);
    }
}
