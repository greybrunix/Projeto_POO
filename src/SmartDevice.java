package src;

public class SmartDevice {
    // This could be a super of the devices
    private String id;
    private boolean mode;

    public SmartDevice(){
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
