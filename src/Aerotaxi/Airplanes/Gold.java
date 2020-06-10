package Aerotaxi.Airplanes;

public class Gold extends Aircraft{
    public Gold(int fuelCapacity, int costPerKm, int capacity, int speed, PropulsionType propulsionType, int passengerFee) {
        super(fuelCapacity, costPerKm, capacity, speed, propulsionType, passengerFee);
        isA = "Gold";
    }
}
