package model;

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
    public SmartCamera(){ // Might remove this
    }
    public SmartCamera(boolean mode, String id, int res, int size){
        super(mode, id);
        this.setRes(res);
        this.setSize(size);
        this.computeConsumption();
    }
    public SmartCamera(SmartCamera c){
        super(c.getMode(), c.getId());
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
        if (this.getMode()){
            this.daily_consumption = this.getRes()*0.009*0.1 * this.getSize()*0.02*0.9;
            this.daily_consumption *= 24;
        }
        else
            this.daily_consumption = 0;
    }
    public void setON(){
        setMode(true);
        this.computeConsumption();
    }
    public void setOFF(){
        setMode(false);
        this.computeConsumption();
    }
    
    
    /* Overrides */
    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;

        SmartCamera camera = (SmartCamera) o;
        return (super.equals(camera)
                && this.getRes() == camera.getRes()
                && this.getSize() == camera.getSize());
    }
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        return sb.append("( ")
          .append(this.getId())
          .append(": (ON: ")
          .append(this.getMode())
          .append(", ")
          .append(this.getRes())
          .append("p, ")
          .append(this.getSize())
          .append("KB, ")
          .append(this.getConsumption())
          .append("KWh) )").toString();
    }
    @Override
    public SmartCamera clone(){
        return new SmartCamera(this);
    }
}
