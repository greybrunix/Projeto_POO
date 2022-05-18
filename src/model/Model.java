package model;

import java.util.Map;
import java.util.Set;
import java.util.HashMap;
import java.util.HashSet;

public class Model {
    private final Map<String, SmartDevice> devices_no_house;
    private final Map<String, SmartHouse> houses_no_contract;
    private final Set<SmartEP> energ_prov;
    public Model(){
        this.devices_no_house = new HashMap<String, SmartDevice>();
        this.houses_no_contract = new HashMap<String, SmartHouse>();
        this.energ_prov = new HashSet<SmartEP>();
    }

    public void addIsolDevice(SmartDevice dev){
        this.devices_no_house.put(dev.getId(), dev.clone());
    }
    public void addIsolHouse(SmartHouse house){
        this.houses_no_contract.put(house.getOwner(), house.clone());
    }
    public void addEnerg(SmartEP energ){
        this.energ_prov.add(energ.clone());
    }
    public void addDevToHouse(SmartDevice dev, SmartHouse house, String room){
        if (house.getRooms().containsKey(room) && available(dev))
        {
            house.addDeviceToRoom(dev, room);
            devices_no_house.remove(dev.getId());
        }
    }

    private boolean available(SmartDevice d){
        for (SmartDevice dev : devices_no_house.values())
            if (d == dev) return true;
        return false;
    }
}
