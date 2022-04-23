package src;
import java.util.Map;
import java.util.HashMap;
import java.util.List;

public class SmartHouse{

    private Map<String, SmartDevice> devices;
    private Map<String, List<String>> divisions;
    private String owner;
    private String NIF;

    public SmartHouse(){
        this.owner = "";
        this.NIF =  "";
        this.devices = new HashMap<String, SmartDevice>();
        this.divisions = new HashMap<String, List<String>>();
    }
    public SmartHouse(String owner, String NIF){
        this.owner = owner;
        this.NIF = NIF;
        this.devices = new HashMap<String, SmartDevice>();
        this.divisions = new HashMap<String, List<String>>();
    }
    public void setDeviceOn(String id){
        this.devices.get(id).setON();
    }
    public boolean deviceExists(String id)
    {
        return true;
    }
}