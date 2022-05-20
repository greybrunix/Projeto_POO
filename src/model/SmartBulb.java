package model;
/**
 * Subclass of SmartDevice which implements a tone of light,
 * the dimensions of the bulb in centimetres and the daily consumption
 * 
 * @author Bruno Giao
 * @author Miguel Vaz
 * @author João Cruz
 */
public class SmartBulb extends SmartDevice{
    /* Constants */
    public static final int Consumption_Val = 1;
    public static final int WARM = 3;
    public static final int NEUTRAL = 2;
    public static final int COLD = 1;

    /* Variables */
    private int tone; /* WARM NEUTRAL COLD */
    private int dimensions; /* in centimetres */
    private double daily_consumption; /* formula is group specific */

    /* Constructors */
    public SmartBulb(){ // Might remove this
    }
    public SmartBulb(boolean mode,String id,int tone, int dimensions){
        super(mode, id);
        this.setTone(tone);
        this.setDimensions(dimensions);
        this.computeConsumption();
    }
    public SmartBulb(SmartBulb b){
        super(b.getMode(), b.getId());
        this.setTone(b.getTone());
        this.setDimensions(b.getDimensions());
        this.computeConsumption();
    }

    /* Getters */
    public int getTone(){
        return this.tone;
    }
    public int getDimensions(){
        return this.dimensions;
    }
    public double getConsumption(){
        return this.daily_consumption;
    }

    /* Setters */
    private void setTone(int t){
        if (t > WARM) this.tone = WARM;
        else if (t < COLD) this.tone = COLD;
        else this.tone = t;
        this.computeConsumption();
    }
    public void setDimensions(int dimensions){
        this.dimensions = dimensions;
    }

    /* Computations */
    public void computeConsumption(){
        if (this.getMode())
            switch(this.getTone()){
                case COLD: 
                    this.daily_consumption = Consumption_Val*10 + COLD*.25;
                    this.daily_consumption *= 24;
                    break;
                case NEUTRAL:
                    this.daily_consumption = Consumption_Val*10 + NEUTRAL*.5;
                    this.daily_consumption *= 24;
                    break;
                case WARM:
                    this.daily_consumption = Consumption_Val*10 + WARM*.75;
                    this.daily_consumption *= 24;
                    break;
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
    public void incTone(){
        if (this.getTone() < WARM) this.setTone(this.getTone()+1);
        else this.setTone(this.getTone());
    }
    public void decTone(){
        if (this.getTone() > COLD) this.setTone(this.getTone()-1);
        else this.setTone(this.getTone());
    }

    /* Overrides */
    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;

        SmartBulb bulb = (SmartBulb) o;
        return ( super.equals(bulb)
                && this.getMode() == bulb.getMode()
                && this.getDimensions() == bulb.getDimensions()
                && this.getTone() == bulb.getTone());
    }
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Lightbulb with ID:\t")
          .append(this.getId())
          .append("\nThis lightbulb is turned ")
          .append(this.getMode())
          .append("\nThis lightbulb is in tone:\t")
          .append(this.getTone())
          .append("\nThis lightbulb has dimensions in cm:\t")
          .append(this.getDimensions())
          .append("\nThis lightbulb is consuming:\t")
          .append(this.getConsumption())
          .append('\n');
        return sb.toString();
    }
    @Override
    public SmartBulb clone(){
        return new SmartBulb(this);
    }
}
