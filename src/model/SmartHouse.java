package model;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
/**
 * Class that refers to a house, a collection of devices and rooms
 */
public class SmartHouse extends SmartEP {

    private Map<String, SmartDevice> devices;
    private Map<String, List<String>> rooms;
    private String owner;
    private String NIF;

    public SmartHouse(){ // Might remove this class
        this.owner = "";
        this.NIF =  "";
        devices = new HashMap<String, SmartDevice>();
        rooms = new HashMap<String, List<String>>();
    }
    public SmartHouse(String owner, String NIF){
        this.owner = owner;
        this.NIF = NIF;
        this.devices = new HashMap<String, SmartDevice>();
        this.rooms = new HashMap<String, List<String>>();
    }
    private SmartHouse(SmartHouse h){
        this.owner = h.getOwner();
        this.NIF = h.getNIF();
        this.devices = h.getDevices();
        this.rooms = h.getRooms();
    }
    public String getOwner(){
        return this.owner;
    }
    public String getNIF(){
        return this.NIF;
    }
    public Map<String, SmartDevice> getDevices(){
        return devices;
    }
    public Map<String, List<String>> getRooms(){
        return rooms;
    }
    /*private setOwner(String newOwn){
        this.owner = newOwn;
    }
    public void setNIF(String nif){
        this.NIF = nif;
    }*/

    public void addDeviceToRoom(SmartDevice dev, String room){
        this.devices.put(dev.getId(), dev);
        this.rooms.get(room).add(dev.getId()); // What the fuck?
    }

    public void setDeviceOn(String id){
        devices.get(id).setON();
    }
    public void setAllOn(){
        for (SmartDevice device: devices.values())
            device.setON();
        for (List<String> devices_in_room: rooms.values())
            for (String identifier: devices_in_room)
                setDeviceOn(identifier);
    }
    public void setDeviceOff(String id){
        devices.get(id).setOFF();
    }
    public void setAllOff(){
        for (SmartDevice device: devices.values())
            device.setOFF();
        for (List<String> devices_in_room: rooms.values())
            for (String identifier: devices_in_room)
                setDeviceOff(identifier);
    }
    public void addRoom(String room){
        List<String> devs = new ArrayList<String>();
        this.rooms.put(room, devs);
    }


    public boolean deviceExists(String id)
    {
        for (List<String> devices_in_room: rooms.values())
            for (String identifier: devices_in_room)
                if (id == identifier) return true;
        return false;
    }

    public boolean equals(Object o){
        if (o == this) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        SmartHouse house = (SmartHouse) o;
        return (house.getNIF() == this.getNIF() &&
                house.getOwner() == this.getNIF());
    }
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("This house belongs to:\t")
          .append(this.getOwner())
          .append("With Fiscal Identifying Number:\t")
          .append(this.getNIF())
          .append("With the following devices:\t")
          .append(devices.toString())
          .append("And the following rooms:\t")
          .append(rooms.keySet().toString());
        return sb.toString();
    }
    public SmartHouse clone(){
        return new SmartHouse(this);
    }
}