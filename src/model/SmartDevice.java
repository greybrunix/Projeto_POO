package model;

/**
 * 
 * This class is a super class for each individual SD
 *   It follows that it has identifier and mode vars
 *    and related standard methods, such as getters
 *     and setters and optimized standard algorithms
 * 
 * @author Bruno Giao
 * @author Miguel Vaz
 * @author Joao Cruz
 */
public abstract class SmartDevice {
    // This could be a super of the devices
    private String id;
    private boolean mode;

    /* Do I actually want empty constructors? */
    public SmartDevice(){ // Might remove this
    }
    public SmartDevice(boolean mode, String id){
        this.setMode(mode);
        this.setId(id);
    }
    public SmartDevice(SmartDevice d){
        this.setMode(d.getMode());
        this.setId(d.getId());
    }

    public boolean getMode(){
        return this.mode;
    }
    public String getId(){
        return this.id;
    }
    abstract public double getConsumption(); 
    public void setMode(boolean m){
        mode = m;
    }
    private void setId(String id){
        this.id = id;
    }
    abstract void computeConsumption();

    abstract public void setON();
    abstract public void setOFF();

    public boolean equals(Object o){
        if (this == o) return true;
        if (o == null || o.getClass() != this.getClass()) return false;
        SmartDevice sd = (SmartDevice) o;
        return (this.getId() == sd.getId());
    }
    abstract public SmartDevice clone();
}
