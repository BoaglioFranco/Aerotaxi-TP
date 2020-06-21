package Aerotaxi.Core.Airplanes;

import java.util.Objects;

public class Aircraft {

    private int fuelTank; //no sirve de nada
    private int costPerKm;
    private int capacity;
    private int speed;
    private PropulsionType propulsionType;
    private int classFee;
    protected String isA = "Aircraft"; //gson stuff

    public Aircraft(int fuelTank, int costPerKm, int capacity, int speed, PropulsionType propulsionType) {
        this.fuelTank = fuelTank;
        this.costPerKm = costPerKm;
        this.capacity = capacity;
        this.speed = speed;
        this.propulsionType = propulsionType;
    }


    protected void setClassFee(int fee){this.classFee = fee;}
    public int getClassFee(){ return classFee;}

    public int getFuelTank() {
        return fuelTank;
    }

    public void setFuelTank(int fuelTank) {
        this.fuelTank = fuelTank;
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

    public PropulsionType getPropulsionType() {
        return propulsionType;
    }


    public String getType() {
        return isA;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Aircraft)) return false;
        Aircraft aircraft = (Aircraft) o;
        return fuelTank == aircraft.fuelTank &&
                capacity == aircraft.capacity &&
                speed == aircraft.speed &&
                classFee == aircraft.classFee &&
                propulsionType == aircraft.propulsionType &&
                isA.equals(aircraft.isA);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fuelTank, capacity, speed, propulsionType, classFee, isA);
    }
}
