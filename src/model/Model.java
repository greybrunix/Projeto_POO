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

    public void turnOnDev(String string) {
    }

    public void turnOffDev(String string) {
    }

    public void incVol(String string) {
    }

    public void decVol(String string) {
    }

    public void incTone(String string) {
    }

    public void decTone(String string) {
    }

    public void getDevDC(String string) {
    }

    public void setAllOn(String string) {
    }

    public void setAllOnDiv(String string, String string2) {
    }

    public void setAllOff(String string) {
    }

    public void setAllOffDiv(String string) {
    }

    public void changeContract(String string, String string2) {
    }

    public void changeBaseValue(String string, int value) {
    }

    public void changeTaxFactor(String string, double tax) {
    }

    public void changeFormula(String string) {
    }
}
