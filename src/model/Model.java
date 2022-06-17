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
    private final Map<String, SmartEP> house_with_changes;
    private final Map<String, SmartDevice> dev_with_changes;
    private SmartHouse mostExp;
    private SmartEP mostLucrative;

    public Model(){
        this.devices_no_house = new HashMap<String, SmartDevice>();
        this.houses_no_contract = new HashMap<String, SmartHouse>();
        this.energ_prov = new HashMap<String, SmartEP>();
        this.energ_with_changes = new HashMap<String, SmartEP>();
        this.dev_with_changes = new HashMap<String, SmartDevice>();
        this.house_with_changes = new HashMap<String, SmartEP>();
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
    public SmartHouse getMostExp(){
        return this.mostExp.clone();
    }
    public SmartEP getMostLucrative(){
        return this.mostLucrative.clone();
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
        this.mostExp = this.energ_prov.get(comp_name).getHouses().get(owner);
        this.mostLucrative = this.energ_prov.get(comp_name);
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
        if (days != 0 && !dev_with_changes.isEmpty()){
            operateDevChanges();
        }
        LocalDate tmp = this.date;
        this.date = date.plusDays(days);
        if (tmp.isBefore(tmp.with(TemporalAdjusters.firstDayOfNextMonth()))
            && (date.isAfter(date.with(TemporalAdjusters.firstDayOfMonth())) 
            || date.isEqual(date.with(TemporalAdjusters.firstDayOfMonth())))){
            if (!energ_with_changes.isEmpty()) operateEnergChanges();
            if (!house_with_changes.isEmpty()) operateHouseChanges();
            for (SmartEP ep : this.energ_prov.values()){
                for (SmartHouse house : ep.getHouses().values()){
                    String comp = whereIsHouse(this.mostExp.getOwner());
                    if (ep.compute(house.getOwner()) > this.energ_prov.get(comp).compute(mostExp.getOwner())) this.mostExp = house;
                }
            }
            for (SmartEP ep : this.energ_prov.values()){

                if (ep.computeAll() > this.energ_prov.get(mostLucrative.getName()).computeAll()) this.mostLucrative = ep;
            }

        }
    }
    private void operateDevChanges() {
        for (SmartDevice dev: dev_with_changes.values()){
            String id = dev.getId();
            String owner = whereIsDev(id);
            String comp  = whereIsHouse(owner);
            this.energ_prov.get(comp).getHouses().get(owner).setDeviceOff(id);
            this.dev_with_changes.remove(id);
        }
    }

    private void operateEnergChanges() {
        for (SmartEP ep : energ_with_changes.values()){
            this.energ_prov.get(ep.getName()).changePrice();
            this.energ_with_changes.remove(ep.getName());
        }
    }

    private void operateHouseChanges() {
        for (String owner: house_with_changes.keySet()){
            String comp = whereIsHouse(owner);
            SmartEP ep = this.house_with_changes.get(owner);
            this.energ_prov.remove(ep.getName());
            this.energ_prov.put(ep.getName(),ep.clone());
            this.energ_prov.get(comp).getHouses().remove(owner);
            this.house_with_changes.remove(owner);
            
        }
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
    }

    public void incVol(String id) {
        String owner = whereIsDev(id);
        String comp = whereIsHouse(owner);
        this.energ_prov.get(comp).getHouses().get(owner).incDevVol(id);
    }

    public void decVol(String id) {
        String owner = whereIsDev(id);
        String comp = whereIsHouse(owner);
        this.energ_prov.get(comp).getHouses().get(owner).decDevVol(id);
        System.out.println(energ_prov.values());
    }

    public void incTone(String id) {
        String owner = whereIsDev(id);
        String comp = whereIsHouse(owner);
        this.energ_prov.get(comp).getHouses().get(owner).incDevTone(id);
    }

    public void decTone(String id) {
        String owner = whereIsDev(id);
        String comp = whereIsHouse(owner);
        this.energ_prov.get(comp).getHouses().get(owner).decDevTone(id);
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
    } 

    public void setAllOffDiv(String owner, String room) {
        String comp = whereIsHouse(owner);
        this.energ_prov.get(comp).getHouses().get(owner).setAllinRoomOff(room);
    }

    public void changeContract(String owner, String comp) {
        String comp_old = whereIsHouse(owner);
        SmartHouse house = this.energ_prov.get(comp_old).getHouses().get(owner);
        SmartEP comp_new = new SmartEP(this.energ_prov.get(comp));
        comp_new.addHouse(owner, house);
        this.house_with_changes.put(house.getOwner(), comp_new);
    }

    public void changeFormula(String comp) {
        SmartEP ep = this.energ_prov.get(comp).clone();
        this.energ_with_changes.put(comp,ep);
    } 

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
