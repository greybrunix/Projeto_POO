package src;

public abstract class Device {
    // This could be a super of the devices
    public enum Mode{
        ON,
        OFF
    };

    private Mode mode;

    public Device(){
        this.setMode(Mode.OFF);
    }
    public Device(Mode mode){
        this.setMode(mode);
    }
    public Device(Device d){
        this.setMode(d.getMode());
    }

    public Mode getMode(){
        return this.mode;
    }
    public void setMode(Mode mode){
        this.mode = mode;
    }

    public void setON(){
        if (this.getMode() == Mode.OFF){
            this.setMode(Mode.ON);
        }
    }
    public void setOFF(){ // Note this can only happen after a day has passed
        if (this.getMode() == Mode.ON){
            this.setMode(Mode.OFF);
        }
    }
}
