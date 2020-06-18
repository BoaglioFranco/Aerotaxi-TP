package Aerotaxi.Core.Airplanes;

public class Silver extends Aircraft implements Catering {


    public Silver(int fuelCapacity, int costPerKm, int capacity, int speed, PropulsionType propulsionType) {
        super(fuelCapacity, costPerKm, capacity, speed, propulsionType);
        setClassFee(4000);
        isA = "Silver";
    }

    public String catering(){
        return "Catering gourmet";
    }
}
