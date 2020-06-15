package Aerotaxi.Core.Airplanes;

public class Aircraft {

    private int fuelCapacity; //no sirve de nada
    private int costPerKm;
    private int capacity;
    private int speed;
    private PropulsionType propulsionType;
    protected String isA = "Aircraft";

    public Aircraft(int fuelCapacity, int costPerKm, int capacity, int speed, PropulsionType propulsionType, int passengerFee) {
        this.fuelCapacity = fuelCapacity;
        this.costPerKm = costPerKm;
        this.capacity = capacity;
        this.speed = speed;
        this.propulsionType = propulsionType;
        this.passengerFee = passengerFee;
    }

    private int passengerFee = 3500;
    private int classFee;

    protected void setClassFee(int fee){this.classFee = fee;}
    public int getClassFee(){ return classFee;}


}

