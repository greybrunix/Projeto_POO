package model;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
/**
 * Class that refers to a house, a collection of devices and rooms
 */
public class SmartHouse extends SmartEP{

    private final Map<String, SmartDevice> devices; // id -> device
    private final Map<String, List<String>> rooms; // name of room -> List of IDs
    private final String owner;
    private final String NIF;

    public SmartHouse(){ // Might remove this class
        this.owner = "";
        this.NIF =  "";
        this.devices = new HashMap<String, SmartDevice>();
        this.rooms = new HashMap<String, List<String>>();
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
        Map<String, SmartDevice> devices = new HashMap<String, SmartDevice>();
        for (SmartDevice dev : this.devices.values())
            devices.put(dev.getId(),dev.clone());
        return devices;
    }
    public Map<String, List<String>> getRooms(){
        Map<String,List<String>> rooms = new HashMap<String,List<String>>();
        for (String room : this.rooms.keySet()){
            for (List<String> devs : this.rooms.values()){
                List<String> devices = new ArrayList<String>(devs);
                rooms.put(room, devices);
            }
        }
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
    public void incTone(String id){
        devices.get(id).incTone();
    }
    public void decTone(String id){
        devices.get(id).decTone();
    }
    public void incVol(String id){
        devices.get(id).volumeUP();
    }
    public void decVol(String id){
        devices.get(id).volumeDOWN();
    }
    public void setAllOn(){
        for (List<String> devices_in_room: rooms.values())
            for (String identifier: devices_in_room)
                setDeviceOn(identifier);
    }
    public void setDeviceOff(String id){
        devices.get(id).setOFF();
    }
    public void setAllOff(){
        for (List<String> devices_in_room: rooms.values())
            for (String identifier: devices_in_room)
                setDeviceOff(identifier);
    }
    public void setAllinRoomOn(String room){
        for (String id : this.rooms.get(room))
            setDeviceOn(id);
    }
    public void setAllinRoomOff(String room){
        for (String id: this.rooms.get(room))
            setDeviceOff(id);
    }
    public void addRoom(String room){
        List<String> devs = new ArrayList<String>();
        this.rooms.put(room, devs);
    }


    public boolean deviceExists(String id)
    {
        for (List<String> devices_in_room: rooms.values())
            for (String identifier: devices_in_room)
                if (id.equals(identifier)) return true;
        return false;
    }

    public boolean equals(Object o){
        if (o == this) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        SmartHouse house = (SmartHouse) o;
        return (house.getNIF().equals(this.getNIF()) &&
                house.getOwner().equals(this.getOwner()));
    }
    public String toString(){
        StringBuilder sb = new StringBuilder();
        return sb.append("( ")
          .append(this.getOwner())
          .append(", ")
          .append(this.getNIF())
          .append(" : ")
          .append(devices.values().toString())
          .append(" ; ")
          .append(rooms.keySet().toString())
          .append(" )").toString();
    }
    public SmartHouse clone(){
        return new SmartHouse(this);
    }
}