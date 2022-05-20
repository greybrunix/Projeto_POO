package model;

import java.util.Map;
import view.View;

import java.io.*;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.HashMap;

public class Model implements Serializable{
    private final Map<String, SmartDevice> devices_no_house;
    private final Map<String, SmartHouse> houses_no_contract;
    private final Map<String, SmartEP> energ_prov;
    private LocalDate date;
    private final Map<String, SmartEP> energ_with_changes;
    private final Map<String, SmartDevice> dev_with_changes;

    public Model(){
        this.devices_no_house = new HashMap<String, SmartDevice>();
        this.houses_no_contract = new HashMap<String, SmartHouse>();
        this.energ_prov = new HashMap<String, SmartEP>();
        this.energ_with_changes = new HashMap<String, SmartEP>();
        this.dev_with_changes = new HashMap<String, SmartDevice>();
        this.date = LocalDate.now();
    }

    public Map<String, SmartDevice> getDevs(){
        return new HashMap<String, SmartDevice>(this.devices_no_house);
    }
    public Map<String, SmartHouse> getHouse(){
        return new HashMap<String, SmartHouse>(this.houses_no_contract);
    }
    public Map<String, SmartEP> getEnerg(){
        return new HashMap<String, SmartEP>(this.energ_prov);
    }
    public LocalDate getDate(){
        return this.date;
    }

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
        if (this.dev_with_changes.size() > 0){
            this.date.plusDays(1);
            this.operateDevChanges();
            this.date.plusDays(days-1);
        }
        else this.date = date.plusDays(days);
        if (date.isEqual(date.with(TemporalAdjusters.firstDayOfMonth()))){
            View.showAllBills(this);
            if (this.energ_with_changes.size() > 0)
                this.operateEnergChanges();
        }
    }
    private void operateDevChanges() {
    }

    private void operateEnergChanges() {
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
            File f = new File(file_name);
            if (!f.createNewFile()){
                f.delete();
                f.createNewFile();
            }
            FileOutputStream fos = new FileOutputStream(file_name);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(this);
            oos.flush();
            oos.close();
        } catch (FileNotFoundException e){
            //e.printStackTrace();
            throw new FileNotFoundException("File not found. ");
        } catch (IOException e) {
            //e.printStackTrace();
            throw new IOException("Error writing to file. ");
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
        Model sys = new Model();
        try {
            FileInputStream fis = new FileInputStream(fileName);
            ObjectInputStream ois = new ObjectInputStream(fis);
            sys = (Model) ois.readObject();
            ois.close();
            return sys;
        } catch (FileNotFoundException e){
            //e.printStackTrace();
            throw new FileNotFoundException("Error file not found. ");
        } catch (IOException e) {
            //e.printStackTrace();
            throw new IOException("Error Loading File. ");
        } catch (ClassNotFoundException e){
            //e.printStackTrace();
            throw new ClassNotFoundException("Error, Class not found");
        }
}

    public void turnOnDev(String id) {
        String owner = whereIsDev(id);
        String comp = whereIsHouse(owner);
        this.energ_prov.get(comp).getHouses().get(owner).setDeviceOn(id);
    }

    public void turnOffDev(String id) {
        String owner = whereIsDev(id);
        String comp = whereIsHouse(owner);
        this.energ_prov.get(comp).getHouses().get(owner).setDeviceOff(id);
    } //TODO: MAKE SURE THIS ONLY APPLIES WHEN DAY ENDS

    public void incVol(String id) {
        String owner = whereIsDev(id);
        String comp = whereIsHouse(owner);
        SmartSpeaker speak = new SmartSpeaker();
        SmartDevice sd = this.energ_prov.get(comp).getHouses().get(owner).getDevices().get(id);
        if (sd.getClass().equals(speak.getClass())){
            speak = (SmartSpeaker) sd;
            speak.volumeUP();
        }
    }

    public void decVol(String id) {
        String owner = whereIsDev(id);
        String comp = whereIsHouse(owner);
        SmartSpeaker speak = new SmartSpeaker();
        SmartDevice sd = this.energ_prov.get(comp).getHouses().get(owner).getDevices().get(id);
        if (sd.getClass().equals(speak.getClass())){
            speak = (SmartSpeaker) sd;
            speak.volumeDOWN();
        }
    }

    public void incTone(String id) {
        String owner = whereIsDev(id);
        String comp = whereIsHouse(owner);
        SmartBulb bulb = new SmartBulb();
        SmartDevice sd = this.energ_prov.get(comp).getHouses().get(owner).getDevices().get(id);
        if (sd.getClass().equals(bulb.getClass())){
            bulb = (SmartBulb) sd;
            bulb.incTone();
        }
    }

    public void decTone(String id) {
        String owner = whereIsDev(id);
        String comp = whereIsHouse(owner);
        SmartBulb bulb = new SmartBulb();
        SmartDevice sd = this.energ_prov.get(comp).getHouses().get(owner).getDevices().get(id);
        if (sd.getClass().equals(bulb.getClass())){
            bulb = (SmartBulb) sd;
            bulb.decTone();
        }
    }

    public void getDevDC(String id) {
        String owner = whereIsDev(id);
        String comp = whereIsHouse(owner);
        this.energ_prov.get(comp).getHouses().get(owner).getDevices().get(id).getConsumption();
    }

    public void setAllOn(String owner) {
        String comp = whereIsHouse(owner);
        this.energ_prov.get(comp).getHouses().get(owner).setAllOn();
    }

    public void setAllOnDiv(String owner, String room) {
        String comp = whereIsHouse(owner);
        this.energ_prov.get(comp).getHouses().get(owner);
    } // TODO: MeTHOD TO MAKE ALL DEVICES CHANGE IN ONE ROOM

    public void setAllOff(String owner) {
        String comp = whereIsHouse(owner);
        this.energ_prov.get(comp).getHouses().get(owner).setAllOff();
    } //TODO: Make sure this only happens when day ends

    public void setAllOffDiv(String owner, String room) {
        String comp = whereIsHouse(owner);
        this.energ_prov.get(comp).getHouses().get(owner);
    } // TODO: METHOD TO MAKE ALL DEVICES CHANGE IN ONE ROOM and only when day ends

    public void changeContract(String owner, String comp) {
    } // TODO: Make sure this only applies when month ends

    public void changeBaseValue(String comp, int value) {
        this.energ_prov.get(comp).setBV(value);
    } // TODO: Make sure this only applies when month ends

    public void changeTaxFactor(String comp, double tax) {
        this.energ_prov.get(comp).setTax(tax);
    } // TODO: Make sure this only applies when month ends

    public void changeFormula(String comp) {
        this.energ_prov.get(comp).changePrice();
    } // TODO: Make sure this only applies when month ends

    private String whereIsDev(String id){
        String owner = new String();
        for (String name_of_comp : this.energ_prov.keySet())
            for (SmartHouse house : this.energ_prov.get(name_of_comp).getHouses().values()) // get is null
                if (house.getDevices().containsKey(id)){
                    owner = house.getOwner();
                }
        return owner;
    }
    private String whereIsHouse(String owner){
        String name_of_comp = new String();
        for (String comp : this.energ_prov.keySet())
            if (this.energ_prov.get(comp).getHouses().containsKey(owner)) // get is null
                name_of_comp = comp;
        return name_of_comp;
    }
}
