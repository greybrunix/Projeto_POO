package src.model;

/********************************************************
 * 
 * This class is a super class for each individual SD
 *   It follows that it has identifier and mode vars
 *    and related standard methods, such as getters
 *     and setters and optimized standard algorithms
 ********************************************************/
public abstract class SmartDevice {
    // This could be a super of the devices
    private String id;
    private static boolean mode;

    /* Do I actually want empty constructors? */
    public SmartDevice(){ // Might remove this
        setMode(false);
        this.setId("");
    }
    public SmartDevice(boolean mode, String id){
        setMode(mode);
        this.setId(id);
    }
    public SmartDevice(SmartDevice d){
        setMode(d.getMode());
        this.setId(d.getId());
    }

    public boolean getMode(){
        return mode;
    }
    public String getId(){
        return this.id;
    }
    abstract public double getConsumption(); 
    public static void setMode(boolean m){
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
