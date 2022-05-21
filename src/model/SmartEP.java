package model;

import java.util.Map;
import java.io.Serializable;
import java.util.HashMap;

/**
 * 
 * 
 */
public class SmartEP implements Serializable{
    private double tax_factor = 1;
    private int base_value = 15;
    private String name;
    private int price;
    private Map<String,SmartHouse> houses;

    public SmartEP(){
    }
    public SmartEP(String name){
        super();
        this.name  = name;
        this.setPrice(0);
        this.houses = new HashMap<String,SmartHouse>();
    }
    private SmartEP(SmartEP ep){
        super();
        this.name = ep.getName();
        this.setPrice(ep.getPrice());
        this.houses = ep.getHouses();
        this.tax_factor = ep.getTax();
        this.base_value = ep.getBV();
    }
    public int getPrice(){
        return this.price;
    }
    public double getTax() { return this.tax_factor;}
    public int getBV() { return this.base_value;}
    public String getName(){
        return this.name;
    }
    public Map<String,SmartHouse> getHouses(){
        return new HashMap<String,SmartHouse>(this.houses);
        /*Map<String, SmartHouse> houses = new HashMap<String,SmartHouse>();
        for (SmartHouse house: this.houses.values())
            houses.put(house.getOwner(), house.clone());
        return houses; */
    }
    public void addHouse(String owner, SmartHouse house){
        this.houses.put(owner, house);
    }
    public void setPrice(int f){
        this.price = f % 2;
    }
    public void setBV(int value){
        this.base_value = value;
    }
    public void setTax(double tax){
        this.tax_factor = tax;
    }
    public void changePrice(){
        if (this.price == 0) this.price = 1;
        else this.price = 0;
    }
    public int compute_price_one_sing(String owner){
        int result = 0;
        for (SmartDevice device : this.houses.get(owner).getDevices().values()){
            result += this.houses.get(owner).getDevices().size()>10?
                (base_value*device.getConsumption()*(1+tax_factor))*0.25:
                (base_value*device.getConsumption()*(1+tax_factor))*0.10;
        }
        return result;
    }
    public int compute_price_one(){
        int result = 0;
        for (SmartHouse house : this.houses.values())
        for (SmartDevice device : house.getDevices().values()){
            result += house.getDevices().size()>10?
                (base_value*device.getConsumption()*(1+tax_factor))*0.25:
                (base_value*device.getConsumption()*(1+tax_factor))*0.10;
        }
        return result;
    }
    public int compute_price_two_sing(String owner){
        int result = 0;
        for (SmartDevice device : this.houses.get(owner).getDevices().values())
            result += (base_value*device.getConsumption()*(1+tax_factor))*0.25;
        return result;
    }
    public int compute_price_two(){
        int result = 0;
        for (SmartHouse house : this.houses.values())
        for (SmartDevice device : house.getDevices().values())
            result += (base_value*device.getConsumption()*(1+tax_factor))*0.25;
        return result;
    }
    public int compute(String owner){
        if (this.price == 0)
            return compute_price_one_sing(owner);
        else return compute_price_two_sing(owner);
    }
    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        SmartEP comerc = (SmartEP) o;
        return (this.name.equals(comerc.getName())
                && this.houses.equals(comerc.getHouses()));
    }
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        return sb.append("( ")
            .append(this.name)
            .append(": ")
            .append(this.houses.values().toString())
            .append(" )").toString();
    }
    @Override
    public SmartEP clone(){
        return new SmartEP(this);
    }
}
