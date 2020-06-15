package Aerotaxi.Core.Airplanes;

public class Bronze extends Aircraft {
    public Bronze(int fuelCapacity, int costPerKm, int capacity, int speed, PropulsionType propulsionType, int passengerFee) {
        super(fuelCapacity, costPerKm, capacity, speed, propulsionType);
        setClassFee(3000);
        isA = "Bronze";
    }
}
