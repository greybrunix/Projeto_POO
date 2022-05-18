package model;

import java.util.Map;
import java.util.Set;

import view.View;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;

public class Model {
    private final Map<String, SmartDevice> devices_no_house;
    private final Map<String, SmartHouse> houses_no_contract;
    private final Map<String, SmartEP> energ_prov;
    private LocalDate date;

    public Model(){
        this.devices_no_house = new HashMap<String, SmartDevice>();
        this.houses_no_contract = new HashMap<String, SmartHouse>();
        this.energ_prov = new HashMap<String, SmartEP>();
        this.date = LocalDate.now();
    }

    public void addIsolDevice(SmartDevice dev){
        this.devices_no_house.put(dev.getId(), dev.clone());
    }
    public void addIsolHouse(SmartHouse house){
        this.houses_no_contract.put(house.getOwner(), house.clone());
    }
    public void addEnerg(SmartEP energ){
        this.energ_prov.put(energ.getName(), energ.clone());
    }
    public void addRoom(String name, String room){
        if (existsHouse(name) && !houses_no_contract.get(name).getRooms().containsKey(room))
            houses_no_contract.get(name).addRoom(room);
    }
    public void addDevToHouse(String id, String name, String room){
        if (existsHouse(name) && houses_no_contract.get(name).getRooms().containsKey(room) && available((id)))
        {
            houses_no_contract.get(name).addDeviceToRoom(devices_no_house.get(id), room);
            this.devices_no_house.remove(id);
        }
        else
           View.showDeviceAddError(); 
    }
    public void signContract(String owner, String comp_name){
        if (existsHouse(owner) &&){
        energ_prov.get(comp_name).getHouses().put(owner, houses_no_contract.get(owner));
        houses_no_contract.remove(owner);
        }
    }

    public void skipTime(int days){
        this.date.plusDays(days);
    }

    private boolean available(String d){
        for (SmartDevice dev : devices_no_house.values())
        if (d == dev.getId()) return true;
        return false;
    }
    private boolean existsHouse(String name){
        for (SmartHouse house : houses_no_contract.values())
            if (name == house.getOwner()) return true;
        return false;
    }
}
