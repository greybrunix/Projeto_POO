package model;

import java.util.Map;
import java.util.Set;
import java.util.HashMap;
import java.util.HashSet;

public class State {
    private final Map<String, SmartDevice> devices;
    private final Map<String, SmartHouse> houses;
    private final Set<SmartEP> energ_prov;
    public State(){
        this.devices = new HashMap<String,SmartDevice>();
        this.houses = new HashMap<String, SmartHouse>();
        this.energ_prov = new HashSet<SmartEP>();
    }

}
