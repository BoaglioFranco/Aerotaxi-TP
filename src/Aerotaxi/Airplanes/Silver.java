package Aerotaxi.Airplanes;

public class Silver extends Aircraft {

    public Silver(int fuelCapacity, int costPerKm, int capacity, int speed, PropulsionType propulsionType, int passengerFee) {
        super(fuelCapacity, costPerKm, capacity, speed, propulsionType, passengerFee);
        isA = "Silver";
    }
}
