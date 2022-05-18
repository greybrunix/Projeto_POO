package model;

import java.util.Map;
import java.util.HashMap;

/**
 * 
 * 
 */
public class SmartEP {
    public static final int tax_factor = 1;
    public static final int base_value = 50;
    private String name;
    private int price;
    private Map<String,SmartHouse> houses;

    public SmartEP(){
        this.price = 0;
        this.houses = new HashMap<String,SmartHouse>();
    }
    public SmartEP(String name, int price){
        this.name  = name;
        this.price = price;
        this.houses = new HashMap<String,SmartHouse>();
    }
    private SmartEP(SmartEP ep){
        this.name = ep.getName();
        this.price = ep.getPrice();
        this.houses = ep.getHouses();
    }
    public int getPrice(){
        return this.price;
    }
    public String getName(){
        return this.name;
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

    @Override
    public SmartEP clone(){
        return new SmartEP(this);
    }
}
