package src;
public class SmartCamera {
    
    /* Enums Structs etc */
    public enum Mode{
        ON,
        OFF
    };
    /* Variables */
    private Mode mode;
    private int resolution;
    private int size;
    private double daily_consumption;

    /* Constructors */
    public SmartCamera(){
        this.setMode(Mode.OFF);
        this.setRes(0);
        this.setSize(0);
        this.computeConsumption();
    }
    public SmartCamera(Mode mode, int res, int size, double dc){
        this.setMode(mode);
        this.setRes(res);
        this.setSize(size);
        this.computeConsumption();
    }
    public SmartCamera(SmartCamera c){
        this.setMode(c.getMode());
        this.setRes(c.getRes());
        this.setSize(c.getSize());
        this.computeConsumption();
    }
    /* Getters */
    public Mode getMode(){
        return this.mode;
    }
    public int getRes(){
        return this.resolution;
    }
    public int getSize(){
        return this.size;
    }
    public double getConsumption(){
        return this.daily_consumption;
    }
    /* Setters */
    private void setMode(Mode mode){ // Probably wll be redundant
        this.mode = mode;
    }
    public void setRes(int res){
        this.resolution = res;
        this.computeConsumption();
    }
    public void setSize(int size){
        this.size = size;
        this.computeConsumption();
    }
    /* Computations */
    public void computeConsumption(){
        if (this.getMode() == Mode.ON)
            this.daily_consumption = this.getRes() * this.getSize();
        else
            this.daily_consumption = 0;
    }



    /* Regular Methods */
    public void setON(){
        if (this.getMode() == Mode.OFF){
            this.setMode(Mode.ON);
            this.computeConsumption();
        }
    }
    public void setOFF(){
        if (this.getMode() == Mode.ON){
            this.setMode(Mode.OFF);
            this.computeConsumption();
        }
    }
    



    /* Overrides */
    public boolean equals(Object o){
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;

        SmartCamera camera = (SmartCamera) o;
        return (this.getRes() == camera.getRes()
                && this.getSize() == camera.getSize());
    }
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("This camera is turned ")
          .append(this.getMode())
          .append("\nThis camera is recording in res:\t")
          .append(this.getRes())
          .append("\nThis camera is storing in size:\t")
          .append(this.getSize())
          .append("\nThis Camera is expending daily:\t")
          .append(this.getConsumption())
          .append('\n');
        return sb.toString();
    }
    public SmartCamera clone(){
        return new SmartCamera(this);
    }
}
