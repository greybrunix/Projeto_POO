package model;

import java.util.Map;
import view.View;

import java.io.*;
import java.time.LocalDate;
import java.util.HashMap;

public class Model implements Serializable{
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

    public Map<String, SmartDevice> getDevs(){return this.devices_no_house;}
    public Map<String, SmartHouse> getHouse(){return this.houses_no_contract;}
    public Map<String, SmartEP> getEnerg(){return this.energ_prov;}
    public LocalDate getDate(){ return this.date;}

    /**
     * 
     * @param dev
     */
    public void addIsolDevice(SmartDevice dev){
        this.devices_no_house.put(dev.getId(), dev.clone());
    }
    /**
     * 
     * @param house
     */
    public void addIsolHouse(SmartHouse house){
        this.houses_no_contract.put(house.getOwner(), house.clone());
    }
    /**
     * 
     * @param energ
     */
    public void addEnerg(SmartEP energ){
        this.energ_prov.put(energ.getName(), energ.clone());
    }
    /**
     * 
     * @param name
     * @param room
     */
    public void addRoom(String name, String room){
        if (existsHouse(name) && !houses_no_contract.get(name).getRooms().containsKey(room))
            houses_no_contract.get(name).addRoom(room);
    }
    /**
     * 
     * @param id
     * @param name
     * @param room
     */
    public void addDevToHouse(String id, String name, String room){
        if (existsHouse(name) && houses_no_contract.get(name).getRooms().containsKey(room) && available((id)))
        {
            houses_no_contract.get(name).addDeviceToRoom(devices_no_house.get(id), room);
            this.devices_no_house.remove(id);
        }
        else
           View.showDeviceAddError(); 
    }
    /**
     * 
     * @param owner
     * @param comp_name
     */
    public void signContract(String owner, String comp_name){
        if (existsHouse(owner) && existsEnerg(comp_name)){
        energ_prov.get(comp_name).getHouses().put(owner, houses_no_contract.get(owner));
        houses_no_contract.remove(owner);
        }
    }
    /**
     * 
     * @param days
     */
    public void skipTime(int days){
        this.date = date.plusDays(days);
    }
    /**
     * 
     * @param d
     * @return
     */
    private boolean available(String d){
        for (SmartDevice dev : devices_no_house.values())
        if (d.equals(dev.getId())) return true;
        return false;
    }
    /**
     * 
     * @param name
     * @return
     */
    private boolean existsHouse(String name){
        for (SmartHouse house : houses_no_contract.values())
            if (name.equals(house.getOwner())) return true;
        return false;
    }
    /**
     * 
     * @param name
     * @return
     */
    private boolean existsEnerg(String name){
        for (SmartEP ep : energ_prov.values())
            if (name.equals(ep.getName())) return true;
        return false;
    }

    /**
     * 
     * @param file_name
     * @throws IOException
     */
    public void save(String file_name) throws
                        IOException
    {
        try {
            FileOutputStream fos = new FileOutputStream(file_name);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(this);
            oos.flush();
            oos.close();
        } catch (FileNotFoundException e){
            File myObj = new File(file_name);
            myObj.createNewFile();
            save(file_name);
        } catch (Exception e) {
            throw new IOException("Error saving to file. ");
        }
    }
    /**
     * 
     * @param fileName
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public Model loadState(String fileName) throws
                                IOException, ClassNotFoundException
    {
        try {
            FileInputStream fis = new FileInputStream(fileName);
            ObjectInputStream ois = new ObjectInputStream(fis);
            Model sys = (Model) ois.readObject();
            ois.close();
            return sys;
        } catch (FileNotFoundException e){
            throw new FileNotFoundException("Error file not found. ");
        } catch (IOException e) {
            throw new IOException("Error Loading File. ");
        } catch (ClassNotFoundException e){
            throw new ClassNotFoundException("Error, Class not found");
        }
    }
}
