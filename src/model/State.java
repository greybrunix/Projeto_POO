package model;

import java.util.Map;
import java.util.Set;
import java.util.HashMap;
import java.util.HashSet;

public class State {
    private final Map<String, SmartDevice> devices = new HashMap<String, SmartDevice>();
    private final Map<String, SmartHouse> houses = new HashMap<String, SmartHouse>();
    private final Set<SmartEP> energ_prov = new HashSet<SmartEP>();
    public State(){
    }
}
