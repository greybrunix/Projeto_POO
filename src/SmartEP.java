package src;

import java.util.Map;
import java.util.HashMap;

public class SmartEP {
    public static final int tax_factor = 1;
    public static final int base_value = 10;
    private int price;
    private Map<String,SmartHouse> houses;

    public SmartEP(){
        this.price = 0;
        this.houses = new HashMap<String,SmartHouse>();
    }
    public SmartEP(int price){
        this.price = price;
        this.houses = new HashMap<String,SmartHouse>();
    }
    private SmartEP(SmartEP ep){
        this.price = ep.getPrice();
        this.houses = ep.getHouses();
    }
    public int getPrice(){
        return this.price;
    }
    public Map<String,SmartHouse> getHouses(){
        return this.houses;
    }

    public void compute_price_one(String owner){
        int result = 0;
        for (SmartDevice device : this.houses.get(owner).getDevices().values()){
            result += this.houses.get(owner).getDevices().size()>10?
                    (base_value*device.getConsumption()*(1+tax_factor))*0.9:
                    (base_value*device.getConsumption()*(1+tax_factor))*0.75;
        }
        this.price = result;
    }
    public SmartEP clone(){
        return new SmartEP(this);
    }
}
