package model;

import view.View;

import java.io.*;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.HashMap;
import java.util.Map;

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
        energ_prov.get(comp_name).addHouse(owner, this.houses_no_contract.get(owner));
        houses_no_contract.remove(owner);
        }
        /*for (SmartEP ep : energ_prov.values()){
            System.out.println(ep.toString());
        }*/
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
        try {
            FileInputStream fis = new FileInputStream(fileName);
            ObjectInputStream ois = new ObjectInputStream(fis);
            Model sys = (Model) ois.readObject();
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
        System.out.println(comp);
        SmartDevice sd = this.energ_prov.get(comp).getHouses().get(owner).getDevices().get(id).clone();
        sd.setOFF();
        this.dev_with_changes.put(id, sd);
    } //TODO: Check this sus code

    public void incVol(String id) {
        String owner = whereIsDev(id);
        String comp = whereIsHouse(owner);
        this.energ_prov.get(comp).getHouses().get(owner).incVol(id);
        System.out.println(energ_prov);
    }

    public void decVol(String id) {
        String owner = whereIsDev(id);
        String comp = whereIsHouse(owner);
        this.energ_prov.get(comp).getHouses().get(owner).decVol(id);
        System.out.println(energ_prov);
    }

    public void incTone(String id) {
        String owner = whereIsDev(id);
        String comp = whereIsHouse(owner);
        this.energ_prov.get(comp).getHouses().get(owner).incTone(id);
        System.out.println(energ_prov);
    }

    public void decTone(String id) {
        String owner = whereIsDev(id);
        String comp = whereIsHouse(owner);
        this.energ_prov.get(comp).getHouses().get(owner).decTone(id);
        System.out.println(energ_prov);
    }

    public void getDevDC(String id) {
        String owner = whereIsDev(id);
        String comp = whereIsHouse(owner);
        double res = this.energ_prov.get(comp).getHouses().get(owner).getDevices().get(id).getConsumption();
        View.showDevDC(id, res);
    }

    public void setAllOn(String owner) {
        String comp = whereIsHouse(owner);
        this.energ_prov.get(comp).getHouses().get(owner).setAllOn();
    }

    public void setAllOnDiv(String owner, String room) {
        String comp = whereIsHouse(owner);
        this.energ_prov.get(comp).getHouses().get(owner).setAllinRoomOn(room);
    }

    public void setAllOff(String owner) {
        String comp = whereIsHouse(owner);
        this.energ_prov.get(comp).getHouses().get(owner).setAllOff();
    } //TODO: Make sure this only happens when day ends

    public void setAllOffDiv(String owner, String room) {
        String comp = whereIsHouse(owner);
        this.energ_prov.get(comp).getHouses().get(owner).setAllinRoomOff(room);
    } // TODO: make this only take effect when day ends

    public void changeContract(String owner, String comp) {
        String comp_old = whereIsHouse(owner);
        SmartHouse house = this.energ_prov.get(comp_old).getHouses().remove(owner);
        this.energ_prov.put(comp, house);
    } // TODO: Make sure this only applies when month ends

    public void changeFormula(String comp) {
        this.energ_prov.get(comp).changePrice();
    } // TODO: Make sure this only applies when month ends

    private String whereIsDev(String id){
        String owner = "";
        for (String name_of_comp : this.energ_prov.keySet()){
            for (SmartHouse house : this.energ_prov.get(name_of_comp).getHouses().values()){ // get is null
                if (house.getDevices().containsKey(id)){
                    owner = house.getOwner();
                }
            }
        }
        return owner;
    }
    private String whereIsHouse(String owner){
        String comp = "";
        for (String name_of_comp : this.energ_prov.keySet()){
            if (this.energ_prov.get(name_of_comp).getHouses().containsKey(owner)){
                comp = name_of_comp;
            }
        }
        return comp;
    }
}
