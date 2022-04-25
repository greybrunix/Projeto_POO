package src;

/********************************************************
 * 
 * This class is a super class for each individual SD
 *   It follows that it has identifier and mode vars
 *    and related standard methods, such as getters
 *     and setters and optimized standard algorithms
 ********************************************************/
public class SmartDevice {
    // This could be a super of the devices
    private String id;
    private boolean mode;

    /* Do I actually want empty constructors? */
    public SmartDevice(){ // Might remove this
        this.setMode(false);
        this.setId("");
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
    public void setMode(boolean mode){
        this.mode = mode;
    }
    private void setId(String id){
        this.id = id;
    }


    public void setON(){
        this.setMode(true);
    }
    public void setOFF(){ // Note this can only happen after a day has passed
        this.setMode(false);
    }

    public boolean equals(Object o){
        if (this == o) return true;
        if (o == null || o.getClass() != this.getClass()) return false;
        SmartDevice sd = (SmartDevice) o;
        return (this.getId() == sd.getId());
    }
    public SmartDevice clone(){
        return new SmartDevice(this);
    }
}
