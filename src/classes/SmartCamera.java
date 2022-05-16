package src.classes;

/**
 * Subclass of SmartDevice which implements a 
 * resolution, a file storage size and a daily consumption
 */
public class SmartCamera extends SmartDevice{
    
    /* Variables */
    private int resolution;
    private int size;
    private double daily_consumption;

    /* Constructors */
    public SmartCamera(){ // Migth remove this
        super();
        this.setRes(0);
        this.setSize(0);
        this.computeConsumption();
    }
    public SmartCamera(boolean mode, String id, int res, int size, double dc){
        super();
        this.setRes(res);
        this.setSize(size);
        this.computeConsumption();
    }
    public SmartCamera(SmartCamera c){
        super();
        this.setRes(c.getRes());
        this.setSize(c.getSize());
        this.computeConsumption();
    }
    /* Getters */
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
        if (this.getMode())
            this.daily_consumption = this.getRes() * this.getSize();
        else
            this.daily_consumption = 0;
    }
    public void setON(){
        this.setMode(true);
        this.computeConsumption();
    }
    public void setOFF(){
        this.setMode(false);
        this.computeConsumption();
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
        sb.append("Camera with id:\t")
          .append(this.getId())
          .append("Is this camera on?\t")
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
