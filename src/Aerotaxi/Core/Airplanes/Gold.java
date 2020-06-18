package Aerotaxi.Core.Airplanes;

public class Gold extends Aircraft implements Catering{

   private boolean wifi;

    public Gold(int fuelCapacity, int costPerKm, int capacity, int speed, PropulsionType propulsionType, boolean wifi) {
        super(fuelCapacity, costPerKm, capacity, speed, propulsionType);
        isA = "Gold";
        setClassFee(6000);
        this.wifi = wifi;
    }

    public String catering(){
        return "Catering gourmet";
    }

    public boolean hasWifi(){
        return wifi;
    }


}
