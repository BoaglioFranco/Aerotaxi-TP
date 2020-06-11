package Aerotaxi.Core.Airplanes;

public class Gold extends Aircraft{

   private boolean catering;
   private boolean wifi;

    public Gold(int fuelCapacity, int costPerKm, int capacity, int speed, PropulsionType propulsionType, int passengerFee, boolean wifi) {
        super(fuelCapacity, costPerKm, capacity, speed, propulsionType, passengerFee);
        isA = "Gold";
        setClassFee(6000);
        catering = true;
        this.wifi = wifi;
    }

    public boolean hasCatering(){
        return catering;
    }

    public boolean hasWifi(){
        return wifi;
    }


}
