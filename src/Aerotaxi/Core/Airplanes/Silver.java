package Aerotaxi.Core.Airplanes;

public class Silver extends Aircraft {

    private boolean catering;

    public Silver(int fuelCapacity, int costPerKm, int capacity, int speed, PropulsionType propulsionType, int passengerFee) {
        super(fuelCapacity, costPerKm, capacity, speed, propulsionType, passengerFee);
        setClassFee(4000);
        catering = true;
        isA = "Silver";
    }

    public boolean hasCatering(){
        return  catering;
    }
}
