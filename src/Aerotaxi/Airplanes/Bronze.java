package Aerotaxi.Airplanes;

public class Bronze extends Aircraft {
    public Bronze(int fuelCapacity, int costPerKm, int capacity, int speed, PropulsionType propulsionType, int passengerFee) {
        super(fuelCapacity, costPerKm, capacity, speed, propulsionType, passengerFee);
        isA = "Bronze";
    }
}
