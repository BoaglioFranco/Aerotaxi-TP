package Aerotaxi.Core;

public class Aircraft {

    private int fuelCapacity; //no sirve de nada
    private int costPerKm;
    private int capacity;
    private int speed;
    private PropulsionType propulsionType;

    private int passengerFee = 3500;
    //class fee
}

enum PropulsionType {
    MOTOR_PISTONES, MOTOR_REACCION, MOTOR_HELICE
}