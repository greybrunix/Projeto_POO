package model;

import java.util.Map;
import java.io.Serializable;
import java.util.HashMap;

/**
 * 
 * 
 */
public class SmartEP implements Serializable{
    public static final int tax_factor = 1;
    public static final int base_value = 50;
    private String name;
    private int price;
    private Map<String,SmartHouse> houses;

    public SmartEP(){
        this.price = 0;
        this.houses = new HashMap<String,SmartHouse>();
    }
    public SmartEP(String name){
        this.name  = name;
        this.price = 0;
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
    public boolean equals(Object o){
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        SmartEP comerc = (SmartEP) o;
        return (this.name == comerc.getName()
                && this.houses.equals(comerc.getHouses()));
    }
    @Override
    public SmartEP clone(){
        return new SmartEP(this);
    }
}
